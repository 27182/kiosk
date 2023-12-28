<template>
    <div>
        <RouterLink to="/">
            <v-btn text="처음으로" size="small" color="primary" variant="tonal" class="mt-10 mr-3">
            </v-btn>
        </RouterLink>
            <v-btn @click="deleteToken" text="토큰 삭제" size="small" color="primary" variant="tonal" class="mt-10 ml-3">
            </v-btn>
    </div>
</template>


<script setup>
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();

function deleteToken(){
    axios.post('/api/logout').then((a)=>{
        router.replace('/');
    })
}

</script>

<script>

export default {
    name: 'AdminMainFooter'
}

</script>