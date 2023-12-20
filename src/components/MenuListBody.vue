<template>
  <div>
    <v-tabs v-model="currentTab" color="deep-purple-accent-4" align-tabs="center" grow>
      <v-tab :value="1">Landscape</v-tab>
      <v-tab :value="2">City</v-tab>
      <v-tab :value="3">Abstract</v-tab>
      <v-tab :value="4">Something</v-tab>
    </v-tabs>

    <div class="v-window-wrapper">
      <div class="loading-progress" v-if="isLoading">
        <v-progress-circular indeterminate></v-progress-circular>
      </div>
      <v-window v-if="!isLoading" v-model="currentTab">
        <v-window-item v-for="n in 4" :key="n" :value="n">
          <v-container fluid>
            <v-row>
              <v-col v-for="i in myData" :key="i" cols="4">
                <v-card class="menu-card" hover>
                  <v-responsive aspect-ratio="1">
                    <v-img aspect-ratio="1" @click="()=> MenuClicked(i)"
                      src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/McDonald%27s_Quarter_Pounder_with_Cheese%2C_United_States.jpg/800px-McDonald%27s_Quarter_Pounder_with_Cheese%2C_United_States.jpg?20210104193859">
                    </v-img>

                    <v-card-title class="mt-n12">
                      {{ i.mname }}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      {{ i.price }}
                    </v-card-title>

                  </v-responsive>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-window-item>
      </v-window>
    </div>
  </div>



  <v-dialog width="500">
  <template v-slot:activator="{ props }">
    <v-btn ref="modalbtn" v-bind="props" text="Open Dialog"> </v-btn>
  </template>

  <template v-slot:default="{ isActive }">
    <v-card title="Dialog">
      <v-card-text>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn
          text="Close Dialog"
          @click="isActive.value = false"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </template>
</v-dialog>
</template>


<script setup>
import { ref, computed} from 'vue';
import { defineProps, defineEmits } from 'vue';


const emit = defineEmits(['add-to-cart']);

let currentTab = ref(null);

// const myData = defineProps(['myData']);

// const isLoading = defineProps(['isLoading']);


const props = defineProps(['myData', 'isLoading']);


computed(() => { return props.isLoading });



function MenuClicked(i){
  const modalbtn = ref(null);
  console.log(modalbtn.value);
  emit('add-to-cart', i);
}



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
  height: 75vh;
  overflow: auto;
}

.menu-card-inner-text {}
</style>