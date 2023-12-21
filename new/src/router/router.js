import { createRouter, createWebHistory } from 'vue-router'
import MenuListView from '@/views/GuestMenuView.vue'
import MainView from '@/views/MainView.vue'
import AdminMainView from '@/views/AdminMainView.vue'





const routes = [
    { path: '/', component: MainView },
    { path: '/menu', component: MenuListView },
    { path: '/admin', component: AdminMainView },
  
  ]
  

  const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(),
    routes, // short for `routes: routes`
  })


  export {router}