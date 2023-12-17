import { createApp } from 'vue'
import App from './App.vue'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import routers from './router/router.js'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

const vuetify = createVuetify({
    components,
    directives,
  })

createApp(App).use(vuetify).use(routers)
.mount('#app')
