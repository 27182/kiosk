<template>
  <div>
    <v-tabs v-model="currentTab" color="black" align-tabs="center" grow>
      <v-tab :value="1">추천 메뉴</v-tab>
      <v-tab :value="2">메인 메뉴</v-tab>
      <v-tab :value="3">사이드 메뉴</v-tab>
      <v-tab :value="4">음료</v-tab>
    </v-tabs>

    <div class="v-window-wrapper">
      <div class="loading-progress" v-if="isLoading">
        <v-progress-circular indeterminate></v-progress-circular>
      </div>
      <v-window v-if="!isLoading" v-model="currentTab">
        <v-window-item v-for="n in 4" :key="n" :value="n">
          <span v-show="n == 1">
            <v-container fluid>
              <v-row>
                <v-col v-for="i in myData" :key="i" cols="4" v-show="i.recommanded === '1'">
                  <v-card class="menu-card" hover @click="() => MenuClicked(i)">
                    <v-responsive aspect-ratio="1">
                      <v-img aspect-ratio="1" 
                        :src="getImageSource(i.imgurl)">
                      </v-img>
                      
                      <v-card-title class="mt-n12" style="width: 100%;">
                        {{ i.mname }}
                      </v-card-title>
                      <div>
                        &nbsp;
                      </div>
                      <v-card-title class="mt-n12">
                        ￦{{ i.price.toLocaleString() }}
                      </v-card-title>
                      
                    </v-responsive>
                  </v-card>
                  
                </v-col>
              </v-row>
            </v-container>

          </span>


          <span v-show="n !== 1">
            <v-container fluid>
              <v-row>
                <v-col v-for="i in myData" :key="i" cols="4" v-show="(i.mtype === 'main' && n === 2) || (i.mtype === 'side' && n === 3) || (i.mtype === 'drink' && n === 4)">
                  <v-card class="menu-card" hover @click="() => MenuClicked(i)">
                    <v-responsive aspect-ratio="1">
                      <v-img aspect-ratio="1" 
                        :src="getImageSource(i.imgurl)">
                      </v-img>
                      
                      <v-card-title class="mt-n12" style="width: 100%;">
                        {{ i.mname }}
                      </v-card-title>
                      <div>
                        &nbsp;
                      </div>
                      <v-card-title class="mt-n12">
                        ￦{{ i.price.toLocaleString() }}
                      </v-card-title>
                      
                    </v-responsive>
                  </v-card>
                  
                </v-col>
              </v-row>
            </v-container>
          </span>
          </v-window-item>
        </v-window>
      </div>
    </div>
    
    <v-dialog v-model="isActive" width="500">
      
      <v-card height="250" style="align-items: center;">
        <v-card-title>수량 선택</v-card-title>
        
        <div class="mt-8">
        <div style="display: inline;">
          <v-btn class="mr-3" @click="decr" style="font-size: larger;"  text="▼" variant="flat"></v-btn>       
          <span  style="font-size: xx-large;"> {{ amount }} </span>
          <v-btn class="ml-3" @click="incr" style="font-size: larger;" text="▲" variant="flat"></v-btn>       
        </div>
        </div>
        
        
        <v-card-actions class="mt-12">
          <v-btn color="#2196F3" size="x-large" text="확인" variant="flat" class="mr-5" @click="()=>addCart(currentMenu)">
          </v-btn>
          <v-spacer></v-spacer>
        <v-btn size="x-large" class="ml-5" color="#2196F3" text="취소" @click="isActive = false" variant="flat"></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>


<script setup>
import { ref, computed } from 'vue';
import { defineProps, defineEmits, defineExpose } from 'vue';
import axios from "axios";


const emit = defineEmits(['add-to-cart']);

let currentTab = ref(null);

// const myData = defineProps(['myData']);

// const isLoading = defineProps(['isLoading']);


const props = defineProps(['myData', 'isLoading']);


computed(() => { return props.isLoading });




let isActive = ref(false);

let amount = ref(1);

let currentMenu = ref(null);

let imageSource = ref("");
function getImageSource(i){
    return imageSource.value + i;
}

function getImagePath(){
  axios.get("/api/image").then((a)=>{
    imageSource.value = a.data.imagesource;
  })
}

getImagePath();


function MenuClicked(i) {
  isActive.value = true;
  currentMenu.value = i;
  
}

function addCart(i) {
  isActive.value = false;
  emit('add-to-cart', i, amount.value);
  setTimeout(() => amount.value = 1,200);
  setTimeout(() => currentMenu.value = null,200);
}

function incr(){
    amount.value++;
}

function decr() {
  if(amount.value > 1){
    amount.value--;
  }
}


defineExpose({
  MenuClicked
})

</script>

<script>

export default {
  name: 'MenuListBody',
}

</script>


<style scoped>
.loading-progress {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.v-window-wrapper {
  margin-top: 5vh;
  height: 72vh;
  overflow: auto;
}

.menu-card-inner-text {}
</style>