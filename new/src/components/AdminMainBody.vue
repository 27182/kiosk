<template>
  <v-card>
    <v-tabs v-model="currentTab" color="black" align-tabs="center" grow>
      <v-tab :value="1">메뉴 관리</v-tab>
      <v-tab :value="2">주문 내역</v-tab>
    </v-tabs>
    <div class="loading-progress" v-if="isLoading">
        <v-progress-circular indeterminate></v-progress-circular>
      </div>
    <v-window v-if="!isLoading" v-model="currentTab">
      <v-window-item v-for="n in 2" :key="n" :value="n">
        <v-container fluid>
          <v-text-field v-model="search" label="Search" prepend-inner-icon="mdi-magnify" single-line variant="outlined"
            hide-details style="width: 70%; display: inline-block;"></v-text-field>

          <v-data-table v-show="n == 2" :items="myOrderData" :headers="orderHeaders" :search="search"
            :sort-by="[{ key: 'ono', order: 'desc' }]">
            <template v-slot:[`item.actions`]="{ item }">
              <v-icon v-if="false" size="small" class="me-2" @click="console.log(item)">
                mdi-pencil
              </v-icon>
              <v-icon size="small" @click="confirmDelete(item, 2)">
                mdi-delete
              </v-icon>
            </template>
          </v-data-table>
          <span>
            <v-data-table v-show="n == 1" :items="myMenuData" :headers="menuHeaders" :search="search">
              <template v-slot:[`item.actions`]="{ item }">
                <v-icon size="small" class="me-2" @click="showDialog1(item)">
                  mdi-pencil
                </v-icon>
                <v-icon size="small" @click="confirmDelete(item, 1)">
                  mdi-delete
                </v-icon>
              </template>
            </v-data-table>
            <v-btn v-if="n == 1" color="primary" size="large" dark class="mt-2" @click="showDialog2()">
              메뉴 추가
            </v-btn>
          </span>
        </v-container>
      </v-window-item>
    </v-window>

    <!-- ********************다이얼로그***********************    -->

    <v-dialog v-model="popup1" persistent>
      <v-form validate-on="submit lazy" @submit.prevent>

        <v-card width="768" style="margin: auto;">
          <v-card-text>
            <v-container class="dialog-container">
              <v-row>
                <v-col cols="4">
                  <v-text-field :rules="rules" v-model="cur_item.mname" label="이름" required></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field :rules="[...rules, ...priceRules]" v-model="cur_item.price" label="가격(￦)"
                    required></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-select :rules="rules" v-model="cur_item.mtype" :items="['main', 'side', 'drink']" label="타입"
                    required></v-select>
                </v-col>
                <v-col cols="4">
                  <v-select :rules="rules" v-model="cur_item.available" :items="['가능', '불가']" label="이용"
                    required></v-select>
                </v-col>
                <v-col cols="4">
                  <v-select :rules="rules" v-model="cur_item.recommanded" :items="['O', 'X']" label="추천"
                    required></v-select>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue-darken-1" variant="text" @click="closePopUp">
              Close
            </v-btn>
            <v-btn type="submit" color="blue-darken-1" variant="text" @click="updateMenu">
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-form>

    </v-dialog>

    <!-- **************************************************2****************************************************** -->
    <v-dialog v-model="popup2" persistent>
      <v-form validate-on="submit lazy" @submit.prevent>

        <v-card width="768" style="margin: auto;">
          <v-card-text>
            <v-container class="dialog-container">
              <v-row>
                <v-col cols="4">
                  <v-text-field v-model="new_item.mname" :rules="rules" label="이름" required></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field v-model="new_item.price" type="number" :rules="[...rules, ...priceRules]" label="가격(￦)"
                    required></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-select v-model="new_item.mtype" :rules="rules" :items="['main', 'side', 'drink']" label="타입"
                    required></v-select>
                </v-col>
                <v-col cols="4">
                  <v-select v-model="new_item.available" :rules="rules" :items="['가능', '불가']" label="이용"
                    required></v-select>
                </v-col>
                <v-col cols="4">
                  <v-select v-model="new_item.recommanded" :rules="rules" :items="['O', 'X']" label="추천"
                    required></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-file-input label="메뉴 사진" accept="image/jpg, image/jpeg, image/png, image/gif"></v-file-input>
              </v-row>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue-darken-1" variant="text" @click="closePopUp">
              Close
            </v-btn>
            <v-btn type="submit" color="blue-darken-1" variant="text" @click="insertMenu">
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-form>

    </v-dialog>


  </v-card>
  <admin-main-footer></admin-main-footer>
</template>

<script setup>
import axios from 'axios';
import { ref, defineProps, computed, onMounted, defineEmits } from 'vue'
import AdminMainFooter from './AdminMainFooter.vue';

onMounted(() => {
  new_item.value = {
    mname: "",
    mtype: "",
    imgurl: "",
    price: "",
    available: "",
    recommanded: "",
  };
});

let currentTab = ref(null)


const emit = defineEmits(['refresh']);





const props = defineProps(['myMenuData', 'myOrderData', 'isLoading']);


computed(() => { return props.isLoading });

const rules = [
  value => {
    if (value) return true;
      return 'invalid value';
  }]

const priceRules = [
  value => {
    if (!isNaN(value)) return true;
      return 'invalid value'
  
  }]



const orderHeaders = [
  { title: '주문 번호', key: 'ono', align: 'center' },
  { title: '주문 내용', key: 'content', align: 'center' },
  { title: '가격(￦)', key: 'totalPrice', align: 'center' },
  { title: '주문 시각', key: 'regDate', align: 'center' },
  { title: 'Actions', key: 'actions', sortable: false, align: 'center' }
]


const menuHeaders = [
  { title: '메뉴 번호', key: 'mno', align: 'center' },
  { title: '이름', key: 'mname', align: 'center' },
  { title: '가격(￦)', key: 'price', align: 'center' },
  { title: '타입', key: 'mtype', align: 'center' },
  { title: '이용', key: 'available', align: 'center' },
  { title: '추천', key: 'recommanded', align: 'center' },
  { title: 'Actions', key: 'actions', sortable: false }

]

let search = ref('');




let cur_item = ref(null);
let popup1 = ref(false);
let popup2 = ref(false);
let new_item = ref(null);

function closePopUp() {
  popup1.value = false;
  popup2.value = false;
  setTimeout(()=>{

    new_item.value = {
      mname: "",
      mtype: "",
      imgurl: "",
      price: 0,
      available: "",
      recommanded: "",
    };
  },300);
}

function showDialog1(item) {
  cur_item.value = item;
  popup1.value = true;

}

function showDialog2() {
  popup2.value = true;
}

function updateMenu() {
  if (
    cur_item.value.mname === ""
    || cur_item.value.mtype === ""
    || cur_item.value.recommanded === ""
    || cur_item.value.available0 === ""
    || cur_item.value.price === 0
    || isNaN(cur_item.value.price)

  ) {
    alert("FAIL!");
    console.log(new_item);
  } else {
  axios.post('/admin/menu/update', {
      'mno': cur_item.value.mno,
      'mname': cur_item.value.mname,
      'mtype': cur_item.value.mtype,
      'price': cur_item.value.price,
      'available': (cur_item.value.available === "가능" ? "1" : "0"),
      'recommanded': (cur_item.value.recommanded === "O" ? "1" : "0"),
    }).then(()=>{emit("refresh")});
    closePopUp();
  }

}

function insertMenu() {

  if (
    new_item.value.mname === ""
    || new_item.value.mtype === ""
    || new_item.value.recommanded === ""
    || new_item.value.available0 === ""
    || new_item.value.price === 0
    || isNaN(new_item.value.price)

  ) {
    alert("FAIL!");
  } else {
    axios.post('/admin/menu/update', {
      'mname': new_item.value.mname,
      'mtype': new_item.value.mtype,
      'price': new_item.value.price,
      'available': (new_item.value.available === "가능" ? "1" : "0"),
      'recommanded': (new_item.value.recommanded === "O" ? "1" : "0"),
      'imgurl': new_item.value.imgurl,
    }).then(()=>{emit("refresh")});
    closePopUp();
  }

}

function confirmDelete(item, i) {
  if (confirm('정말 삭제하시겠습니까?')) {
    if(i == 1 ){
      axios.delete('/admin/menu/' + item.mno, {}).then(()=>{emit("refresh")});

    } else if(i == 2){
      axios.delete('/admin/order/' + item.ono, {}).then(()=>{emit("refresh")});
    }
  }
}


</script>

<script>
export default {
  name: 'AdminMainBody',
}
</script>


<style scoped>

.loading-progress {
  height: 70vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>
