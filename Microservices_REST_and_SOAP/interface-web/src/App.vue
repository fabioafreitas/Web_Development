<template>
  <div id="app">
    <div  v-if="!statusLogin" class="login">
      <h1 style="margin-bottom: 10%">Arquiteturas Orientadas a Serviços</h1>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">Username</span>
        </div>
        <input v-model="username" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
      </div>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">Password</span>
        </div>
        <input v-model="password" type="password" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
      </div>
      <button @click="autenticarUsuario()" type="button" class="btn btn-secondary btn-block">Login</button>
    </div>

    <div v-if="statusLogin">
      
      <div class="row">
        <div class="col-5">
          <div class="row">
            <div class="col form-group mb-5">
              <h1 class="mb-4">Inserir Imagem</h1>
              <input class="form-control-file mb-2" type="file" name="file" ref="filePost" @change="previewFilePost" multiple>
              <button class="btn btn-secondary btn-block" @click="post()">Inserir</button>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <h1 class="mb-4">Exibir Imagem</h1>
              <select class="form-control form-control-sm mb-3" v-model="selectImagem">
                <option :key='img' v-for="img in imagens">{{img}}</option>
              </select>
              <button class="btn btn-secondary btn-block mb-3" @click="get()">Exbir</button>
              <img class="rounded" :src="imagem">
            </div>
          </div>
        </div>
        <div class="col-7">
          <h1 class="mb-5">Chart Random Data</h1>
          <line-chart
            :height="500"
            :chartData="chartData"
            :options="chartOptions"
            :chartColors="chartColors"
            label="Data"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import LineChart from "./components/LineChart";

export default {
  name: "App",
  components: {
    LineChart,
  },
  data() {
    return {
      statusLogin: true,
      username: '',
      password: '',
      loginUrl: 'http://127.0.0.1:8080/login',
      dataUrl: 'http://127.0.0.1:7070/data',
      imagesUrl: 'http://127.0.0.1:6060/imagens',
      imagens: [],
      selectImagem: '',
      deleteImagem: '',
      imagem: '',
      filePost: '',
      filePut: '',
      chartData: [],
      chartColors: {
        borderColor: "#251F47",
        pointBorderColor: "#260F26",
        pointBackgroundColor: "#858EAB",
        backgroundColor: "#858EAB",
      },
      chartOptions: {
        responsive: true,
        animation: false,
        maintainAspectRatio: false,
        scales: {
          yAxes: [
            {
              ticks: {
                suggestedMin: 26,
                suggestedMax: 27.5,
              },
            },
          ],
        },
      }
    };
  },
  created() {
    this.interval = setInterval(() => this.getLogs(), 3000);
  },
  methods: {
    getLogs() {
      axios.get(this.dataUrl).then(response => {
        let arrValue = response.data;
        let arrAux = [];
        for (let i = 0; i < arrValue.length; i++) {
          let date = i;//moment(arrDate[i], "DD-MM-YYYY HH:mm:ss").format("mm:ss");
          arrAux.push({ date, total: arrValue[i] });
        }
        this.chartData = arrAux;
      });
    },

    autenticarUsuario() {
      axios.post(this.loginUrl, {username: this.username, password: this.password})
      .then(response => {
        if(!response.data) {
          alert("Usuário ou senha incorreto");
        }
        else {
          this.statusLogin = true;
        }
      })
    },
    previewFilePost() {
        this.filePost = this.$refs.filePost.files[0];
    },
    previewFilePut() {
        this.filePut = this.$refs.filePut.files[0];
    },
    get() {
        this.imagem = this.imagesUrl + '/' + this.selectImagem;
    },
    post() {
        let formData = new FormData();
        formData.append('file', this.filePost)
        let header = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post(
            this.imagesUrl, formData, header
        ).then(() => {
            alert("Adicionou");
        }).catch(() => {
            alert("Nao Adicionou")
        });
    },
    put() {
        let formData = new FormData();
        formData.append('file', this.filePut)
        let header = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.put(
            this.imagesUrl, formData, header
        ).then(() => {
            alert("Alterou");
        }).catch(() => {
            alert("Nao Alterou")
        });
    },
    deletar() {
        axios.delete(this.imagesUrl+'/'+this.deleteImagem)
        .then(() => {
            alert("Imagem deletada")
        }).catch( () => {
            alert("Nao foi deletada")
        });
    },
  },
  async mounted() {
      this.getLogs();
      axios.get(this.imagesUrl).then(response => {
          console.log(response.data)
          this.imagens = response.data;
      });
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #f3f6fb;
  height: 100vh;
  padding: 2%;
}

.login {
  margin-top:10vh; 
  padding-left: 30%; 
  padding-right: 30%
}

.teste {
  margin: 0 20% 0 20%;
}
</style>
