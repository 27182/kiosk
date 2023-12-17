<template>
    <div>
        <v-card>
            <v-layout>
                <v-navigation-drawer permanent rounded>
                    <v-list>
                        <v-list-item title="Admin Page" disabled></v-list-item>
                    <v-divider thickness="3px"></v-divider>
                    <br>
                    <br>
                    <router-link to="/admin/menu">
                        <v-list-item title="Menu Management" value="menu"></v-list-item>
                    </router-link>
                    <v-divider></v-divider>
                    <router-link to="/admin/order">
                        <v-list-item  title="Order Management" value="order" ></v-list-item>
                    </router-link>
                </v-list>
                
                <template v-slot:append>
                    <div class="pa-2">
                        <router-link to="/">
                            <v-btn class="bg-grey" block>
                                처음으로
                            </v-btn>
                        </router-link>
                    </div>
                </template>
            </v-navigation-drawer>
        </v-layout>
    </v-card>
</div>
</template>


<script>
export default {
    name: 'AdminSideNav',
    props: {
    }
}
</script>