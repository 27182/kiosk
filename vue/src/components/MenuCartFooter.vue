<template>
    <div style="position: fixed; bottom: 0; width: 100vw; background-color: white;">

 <v-container style="">
            <v-row justify="space-between">
                
                <div>

                    <v-chip>카트 &nbsp;&nbsp;{{ count }}개</v-chip>
                    <router-link to="/">
                            <v-btn class="bg-grey" block style="margin-top: 10vh;">
                                처음으로
                            </v-btn>
                        </router-link>
                </div>
      
                <v-btn color="indigo-darken-3" variant="elevated" height="172"
                min-width="164">결제하기</v-btn>
            </v-row>
        </v-container>
        
    </div>

</template>

<script>
export default {
    name: 'MenuCartFooter',
    props: {
        
    },
    data(){
        return {
            count : 0,
        }
    }
}

</script>
