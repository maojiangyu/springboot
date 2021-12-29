// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Vuex from 'vuex'

Vue.use(Vuex)
Vue.use(ElementUI);

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'

Vue.prototype.$axios = axios;
Vue.config.productionTip = false

const store = new Vuex.Store({
  state:{
    user:{
      id :1,
      join:true,
      name:'mjy',
      community:1,
      isLeader:true
    },
    key:3,
    myCommunity:[],
    whichCommunity:1
  },
  mutations:{
    getMyCommunty(state,community){
      state.myCommunity = community
    },
    getKey(state,id){
      state.key = id;
    },
    setUser(state,user){
      state.user = user
    },
    setCommunity(state,n){
      state.whichCommunity = n
    }
  },
  actions:{
    setUser(context){
      context.commit('setUser')
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store:store,
  components: { App },
  template: '<App/>'
})
