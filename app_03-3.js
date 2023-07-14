const app = Vue.createApp({
  data() {
    return {
      counter: 0,
      name: '',
      fullname: '',
      lastname: ''
    };
  },
  methods: {
    add(num) {
      this.counter = this.counter + num;
    },
    reduce(num) {
      this.counter = this.counter - num;
      // this.counter--;
    },
    resetInput(){
      this.name = '';
    },
  },
  watch: {
      name(value){
          console.log('執行watch name')
          if ( this.name == '' ){
            this.fullname = '';
          }else {
            this.fullname = value + ' Huang';
          }
          
      },
      counter(value) {
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
app.mount('#events');
