<template>
    <div>
        <v-divider inset color="#6A1B9A" thickness="5px"></v-divider>
        <span>
            <v-card class="total-price-wrapper" variant="flat">
                <v-card-title>총</v-card-title>
                <v-card-title class="mt-n1">￦{{ totalPrice.toLocaleString() }}</v-card-title>
            </v-card>
            <div class="cart-text-wrapper" ref="myRef">
                <v-table density="compact">
                    <tbody>
                        <tr v-for="(menu_map, i) in myCart" :key="i">
                            <td class="cart-menu-name-wrapper">
                                {{ menu_map.get('menu').mname }} &nbsp;&nbsp;{{ menu_map.get('amount') }} 개 &nbsp;&nbsp;
                            </td>
                            <td class="cart-menu-price-wrapper">
                                ￦{{ ((menu_map.get('amount') * menu_map.get('menu').price)).toLocaleString() }}

                            </td>
                            <td class="del-btn-wrapper">
                                <v-btn @click="() => deletefromCart(i)" size="small" variant="tonal">취소</v-btn>
                            </td>


                        </tr>
                    </tbody>
                </v-table>
            </div>

            <v-btn class="pay-btn" variant="tonal" height="13vh" @click="gotoPay" :disabled="cartisempty"
                style=" background-color: rgb(179, 176, 189); font-weight: bolder;  margin: 1vh 1vw;">
                결제하기
            </v-btn>
        </span>
    </div>
</template>




<script setup>

import { watch, ref, nextTick } from 'vue';
import { onMounted } from 'vue';
import { defineProps, defineEmits } from 'vue';
import { computed } from 'vue';

const props = defineProps(['myCart']);

computed(() => { return props.myCart })

const emit = defineEmits(['delete-from-cart', 'go-to-pay']);


function deletefromCart(i) {
    emit('delete-from-cart', i);
}

let totalPrice = ref(0);

let cartisempty = ref(true);


watch(props.myCart, (newCart) => {
    let tmp = 0;
    for (let i = 0; i < newCart.length; i++) {
        tmp += newCart[i].get('menu').price * newCart[i].get('amount');
    }
    totalPrice.value = tmp;
    nextTick(() => {
        if (myRef.value) {
            myRef.value.scrollTo(0, myRef.value.scrollHeight);
        }
    });
})

watch(props.myCart, () => {
    props.myCart.length == 0 ? cartisempty.value = true : cartisempty.value = false;
})


function gotoPay() {
    emit('go-to-pay');
}

const myRef = ref(null);

onMounted(() => {

});


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



.total-price-wrapper {
    margin-top: 2vh;
    margin-left: 2vw;
    position: absolute;
    left: 0;
    display: inline;
}

/* .menu-text-wrapper {
    position: relative;
    margin-bottom: 5px;
}

.del-btn-wrapper {
    position: absolute;
    right: 0;
    margin-right: 1vw;
} */

.cart-text-wrapper {
    height: 15vh;
    width: 60%;
    overflow: auto;
    margin-left: 10vw;
}

.menu-text-wrapper {
    display: flex;
    flex-direction: row;
}

.cart-menu-name-wrapper {
    width: 70%;
}

.cart-menu-price-wrapper {
    width: 20%;
}


.del-btn-wrapper {}


</style>