<template>
    <div>
        <AdminSideNav></AdminSideNav>
        <v-layout>    
            <RouterView></RouterView>
        
    </v-layout>
    </div>
</template>

<script>
import AdminSideNav from "@/components/AdminSideNav.vue"

export default {
    name: 'AdminPage',
    props: {
    },
    components: {
        AdminSideNav
    }

}


</script>