<template>
    <div class="wrapper">
        <RouterLink to="/menu" style="width: 100%;">
            <v-btn color="#42b983" prepend-icon="$vuetify" stacked class="mb-5" style="width: 100%;">
                손님용 페이지
            </v-btn>
        </RouterLink>
        <div v-show="isLogin">
            <RouterLink to="/admin">
                <v-btn color="#2c3e50" prepend-icon="$vuetify" stacked>
                    관리자용 페이지
                </v-btn>
            </RouterLink>
        </div>
        <div v-show="!isLogin">
            <RouterLink to="/login">
                <v-btn color="#2c3e50" prepend-icon="$vuetify" stacked>
                    관리자용 페이지
                </v-btn>
            </RouterLink>
        </div>
    </div>
</template>


<script setup>

import { onMounted, ref } from 'vue';


let isLogin = ref(false);

onMounted(() => {
    let match = document.cookie.match(new RegExp('(^| )' + "Login" + '=([^;]+)'));
    if (match) {
        isLogin.value = true;
    }
})


</script>

<script>

export default {
    name: 'MainView'
}

</script>

<style scoped>
.wrapper {
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>