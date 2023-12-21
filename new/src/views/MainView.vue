<template>
    <div class="wrapper">
        <RouterLink to="/menu">
            <v-btn color="#42b983" prepend-icon="$vuetify" stacked class="mb-5">
                Guest
            </v-btn>
        </RouterLink>
        <RouterLink to="/admin">
            <v-btn color="#2c3e50" prepend-icon="$vuetify" stacked>
                Admin
            </v-btn>
        </RouterLink>
        </div>
</template>

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