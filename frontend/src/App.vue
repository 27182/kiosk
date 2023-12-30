<template>
    <RouterView/>
</template>

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


body {
  margin: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
  background-color: #DDC9BC;
  width: 100vw;
  display: flex;
  flex-direction: column;
  align-items: center;
}

body::-webkit-scrollbar {
  display: none;
  height: 0;
  width: 0;
}
</style>
