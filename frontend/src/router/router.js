import { createRouter, createWebHistory } from 'vue-router'
import MenuListView from '@/views/GuestMenuView.vue'
import MainView from '@/views/MainView.vue'
import AdminMainView from '@/views/AdminMainView.vue'
import GuestResultView from '@/views/GuestResultView.vue'
import LoginPageView from '@/views/LoginPageView.vue'
import PaymentView from '@/views/PaymentView.vue'






const routes = [
    { path: '/', component: MainView, name: 'main' },
    { path: '/menu', component: MenuListView, name: 'menu'},
    { path: '/result', component: GuestResultView, name: 'result'  },
    { path: '/admin', component: AdminMainView },
    { path: '/login', component: LoginPageView},
    { path: '/pay', component: PaymentView, name: 'pay'},
  
  ]
  

  const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(),
    routes, // short for `routes: routes`
  })


  export {router}