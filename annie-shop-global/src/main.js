import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import routes from './routes'
import App from './App.vue'


import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';

Vue.use(ElementUI);
//加载路由中间件
Vue.use(VueRouter);
Vue.use(VueResource);

//定义路由
const router = new VueRouter({
    routes
});

new Vue({
    router,
    el: "#app",
    render: h => h(App)
});
