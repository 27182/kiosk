<template>
  <MenuListView/>
</template>

<script>
import MenuListView from './views/MenuListView.vue';

export default {
  name: 'App',
  components: {
    MenuListView
}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}


body {
  -ms-overflow-style: none;  
  scrollbar-width: none;  
}

body::-webkit-scrollbar {
  display: none;
}

</style>
