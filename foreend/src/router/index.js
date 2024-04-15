import { createRouter, createWebHashHistory } from 'vue-router'
import axios from '@/plugins/axios.js'
import Test from '@/views/test.vue'
import LoginVue from '@/views/Login.vue'
import Home from '@/views/home.vue'

// 定义路由关系
const routes= [
    {path: '/', name:"Login",component: LoginVue,},
	{path: '/home', name:"Home",component: Home,} ,
	{path: '/test', name:"Test",component: Test},
	
]
// 创建路由器
const router =createRouter({
  history: createWebHashHistory(),
  routes,
})

// 导出路由
export default router
