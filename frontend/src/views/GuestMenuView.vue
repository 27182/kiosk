<template>
  <div class="wrapper">
    <div>
      <div class="loading-progress" v-if="isWaitingtoPay">
        <v-progress-circular :size="74" :width="8" indeterminate></v-progress-circular>
      </div>
      <v-card variant="flat">
        <MenuListBody :imgsrc="imgsrc" :isLoading="isLoading" :myData="myData" @add-to-cart="addMenutoCart"></MenuListBody>
        <MenuListFooter @go-to-pay="gotoPay" @delete-from-cart="deletefromCart" :myCart="myCart"></MenuListFooter>
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
import { useRouter } from 'vue-router';


let myData = ref([]);
let isLoading = ref(true);
let myCart = ref([]);
let isWaitingtoPay = ref(false);

let imgsrc = ref({});
async function getData() {
  await axios.get("/api/menu/list").then((d) => {
    myData.value = d.data;
  })
}
const router = useRouter();

function gotoPay() {
  isWaitingtoPay.value = true;
  let content = {};
  let totalPrice = 0;
  let cartState = myCart.value.map(i => {
    let a = i.get('menu').mname;
    let b = i.get('amount');
    let c = i.get('menu').price * b;
    content[a] = b.toString() + "개";
    totalPrice += c;
    return [a, b, c];
  });


  const data = {
    'content': JSON.stringify(content).replace(/['"]+/g, ''),
    'totalPrice': totalPrice,
  }



  axios.post('/api/order', data

  ).then((res) => {
    if (res.data.isSuccess) {
      setTimeout(() => {
        isWaitingtoPay.value = false;
        router.push({
          path: '/result', name: 'result', state: {
            ono: res.data.ono,
            myCart: cartState,
          }
        });
      }, 1000
      );
    } else {
      alert('오류: 주문 접수 실패');
      isWaitingtoPay.value = false;
    }

  })
}


function addMenutoCart(i, amount) {
  let map = new Map();
  map.set('menu', i);
  map.set('amount', amount);
  myCart.value.push(map);
}

onMounted(async () => {
  await getData().then(async ()=>{
    for(let item of myData.value) {
      await axios.get("/api/menu_img/" + item.imgurl , {responseType: 'blob'}).then((a) => {
          let url = URL.createObjectURL(a.data);
          imgsrc.value[item.mno] = url;
      });
    }
  }
  )
  isLoading.value = false;
  
  
});


function deletefromCart(i) {
  myCart.value.splice(i, 1);
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
.loading-progress {
  position: absolute;
  z-index: 1;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 768px;
  /* top: 50%;
  left: 50%;
  transform: translate(-50%, -50%); */

}

.wrapper {
  width: 768px;
}
</style>
