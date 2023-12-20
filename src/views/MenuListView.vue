<template>
  <div>
    <div>
      <v-card variant="flat" >
        <MenuListBody :isLoading="isLoading" :myData="myData" @add-to-cart="addMenutoCart"></MenuListBody>
        <MenuListFooter :myCart="myCart"></MenuListFooter>
      </v-card>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import axios from 'axios';
import { ref } from "vue";
import MenuListBody from '@/components/MenuListBody.vue'
import MenuListFooter from '@/components/MenuListFooter.vue'


let myData = ref([]);
let isLoading = ref(true);
let myCart = ref([]);

function getData() {
  axios.get("/menu/list").then((d) => {
    myData.value = d.data;
    isLoading.value = false;
  })
}


function addMenutoCart(i){
  myCart.value.push(i);
}

onMounted(() => {
  getData();
});



</script>

<script>
export default {
  name: 'HelloWorld',
  props: {

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
