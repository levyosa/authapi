import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/global.css'
import 'bootstrap/dist/css/bootstrap.css'

createApp(App).use(router).mount('#app')