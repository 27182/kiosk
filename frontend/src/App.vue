<template>
    <RouterView/>
</template>



<script setup>


</script>

<script>

export default {
  name: 'App',
  components: {
    
  }
}
</script>

<style>
#app {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

html {
  overflow-x: auto !important;
}


body {
  margin: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
  width: 100vw;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
  background-image: url('./assets/backgroundimg.jpg');
}

body::-webkit-scrollbar {
  display: none;
  height: 0;
  width: 0;
}
</style>
