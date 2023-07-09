import { createRouter, createWebHistory } from 'vue-router'
import {notification} from "ant-design-vue";
import store from "@/store";

const routes = [
  {
    path: '/login',
    component: () => import( '../views/login.vue')
  },
  {
    path: '/',
    component: () => import( '../views/main.vue'),
    meta:{
      loginRequire: true
    },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

//路由登录拦截
router.beforeEach((to,from,next)=>{
  if(to.matched.some(function (item){
    console.log("是否需要登录校验：",item.meta.loginRequire||false);
    return item.meta.loginRequire;
  })){
    const _member = store.state.member;
    console.log("页面登陆校验开始：",_member);
    if(!_member.token){
      console.log("用户未登录或登陆超时！");
      notification.error({description:"未登录或登陆超时"});
      next('/login');
    }else{
      next();
    }
  }else{
    next();
  }
});

export default router
