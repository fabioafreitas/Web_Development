import Vue from 'vue'
import Router from 'vue-router'
import Hello from './views/Hello.vue'
import World from './views/World.vue'


Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home'
    },
    {
      path: '/hello',
      name: 'hello',
      component: Hello
    },
    {
      path: '/world',
      name: 'world',
      component: World
    }
  ]
})
