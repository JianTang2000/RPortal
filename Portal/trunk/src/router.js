import VueRouter from 'vue-router'
import Goods from './components/goods'
import Message from './components/message'
import Cart from './components/cart'
import Order from './components/order'
import UserInfo from './components/userInfo'
import LoginHint from './components/loginHint'
import Axios from 'axios'


// 1、创建路由对象
var router = new VueRouter({
    routes: [
        //path 是URL上显示的路径名，import处定义变量（MainView）的文件路径
        {path: '/goods', component: Goods},
        {path: '/message', component: Message, meta: {requiresAuth: true}},
        {path: '/cart', component: Cart, meta: {requiresAuth: true}},
        {path: '/order', component: Order, meta: {requiresAuth: true}},
        {path: '/loginHint', component: LoginHint},
        {path: '/userInfo', component: UserInfo}
    ]
})

// 2、处理需要登录才能访问的页面
router.beforeEach((to, from, next) => {
    // 如果需要验证登录状态
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // ID 1000以下为预置的保留用户,比如gust或者多个admin等
        // if (JSON.parse(localStorage.getItem('loginResult')).userId  < 1000) {
        if (JSON.parse(localStorage.getItem('loginResult')).userId  ==2 ) {
            next({
                path: '/loginHint',
                // to.fullPath表示把当前路由信息传递过去方便登录后跳转回
                query: {redirect: to.fullPath}
            })
        } else {
            // 如果登录，那么放行
            next()
        }
    } else {
        // 如果不需要验证，那么放行
        next()
    }
})

// 3、把路由对象暴露出去
export default router