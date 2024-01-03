<template>
    <div class="wrapper">
        <iframe :src="kakaolink" style="height: max-content; width: max-content;">

        </iframe>
    </div>



</template>


<script setup>
import { watch, ref } from 'vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';



let kakaolink = ref("");
let myCart = ref([]);

const router = useRouter();

function getState() {
    if (history.state.myCart != undefined) {
        myCart.value = history.state.myCart;
    }
    if(history.state.kakaolink != undefined){
        kakaolink.value = history.state.kakaolink;
    }
}

watch(kakaolink,()=>{
    if(kakaolink.value.indexOf('pg_token') != -1){
        router.push({
        path: '/pay', name: 'pay', state: {
          myCart: myCart,
        }
      });
    }
})

onMounted(()=>{
    getState();
})


</script>


<script>

export default {
    name: 'PaymentView'
}

</script>


<style scoped>

.wrapper {
    height: 100vh;
}

</style>

