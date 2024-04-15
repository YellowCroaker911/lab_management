import { createApp } from 'vue'
import * as ElIcons from '@element-plus/icons-vue'
import App from './App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import axios from './plugins/axios.js'


const app = createApp(App);
for (const [key, component] of Object.entries(ElIcons)) {
  app.component(key, component)
}

app.use(router)
app.use(ElementPlus).mount('#app')
 
