<template>
    <div class="admin-main-wrapper">
        <AdminMainBody :isLoading="isLoading" :myData="myData"></AdminMainBody>
        <AdminMainFooter></AdminMainFooter>
    </div>


</template>




<script setup>
import AdminMainBody from '@/components/AdminMainBody.vue';
import AdminMainFooter from '@/components/AdminMainFooter.vue';
import axios from 'axios';
import {ref, onMounted} from 'vue'


let myData = ref([]);
let isLoading = ref(true);


function getOrderData() {
  axios.get("/admin/order/list").then((d) => {
    myData.value = d.data;
    isLoading.value = false;
  })
}

onMounted(() => {
  getOrderData();
});


</script>

<script>

export default {
    name: 'AdminMainView',
   
}



</script>

<style scoped>
    .admin-main-wrapper {
        width: 100%;
        background-color: white;
        height: 100vh;
    }
</style>