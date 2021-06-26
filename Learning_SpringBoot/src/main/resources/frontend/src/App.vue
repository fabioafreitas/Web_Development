<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Produtos Front</a>
      </div>
    </nav>
    <div class="container">
      {{error}}
      <form @submit.prevent="salvar">
        <label>Nome</label>
        <input type="text" placeholder="Nome" v-model="produto.nome">
        <label>Quantidade</label>
        <input type="number" placeholder="QTD" v-model="produto.quantidade">
        <label>Valor</label>
        <input type="text" placeholder="Valor" v-model="produto.valor">
        <button @click="listar()" class="waves-effect waves-light btn-small">
          Salvar
          <i class="material-icons left">save</i>
        </button>
      </form>
      <table>
        <thead>
          <tr>
            <th>NOME</th>
            <th>QTD</th>
            <th>VALOR</th>
            <th>OPÇÕES</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="produto of produtos" :key="produto.key">
            <td>{{ produto.nome }}</td>
            <td>{{ produto.quantidade }}</td>
            <td>{{ produto.valor | filtroDinheiro }}</td>
            <td>
              <button @click="alterar(produto)" class="waves-effect btn-small blue darken-1">
                <i class="material-icons">create</i>
              </button>
              <button @click="deletar(produto)" class="waves-effect btn-small red darken-1">
                <i class="material-icons">delete_sweep</i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script type="text/javascript">
import axios from 'axios'
export default {
  name: 'app',
  data: () => {
    return {
      url: "http://localhost:8080",
      produto: {
        nome: "",
        quantidade: "",
        valor: ""
      },
      produtos: [],
      prodJson: "",
      error: ""
    }
  },
  methods: {
    salvar() {
      if (!this.jaExiste) {
        axios
          .post(this.url + "/produtos", this.produto)
          .then(response => {
            this.listar;
            this.produto = {};
            alert("Salvo com sucesso!");
          })
          .catch(error => {
            this.error = error.data;
          });
      } else {
        axios
          .put(this.url + "/produtos", this.produto)
          .then(response => {
            this.listar;
            this.produto = {};
            alert("Alterado com sucesso!");
          })
          .catch(error => {
            this.error = error.data;
          });
      }
    },
    alterar(produto) {
      this.produto = produto;
    },
    deletar(produto) {
      if (confirm("Deseja excluir o produto?")) {
        axios
          .delete(this.url + "/produtos", { data: { nome: produto.nome } })
          .then(resposta => {
            this.listar;
            this.error = "";
            alert("Deletado com sucesso!");
          })
          .catch(error => {
            this.error = error.data;
          });
      }
    }
  },
  computed: {
    jaExiste() {
      for (let i = 0; i < this.produtos.length; i++) {
        if (this.produto.nome === this.produtos[i].nome) {
          return true;
        }
      }
      return false;
    },
    listar() {
      axios.get(this.url + "/produtos").then(response => {
        this.produtos = response.data;
      });
    }
  },
  filters: {
    filtroDinheiro: function(valor) {
      return ("R$ " + valor.toFixed(2)).replace(".", ",");
    }
  },
  mounted() {
    axios.get(this.url + "/produtos").then(response => {
      this.produtos = response.data;
    });
  }
}
</script>

<style>
</style>
