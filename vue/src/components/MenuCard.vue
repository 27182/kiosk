<template>
    <v-card height="300" image="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        title="Card title" theme="dark">
        <template v-slot:dataInfo>
            데이터 정보
        </template>
    </v-card>
</template>

<script>

export default {
    name: 'MenuCard',
    props: {

    },

}

</script>