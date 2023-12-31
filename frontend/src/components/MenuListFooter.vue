<template>
    <div>
        <v-divider inset color="#6A1B9A" thickness="5px"></v-divider>
        <span class="content-wrapper">
            <div class="total-price-wrapper">
                <v-card-title>총 주문 금액</v-card-title>
                <v-card-title class="mt-n1">￦{{ totalPrice.toLocaleString() }}</v-card-title>
            </div>
            <div class="cart-text-wrapper" ref="myRef">
                <v-table density="compact" v-show="!isSmallScreen">
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
                <!--                  sheet               -->
                <v-bottom-sheet inset>
                    <template v-slot:activator="{ props }">
                        <v-btn v-bind="props" variant="tonal" color="primary" text="내 카트" v-show="isSmallScreen" 
                        style="margin-top: 5vh;"></v-btn>
                    </template>
                    <v-card>
                        <v-table density="compact" v-show="!isSmallScreen.matches">
                            <thead>
                                <th>
                                    제품
                                </th>
                                <th>
                                    가격
                                </th>
                                <th>

                                </th>
                            </thead>
                            <tbody style="text-align: center;">
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
                    </v-card>
                    </v-bottom-sheet>
                    <!--                  sheet               -->
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
    screenSize.matches? isSmallScreen.value = true : isSmallScreen = false;
});


let isSmallScreen = ref(false);

let screenSize = window.matchMedia("(max-width: 650px)");

screenSize.addEventListener('change',()=>{
    if(screenSize.matches){
        isSmallScreen.value = true;
    } else {
        isSmallScreen.value = false;
    }
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



.total-price-wrapper {
    padding-top: 2vh;
    padding-left: 2vw;
    position: absolute;
    left: 0;
    display: inline;
    height: 100%;
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
    overflow: auto;
}

.content-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.cart-menu-name-wrapper {}

.cart-menu-price-wrapper {}


.del-btn-wrapper {}
</style>