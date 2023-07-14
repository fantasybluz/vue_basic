var app = new Vue({
    el: "#events",
    data:{
        counter: 0,
        counter1: 0,
        counter2: 0,
        counter3: 0,
        name: '',
        name2: '',
        confirmedName: '',
    },
    methods:{
        add: function(){
            this.counter2 = this.counter2+1;
        },
        reduce: function(){
            this.counter2--;
        },
        add3: function(num){
            this.counter3 = this.counter3 + num;
        },
        reduce3: function(num){
            this.counter3 = this.counter3 - num;
        },
        setName: function(event){
            this.name = event.target.value;
        },
        setName2: function(event, lastName){
            this.name2 = event.target.value + ''+lastName;
        },
        submitForm: function(event){
            alert('Summitted !')
        },
        confirmInput(){
            this.confirmedName = this.name2;
        }
    }
});
