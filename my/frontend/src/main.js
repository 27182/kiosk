import { createApp } from 'vue'
import App from './App.vue'
import { router } from '@/router/router.js'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import axios from 'axios';
import { aliases, mdi } from 'vuetify/iconsets/mdi'
import '@mdi/font/css/materialdesignicons.css'



const vuetify = createVuetify({
    components,
    directives,
    icons: {
      defaultSet: 'mdi',
      aliases,
      sets: {
        mdi,
      },
    },
  })



const app = createApp(App);
app.config.globalProperties.$axios = axios; 


app.use(vuetify).use(router).mount('#app')


