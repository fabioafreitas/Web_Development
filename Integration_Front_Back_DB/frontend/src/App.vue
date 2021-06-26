<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Clientes Frontend</a>
      </div>
    </nav>
    <div class="container">
      {{error}}
      <form @submit.prevent="salvar()">
        <label>Cpf</label>
        <input type="text" placeholder="cpf" v-model="cliente.cpf" />
        <label>Nome</label>
        <input type="text" placeholder="nome" v-model="cliente.nome" />
        <label>Username</label>
        <input type="text" placeholder="username" v-model="cliente.username" />
        <button @click="listar()" class="waves-effect waves-light btn-small">
          Salvar
          <i class="material-icons left">save</i>
        </button>
      </form>
      <table>
        <thead>
          <tr>
            <th>CPF</th>
            <th>NOME</th>
            <th>USERNAME</th>
            <th>OPÇÕES</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cliente of clientes" :key="cliente.key">
            <td>{{ cliente.cpf }}</td>
            <td>{{ cliente.nome }}</td>
            <td>{{ cliente.username }}</td>
            <td>
              <button @click="alterar(cliente)" class="waves-effect btn-small blue darken-1">
                <i class="material-icons">create</i>
              </button>
              <button @click="deletar(cliente)" class="waves-effect btn-small red darken-1">
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
import axios from "axios";

export default {
  name: "app",
  data: () => {
    return {
      url: "http://localhost:9090",
      cliente: {
        cpf: "",
        nome: "",
        username: ""
      },
      clientes: [],
      error: "",
      condicao: false
    };
  },
  methods: {
    salvar() {
      axios
        .post(this.url + "/clientes", this.cliente)
        .then(function() {
          this.listar();
          this.produto = {};
          alert("Salvo com sucesso!");
        })
        .catch(error => {
          this.error = error.response.data;
        });
    },
    alterar(cliente) {
      this.cliente = cliente;
    },
    deletar(cliente) {
      if (confirm("Deseja excluir o produto?")) {
        console.log(cliente);
        axios
          .delete(this.url + "/clientes", { data: cliente })
          .then(function() {
            this.listar();
            this.error = "";
            alert("Deletado com sucesso!");
          })
          .catch(error => {
            this.error = error.response.data;
          });
      }
    },
    listar() {
      axios
        .get(this.url + "/clientes")
        .then(response => {
          this.clientes = response.data;
        })
        .catch(error => {
          this.clientes = [];
          this.error = error.response.data;
        });
    }
  },
  mounted() {
    axios
      .get(this.url + "/clientes")
      .then(response => {
        this.clientes = response.data;
      })
      .catch(error => {
        this.clientes = [];
        this.error = error.response.data;
      });
  }
};
</script>

<style>
</style>
