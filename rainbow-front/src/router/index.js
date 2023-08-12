import Vue from 'vue'
import VueRouter from 'vue-router'
import vuex from '@/store/index'


Vue.use(VueRouter)

const routes = [
  // 登录界面路由
  {
    path: '/',
    component: () => import('@/views/Login/WelcomView.vue'),
    children: [
      { path: '/', name: 'welcome-loginPage', component: () => import('@/components/Login/LoginPage.vue') },
      { path: '/welcome-registerPage', name: 'registerPage', component: () => import('@/components/Login/RegisterPage.vue') },
      { path: '/welcome-forgetPasswordPage', name: 'forgetPassword', component: () => import('@/components/Login/ForgetPasswordPage.vue') }
    ]
  },
  // 首页
  {
    path: '/index',
    name: 'index',
    component: () => import('@/views/IndexView.vue'),
  },
]

//初始化路由
const router = new VueRouter({
  routes
})
/**
 * 路由守卫
 */
router.beforeEach((to, from, next) => {
  var path = to.path;
  console.log(path);
  if (path === '/' && vuex.state.userInfo != null) {
    next({ path: '/index' });
  } else if (path === '/welcome-registerPage' || path === '/welcome-forgetPasswordPage') {
    next();
  }
  else {
    if (path != '/' && vuex.state.userInfo == null) {
      next({ path: '/' });
    } else {
      next();
    }
  }
});


// if (path === '/welcome-registerPage' || path === '/welcome-forgetPasswordPage') {
//   next();
// } else {
//   //访问登录页，userInfo存在，跳转到首页
//   if (path === '/' && vuex.state.userInfo) {
//     next({ path: '/' });
//   }
//   else {   // 访问其他页面，userInfo不存在，跳转到登录
//     if (path != '/' && !vuex.state.userInfo) {
//       next({ path: '/index' });
//     } else {
//       next();  //放行
//     }
//   }
// }


export default router
