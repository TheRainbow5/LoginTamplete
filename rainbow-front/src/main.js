import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 引入axios
import axios from 'axios'
//引入vuex
import vuex from 'vuex'


//注册vuex
Vue.use(vuex)
Vue.config.productionTip = false
// 注册element-ui
Vue.use(ElementUI)
// 注册axios
Vue.prototype.$axios = axios;
axios.defaults.baseURL = 'http://localhost:9090'  //设置全局的url地址


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')


