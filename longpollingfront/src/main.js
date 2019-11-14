import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-datetime/dist/vue-datetime.css'
import {Datetime} from 'vue-datetime'
import {EagleModal} from 'vue-eagle-modal'
import VueCookie from 'vue-cookie'

Vue.component('datetime', Datetime);
Vue.use(BootstrapVue);
Vue.use(EagleModal);
Vue.use(VueCookie);

App.$cookie = VueCookie;

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
