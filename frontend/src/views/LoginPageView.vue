<template>
    <div class="wrapper">
        <div class="border-wrapper">
            <v-icon size="256" icon="mdi-account" color="blue"></v-icon>
            <v-form @submit.prevent="login">
                <v-text-field v-model="id" class="input" label="Admin ID: admin" bg-color="white"></v-text-field>
                <v-text-field v-model="pw" class="input" label="Admin Password: 0000" bg-color="white"
                type="password"></v-text-field>
                <v-btn :loading="loading" size="x-large" type="submit" block class="mt-2" color="primary">Get JSON Web Token</v-btn>
            </v-form>
            <div v-show="errorMsg" class="mt-4" style="color: red;">로그인 실패 {{ "(" + i + ")" }}</div>
        </div>
        <v-btn class="mt-5" color="red" @click="passLogin">
            로그인 백도어
        </v-btn>
    </div>
</template>


<script setup>

import axios from 'axios';
import { ref } from 'vue'
import { useRouter } from 'vue-router';


let loading = ref(false);

let errorMsg = ref(false);

let i = ref(0);

const router = useRouter();

let id = ref("");
let pw = ref("");


async function login() {
    loading.value = true;
    let formData = {
        id: id.value,
        pw: pw.value
    }
    const data = await axios.post('/api/login', formData, { "Content-Type": 'application/json' });
    if (!data.data.isSuccess) {
        i.value++;
        errorMsg.value = true;
        loading.value = false;
    } else if (data.data.isSuccess) {
        router.replace('/admin');
    }
}

function passLogin(){
    axios.get('/api/login/pass').then((a)=>{
        router.replace('/admin');
    })
}


</script>

<script>
export default {
    name: "LoginPageView",
}


</script>

<style scoped>
.wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 768px;
    height: 100vh;
    /* background-color: white; */
    justify-content: center;
}

.input {
    width: 100%;
    text-align: center;
}

@media screen and (max-width: 767px) {
  .wrapper {
    width: 100vw;
  }
}

/* .border-wrapper {
    display: inline-block;
    border: 3px solid rgb(64, 162, 219);
    padding: 5vh 5vh;
    border-radius: 10vh;
} */
</style>