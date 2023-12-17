<template>
    <router-link to="/">
        처음으로
    </router-link>
    <div>
        <div class="TabNav-wrapper">
            <MenuTabNav></MenuTabNav>
        </div>
        <MenuCartFooter></MenuCartFooter>
    </div>
</template>

<script>
import MenuTabNav from '@/components/MenuTabNav.vue'
import MenuCartFooter from '@/components/MenuCartFooter.vue'
export default {
    name: 'MenuPage',
    props: {
    },
    components: {
        MenuTabNav,
        MenuCartFooter
    }
}


</script>

<style scoped>

.TabNav-wrapper {

    margin-left: 2vw;
    margin-right: 2vw;
}

</style>