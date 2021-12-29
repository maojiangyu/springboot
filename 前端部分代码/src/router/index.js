import Vue from 'vue'
import Router from 'vue-router'
import CommunityIndex from "../components/CommunityIndex";
import login from "../components/login";
import CIndex from "../components/Community/CIndex";
import normalC from "../components/Community/normalC";
import register from "../components/register";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: login
    },
    {
      path:'/community',
      name:'community',
      component:CommunityIndex
    },
    {
      path:'/Community/information',
      component:CIndex
    },
    {
      path:'/Community/myCommunity',
      component:normalC
    },
    {
      path: '/register',
      name: 'register',
      component: register
    }

  ]
})
