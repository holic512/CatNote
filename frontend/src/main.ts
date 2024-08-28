// main.ts
import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router/index.ts'
import {createPinia} from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

// 配置pinia
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

const app = createApp(App)

app.use(pinia)
app.use(router)
app.use(ElementPlus)
app.mount('#app')