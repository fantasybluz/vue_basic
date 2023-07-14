const app = Vue.createApp({
  data() {
    return {
      counter: 0,
      counter1: 0,
      counter2: 0,
      counter3: 0,
      name: '',
      name2: '',
      confirmedName:  '',
    };
  },
  methods: {
    add() {
      this.counter2 = this.counter2 + 1;
    },
    reduce(){
      this.counter2--;
    },
    add3(num) {
      this.counter3 = this.counter3 + num;
    },
    reduce3(num){
      this.counter3 = this.counter3 - num;
    },
    setName(event){
      // 當input事件觸發時，會自動帶一個參數傳入，這裡命名為event
      this.name = event.target.value ;
    },
    setName2(event, lastname){
      this.name2 = event.target.value + ' '+ lastname;
    },
    submitForm(event){
      // event.preventDefault();
      alert('Submitted!')
    },
    confirmInput(){
      this.confirmedName = this.name2;
    }
  }
});

app.mount('#events');
