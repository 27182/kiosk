<template>
    <div>
        <v-divider inset color="#6A1B9A" thickness="5px"></v-divider>
  <!--       <div v-for="menu in myCart" :key="menu">
            {{ menu }}
        </div> -->
        <span style="position: relative;">
            <div class="cart-text-wrapper">
                ds
            </div>

            <v-btn class="pay-btn" variant="tonal" height="11vh" 
            style=" background-color: rgb(179, 176, 189); font-weight: bold;  margin: 1vh 1vw;">
            결제하기
        </v-btn>
    </span>
    </div>
</template>




<script setup>

import { defineProps } from 'vue';
import { computed } from 'vue';

const props = defineProps(['myCart']);

computed(() => { return props.myCart })

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
    bottom: 2vh;
    
} 


.cart-text-wrapper {
    margin-left: 5vw;
    height: 15vh;
    width: 70vw;
}

</style>