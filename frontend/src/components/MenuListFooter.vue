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
                        <v-table density="compact">
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
                
                <v-btn class="pay-btn" variant="tonal" height="13vh" @click="gotoPay" :disabled="!cartisempty"
                style=" background-color: rgb(179, 176, 189); font-weight: bolder;  margin: 1vh 1vw;">
                결제하기
                
            </v-btn>
            <v-img style="position: absolute; margin-bottom: 6vh; margin-right: 2.2vw; right: 0; bottom: 0; height: 10vh; width: 5vw;" src=" data:image/png;base64,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">
                    
                </v-img>
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
    screenSize.matches? isSmallScreen.value = true : isSmallScreen.value = false;
});


let isSmallScreen = ref(false);

let screenSize = window.matchMedia("(max-width: 650px)");

screenSize.addEventListener('change',()=>{
    if(screenSize.matches){
        try{
            isSmallScreen.value = true;

        } catch(e){
            document
        }
    } else {
        try{
            isSmallScreen.value = false;

        } catch(e){
            document
        }
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