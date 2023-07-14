const app = new Vue({
    el: "#events",
    data: {
        counter: 0,
        name: '',
    },
    methods:{
      setName: function(event, lastName) {
        this.name = event.target.value;
      },
      add: function(num) {
        this.counter = this.counter + num;
      },
      reduce: function(num) {
        this.counter = this.counter - num;
        // this.counter--;
      },
      resetInput: function(){
        this.name = '';
      },
      outputFullname: function(){
        console.log('執行output');
        
        if ( this.name == '' ){
          return '';
        }
        return this.name + ' Huang';
      }
    },
    computed: {
      fullname: function(){
        console.log('執行fullname');
        
        if ( this.name == '' ){
          return '';
        }
        return this.name + ' Huang';
        }
    }
});

  