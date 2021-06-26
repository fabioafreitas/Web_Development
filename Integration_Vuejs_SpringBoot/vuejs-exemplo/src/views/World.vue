<template>
  <div>
    <p>
        <button @click="requisicaoSpringBoot">requisição</button>
    </p>
    <h1 style="color:red" >{{texto}}</h1><h1> Hello</h1>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "world",
  data() {
    return {
      uri: "https://springboot-exemplo-deploy.herokuapp.com/",
      texto: ""
    };
  },
  methods: {
    requisicaoSpringBoot() {
        axios.get(this.uri+'/world')
            .then(response => {
                this.texto = response.data
            })
    }
  }
};
</script>