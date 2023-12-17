<template>
  <div class="hello">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <router-link to="menu">
      <v-btn prepend-icon="$vuetify" variant="tonal">
        Order
      </v-btn>
    </router-link>
    <br>
    <br>
    <router-link to="admin">
      <v-btn prepend-icon="$vuetify" variant="tonal">
        Admin
      </v-btn>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


ul {
  list-style-type: none;
  padding: 0;
}



a {
  color: #42b983;
}
</style>
