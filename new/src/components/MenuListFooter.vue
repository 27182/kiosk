<template>
    <div>
        <v-divider inset color="#6A1B9A" thickness="5px"></v-divider>
        <span>

            <v-card class="total-price-wrapper ml-n3" variant="flat" width="200">
                <v-card-title>총</v-card-title>
                <v-card-title class="mt-n1">￦{{ totalPrice.toLocaleString() }}</v-card-title>
            </v-card>
            <div class="cart-text-wrapper">
                <div v-for="(menu_map,i) in myCart" :key="i">
                    <span>
                        {{ menu_map.get('menu').mname }} &nbsp;&nbsp;&nbsp; {{ menu_map.get('amount') }} 개 &nbsp;&nbsp;&nbsp; ￦{{ ((menu_map.get('amount') * menu_map.get('menu').price)).toLocaleString() }}
                    </span>
                    <v-btn @click="()=>deletefromCart(i)" class="ml-10" size="small" variant="flat" text="취소"></v-btn>
                </div> 
            </div> 

            <v-btn class="pay-btn" variant="tonal" height="13vh" 
            style=" background-color: rgb(179, 176, 189); font-weight: bolder;  margin: 1vh 1vw;">
            결제하기
        </v-btn>
    </span>
    </div>
</template>




<script setup>

import { watch, ref } from 'vue';
import { defineProps, defineEmits } from 'vue';
import { computed } from 'vue';

const props = defineProps(['myCart']);

computed(() => { return props.myCart })

const emit = defineEmits(['delete-from-cart']);


function deletefromCart(i){
    emit('delete-from-cart', i);
}

let totalPrice = ref(0);

watch(props.myCart, (newCart)=>{
    let tmp = 0;
    for(let i = 0; i < newCart.length ; i++){
        tmp += newCart[i].get('menu').price * newCart[i].get('amount');
    }
    totalPrice.value = tmp;
})

</script>

<script>

export default {
    name: 'MenuListFooter'
}

</script>

<style scoped>
.pay-btn {
    position: absolute;
    right: 0;
    bottom: 0;
    
} 


.cart-text-wrapper {
    height: 15vh;
    width: 60%;
    overflow: auto;
    margin-left: 8vw;
}

.total-price-wrapper {
    margin-top: 2vh;
    margin-left: 3vh;
    position: absolute;
    left: 0;
}

</style>