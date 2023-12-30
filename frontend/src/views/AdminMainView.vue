<template>
  <div class="admin-main-wrapper">
    <AdminMainBody :isLoading="isLoading" :myMenuData="myMenuData" :myOrderData="myOrderData" @refresh="getAllData">
    </AdminMainBody>
  </div>
</template>




<script setup>
import AdminMainBody from '@/components/AdminMainBody.vue';
import axios from 'axios';
import { watch } from 'vue';
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router';



let myMenuData = ref([]);
let myOrderData = ref([]);
let isLoading = ref(true);
let loadingStack = ref(0);

const router = useRouter();


function getOrderData() {
  axios.get("/api/admin/order/list").then((d) => {
    myOrderData.value = d.data;
    myOrderData.value.forEach((a) => { a.regDate = formatDate(a.regDate); });
    loadingStack.value++;
  }).catch(e => {
    if (e.response.status === 401) {
      router.replace('/login')
    }
  }
  )
}
function formatDate(value) {
  return value.replace('T', ' ').substring(0, 19);
}

function getMenuData() {
  axios.get("/api/admin/menu/list").then((d) => {
    myMenuData.value = d.data;
    myMenuData.value.forEach((a) => {
      a.regDate = formatDate(a.regDate);
      a.available == "1" ? a.available = "가능" : a.available = "불가";
      a.recommanded == "1" ? a.recommanded = "O" : a.recommanded = "X";

    });
    loadingStack.value++;
  }).catch(e => {
    if (e.response.status === 401) {
      router.replace('/login')
    }
  }
  )
}

function getAllData() {
  getMenuData();
  getOrderData();
}


onMounted(() => {
  getMenuData();
  getOrderData();
});


watch(loadingStack, () => { if (loadingStack.value == 2) isLoading.value = false; });


</script>

<script>

export default {
  name: 'AdminMainView',

}



</script>

<style scoped>
.admin-main-wrapper {
  width: 40vw;
  background-color: white;
  height: 100vh;
}
</style>