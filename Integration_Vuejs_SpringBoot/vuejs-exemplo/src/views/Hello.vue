<template>
  <div>
    <p>
        <button @click="requisicaoSpringBoot">requisição</button>
    </p>
    <h1 style="color:red" >{{texto}}</h1><h1> World</h1>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "hello",
  data() {
    return {
      uri: "https://springboot-exemplo-deploy.herokuapp.com/",
      texto: ""
    };
  },
  methods: {
    requisicaoSpringBoot() {
        axios.get(this.uri+'/hello')
            .then(response => {
                this.texto = response.data
            })
    }
  }
};
</script>