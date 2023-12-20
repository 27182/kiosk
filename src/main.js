import { createApp } from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import axios from 'axios';

const vuetify = createVuetify({
    components,
    directives,
  })



const app = createApp(App);
app.config.globalProperties.$axios = axios; 


app.use(vuetify).use(VueRouter).mount('#app')


