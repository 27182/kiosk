<template>
  <div>
    <div>
      <v-card variant="flat" >
        <MenuListBody :isLoading="isLoading" :myData="myData" @add-to-cart="addMenutoCart"></MenuListBody>
        <MenuListFooter @delete-from-cart="deletefromCart" :myCart="myCart"></MenuListFooter>
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


function addMenutoCart(i,amount){
  let map = new Map();
  map.set('menu',i);
  map.set('amount',amount);
  myCart.value.push(map);
}

onMounted(() => {
  getData();
});


function deletefromCart(i){
  console.log(i);
  myCart.value.splice(i,1);
  console.log(myCart);
}



</script>

<script>
export default {
  name: 'GuestMenuView',
  props: {

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
