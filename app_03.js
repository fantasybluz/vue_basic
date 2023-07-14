const app = Vue.createApp({
  data() {
    return {
      counter: 0,
      name: ''
    };
  },
  methods: {
    setName(event, lastName) {
      this.name = event.target.value;
    },
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
    outputFullname(){
      console.log('執行output');
      
      if ( this.name == '' ){
        return '';
      }
      return this.name + ' Huang';
    }
  },
  computed: {
    fullname(){
      console.log('執行fullname');
      
      if ( this.name == '' ){
        return '';
      }
      return this.name + ' Huang';
    }
  }
});

app.mount('#events');
