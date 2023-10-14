import Vue from 'vue'
import VueRouter from 'vue-router'
import * as path from "path";
import store from "@/store";
import it from "element-ui/src/locale/lang/it";


Vue.use(VueRouter)

const routes = [

  // {
  //   path: '/',
  //   component: () => import ('../views/Manage.vue'),
  //   redirect:"/home",
  //   children:[
  //     { path:'home',name:'主页',component: ()=> import ('../views/Home.vue')},
  //     { path:'user',name:'用户管理',component: ()=> import ('../views/User.vue')},
  //     { path:'role',name:'角色管理',component: ()=> import ('../views/Role.vue')},
  //     { path:'menu',name:'菜单管理',component: ()=> import ('../views/Menu.vue')},
  //     {
  //       path: '/file',
  //       name: 'file',
  //       component: () => import('../views/File.vue')
  //     },
  //     {
  //       path: 'person',
  //       name: '个人信息',
  //       component: () => import('../views/Person.vue')
  //     }
  //
  //
  //
  //
  //
  //
  //   ]
  // },

  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/404',
    name: '404',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/404.vue')
  },


  {
    path: '/register',
    name: 'register',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  },

  {
    path: '/front',
    name: 'front',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/front/Front'),
    children:[{
      path: 'home',
      name: 'FrontHome',
      component: ()=> import("../views/front/Home")
    },
      {
        path: 'map',
        name: 'map',
        component: ()=> import("../views/front/Map")
      },
      {
        path: "/password",
        name: 'password',
        component: () => import('../views/front/Password')
      },

      {
        path: 'video',
        name: 'video',
        component: ()=> import("../views/front/Video")
      },
      {
        path: 'videoDetail',
        name: 'videoDetail',
        component: ()=> import("../views/front/VideoDetail")
      }]
  },


]
export const  setRoutes = () => {
  const storeMenus = localStorage.getItem("menus")
  if (storeMenus) {
    const manageRoute = {path: '/', name:'Manage', component: () => import ('../views/Manage.vue'), redirect:"/home", children:[
        { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
        {
          path: "/password",
          name: 'password',
          component: () => import('../views/Password')
        },
      ]}
    const menus = JSON.parse(storeMenus)

    menus.forEach(item => {
      if (item.path) {  // 当且仅当path不为空的时候才去设置路由
        let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
        manageRoute.children.push(itemMenu)
      } else if(item.children.length) {
        item.children.forEach(item => {
          if (item.path) {
            let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
            manageRoute.children.push(itemMenu)
          }
        })
      }
    })

    const currentRouteNames = router.getRoutes().map(v => v.name)
    if(!currentRouteNames.includes('Manage')){
      router.addRoute(manageRoute)

    }



  }
}
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
setRoutes()


router.beforeEach((to,from,next)=>{
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称
  store.commit("setPath")

  // 未找到路由的情况
  if (!to.matched.length) {
    const storeMenus = localStorage.getItem("menus")
    if (storeMenus) {
      next("/404")
    } else {
      // 跳回登录页面
      next("/login")
    }
  }
  // 其他的情况都放行
  next()
})

export default router
