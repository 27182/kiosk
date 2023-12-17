<template>
    <div style="position: fixed; top: 0; width: 100%; z-index: 1; background-color: white;">
        <v-tabs v-model="tab" bg-color="transparent" color="basil" grow fixed>
            <v-tab style="font-size: larger;" v-for="item in items" :key="item" :value="item">
                {{ item }}
            </v-tab>
        </v-tabs>
    </div>
        

    <div>
        <v-window v-model="tab" style="margin-top: 5vh;">
            <v-window-item v-for="item in items" :key="item" :value="item">
                <v-container fluid>
                    <v-row cols="12">
                        <v-col v-for="i in 20" :key="i" cols="4">
                            <MenuCard></MenuCard>
                        </v-col>
                    </v-row>
                </v-container>
            </v-window-item>
        </v-window>
    </div>
    </template>

<script>
import MenuCard from '@/components/MenuCard.vue'
export default {
    name: 'MenuTabNav',
    props: {
    },
    data() {
        return {
            tab: 'Appetizers',
            items: [
                '추천 메뉴', '메인 메뉴', '사이드 메뉴', '음료',
            ],
            text: '내                용'
        }
    },

    components: {
        MenuCard
    }
}
</script>