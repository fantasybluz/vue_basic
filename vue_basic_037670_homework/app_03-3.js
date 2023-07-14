const app = new Vue({
    el: "#events",
    data:{
         counter: 0,
         name: '',
         fullname: '',
         lastname: '',
    },
    methods: {
      add: function(num) {
        this.counter = this.counter + num;
      },
      reduce: function(num) {
        this.counter = this.counter - num;
        // this.counter--;
      },
      resetInput: function(){
        this.name = '';
      }
    },
    watch: {
        name: function(value){
            console.log('執行watch name')
            if ( this.name == '' ){
              this.fullname = '';
            }else {
              this.fullname = value + ' Huang';
            }
            
        },
        counter: function(value) {
                if (value > 50) {
                this.counter = 0;
            }
        }
    }
});
  // method 可直接在頁面中獲取data或是event，但在每次的行為渲染中燈會被執行，適合使用在event-binding中。
  // computed 使用在單純獲取直接data，不會受其他data變化而被渲染。
  // watch 適合使用在具有因果關係的行為，如送出http請求，不適合單純使用在data-binding。
  // v-on:click = @click 
  // v-bind:value = :value
  // watch :監聽數據的具體變化並執行相應的操作，
  // Computed:用於計算和派生數據屬性，並提供一個緩存機制以優化性能。你可以根據具體的需求選擇使用其中之一或兩者結合使用。