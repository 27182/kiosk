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
  font-family: 'maple' !important;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

* {
  font-family: 'maple' !important;
}

@font-face {
  font-family: 'maple';
  src: url('assets/Maplestory-Bold.woff') format('woff');
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
