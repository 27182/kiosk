<template>
    <div class="all-wrapper">
        <div class="result-wrapper">
            <v-card class="result-card pa-8" variant="flat">
                <v-divider></v-divider>
                <div class="py-12 text-center">
                    <v-icon class="mb-6" color="success" icon="mdi-check-circle-outline" size="128"></v-icon>
                    <div class="text-h4 font-weight-bold">주문이 접수되었습니다!</div>
                </div>
                <div class="text-h6 font-weight-bold">주문 번호</div>
                <div class="text-h3 font-weight-bold mb-5">{{ ono }}</div>
                
                <span>
                    <v-table density="compact">
                        <thead>
                            <tr>
                                <th class="text-center">
                                    상품명
                                </th>
                                <th class="text-center">
                                    수량
                                </th>
                                <th class="text-center">
                                    가격
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in myCart" :key="item">
                                <td>{{ item[0]}}</td>
                                <td>{{ item[1] }}</td>
                                <td>￦{{ item[2].toLocaleString() }}</td>
                            </tr>
                        </tbody>
                        
                        <tfoot>
                            <tr style="font-weight: bold;">
                                <td></td>
                                <td></td>
                                <td>총합 ￦{{total.toLocaleString()}}</td>
                            </tr>
                        </tfoot>
                    </v-table>
                    
                </span>

                
                
                <span>
                    <RouterLink to="/">
                        <v-btn text="처음으로" size="x-large" color="primary" class="mb-10 mt-16">
                        </v-btn>
                    </RouterLink>
                </span>
                <v-divider>
                    
                </v-divider>
            </v-card>
            
            
        </div>
    </div>
    </template>


<script setup>
import { onMounted, ref } from 'vue';


onMounted(() => {
    printCart();
    for(let i = 0  ; i < myCart.value.length ; i++){
        total.value += myCart.value[i][2];
    }
    ono.value = history.state.ono;
    
})
let ono = ref(0);
let total = ref(0);
let myCart = ref([]);


function printCart() {
    if (history.state.myCart != undefined) {
        myCart.value = history.state.myCart;
    }
}

</script>


<style>
.result-card {
    width: 40vw;
}

.result-wrapper {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: white;
}

.all-wrapper {
    height: 100vh;
    width: 40vw;
    background-color: white;
}
</style>