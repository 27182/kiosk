<template>
    <v-card>
        <v-tabs v-model="currentTab" color="black" align-tabs="center" grow>
            <v-tab :value="1">메뉴 관리</v-tab>
            <v-tab :value="2">주문 관리</v-tab>
        </v-tabs>
        <v-window v-model="currentTab">
            <v-window-item v-for="n in 2" :key="n" :value="n">
                <v-container fluid>
                    <v-data-table :items="myData" :headers="headers">
                        <template v-for="header in headers.filter((header) => header.hasOwnProperty('formatter'))"
                            v-slot:[`items.${header.value}`]="{ header, value }">
                            {{ header.formatter(value) }}
                        </template>

                    </v-data-table>
                </v-container>
            </v-window-item>
        </v-window>
    </v-card>
</template>

<script setup>
import { ref, defineProps, computed } from 'vue'

let currentTab = ref(null)

const props = defineProps(['myData', 'isLoading']);


computed(() => { return props.isLoading });

const headers = [
    { title: '주문 번호', key: 'ono' },
    { title: '주문 내용', key: 'content' },
    { title: '가격(￦)', key: 'totalPrice' },
    { title: '주문 시각', key: 'regDate', formatter: formatDate },
]

function formatDate(value) {
    return value.replace('T', ' ').substring(0, 19);
}


</script>

<script>
export default {
    name: 'AdminMainBody',
}
</script>