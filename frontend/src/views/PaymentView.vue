<template>
    <div class="wrapper">
        <div class="loading-progress" v-show="isWaitingtoload">
            <v-progress-circular :size="74" :width="8" indeterminate color="yellow"></v-progress-circular>
        </div>

        <iframe ref="frame" class="pay-frame" :src="kakaoLink" v-show="!isWaitingtoload">

        </iframe>
        <div class="notice">
            <h4>
                테스트용 키로 구현하여 결제하여도 실제 돈은 빠져나가지 않습니다.
                <v-btn text="결제 스킵하기" color="red" @click="skip">

                </v-btn>
            </h4>
        </div>



    </div>
</template>



<script setup>
import axios from 'axios';
// import { computed } from 'vue';
// import { watch } from 'vue';
import { onMounted } from 'vue';
import { ref } from 'vue'
import { useRouter } from 'vue-router';

let kakaoLink = ref("");

let myCart = [];

let orderData = ref({});

let frame = ref(null);

let ono = 0;

// let frame_href = ref("");

let isWaitingtoload = ref(true);


onMounted(() => {
    if (history.state.myCart != null) {
        myCart = history.state.myCart;
    }

    orderData.value = history.state.orderData;

    // console.log(frame.value);

    getReadytoPay();

})

// computed(()=>{
//     frame_href.value = frame.value.contentWindow.location.href;
// })



// watch(frame_href,()=>{
//     try{
//         frame_href.value = frame.value.contentWindow.location.href;
//         if(frame_href.value.indexOf("pg_token") !== -1){
//             alert('!!')
//         }
//         console.log(frame_href.value)
//     } catch(e){
//         console.log(e);
//     }
// })

const router = useRouter();
window.addEventListener('message', function (e) {
    if (e.data.isSuccess === 'true') {
        let sendingCart = myCart;
        router.push({
            path: '/result', name: 'result', state: {
                ono: e.data.ono,
                myCart: sendingCart,
            }
        });
    } else if(e.data.isSuccess === 'false'){
        router.push({
            path: '/menu', name: 'menu', state: {
                cancelPay: "true"
            }
        });
    }
})


function getReadytoPay() {
    axios.post('/api/kakaopay/ready', orderData.value).then((res) => {
        kakaoLink.value = res.data.next_redirect_pc_url;
        ono = res.data.ono;
        setTimeout(() => { isWaitingtoload.value = false }, 500)
    })
}


function skip() {

    isWaitingtoload.value = true;

    orderData.value.ono = ono

    axios.post('/api/order', orderData.value
    ).then((res) => {
        if (res.data.isSuccess) {
            setTimeout(() => {
                isWaitingtoload.value = false;
                let sendingCart = myCart;
                router.push({
                    path: '/result', name: 'result', state: {
                        ono: res.data.ono,
                        myCart: sendingCart,
                    }
                });
            }, 1000
            );
        } else {
            alert('오류: 주문 접수 실패');
            isWaitingtoload.value = false;
        }

    })
}


</script>


<script>

export default {
    name: 'PaymentView'
}


</script>


<style scoped>
.wrapper {
    height: 100vh;
    width: 35em;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.notice {
    color: red;
}

.pay-frame {
    width: 100%;
    height: 80%;
    border: none;
    border-radius: 25px;
}

.loading-progress {
    height: 80%;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    z-index: 1;
}
</style>