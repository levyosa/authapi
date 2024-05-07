import { createRouter, createWebHistory } from 'vue-router'
import LoginComponent from './components/LoginComponent.vue'
import RegisterComponent from './components/RegisterComponent.vue'
import UserListComponent from './components/UserListComponent.vue'

const routes = [
  { path: '/login', component: LoginComponent },
  { path: '/register', component: RegisterComponent },
  { path: '/users', component: UserListComponent }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router