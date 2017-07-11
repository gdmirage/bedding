import Vue from 'vue';

// 系统参数
import ParamManager from './components/common/ParamManager.vue'


import NotFound from './components/404.vue'
import Abstract from './components/Abstract.vue'

// 商品中心   Begin---------
// 分类管理
import ProductTypeManager from './components/goods/productType/ProductTypeManager.vue'
import ProductCategoryManager from './components/goods/category/CategoryManager.vue'

//单件商品管理
import SingleProductManager from './components/goods/singleProduct/SingleProductManager.vue'
//编辑单件商品
import AddSingleProduct from './components/goods/singleProduct/AddSingleProduct.vue'

// 商品中心   End---------

const root = Vue.component('root', {
    template: '<router-view></router-view>'
});

let routes = [
    {
        path: '/404',
        component: NotFound,
        name: '404',
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/',
        component: root,
        name: '首页',
        meta: {
            requiresAuth: true
        },
        children: [
            {
                path: 'common',
                name: '通用功能',
                iconClass: 'el-icon-message',
                component: Abstract,
                children: [
                    {
                        path: 'paramManager',
                        name: '系统参数',
                        component: ParamManager,
                    }
                ]
            },
            {
                path: 'goods',
                name: '商品中心',
                iconClass: 'el-icon-message',
                component: Abstract,
                children: [
                    {
                        path: 'categoryManager',
                        name: '分类管理',
                        component: ProductCategoryManager,
                    },
                    {
                        path: 'productTypeManager',
                        name: '商品分类管理',
                        component: ProductTypeManager,
                    },
                    {
                        path: 'singleProductManager',
                        name: '单品列表管理',
                        component: Abstract,
                        children:[
                            {
                                path: 'list',
                                name: '列表筛选展开',
                                component: SingleProductManager
                            } ,
                            {
                                path: 'add',
                                name: '商品新增/编辑',
                                component: AddSingleProduct
                            } ,
                        ]
                        }
                    ]
            }
        ]
    },
    {
        path: '*',
        redirect: {path: '/404'}
    }
];
export default routes;
