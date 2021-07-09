import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import Vuex from 'vuex';
import router from "./router";
Vue.config.productionTip = false
import store from './store/index';
new Vue({
  vuetify,
  Vuex,
  store,
  router,
  render: h => h(App)
}).$mount('#app')
