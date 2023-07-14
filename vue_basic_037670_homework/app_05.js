const app = new Vue({
    el: "#user-goals",
    data: { 
        goals: [],
        enterGoalValue: '',
    },
    methods: {
      addGoal: function() {
        this.goals.push(this.enterGoalValue);
        console.log(this.goals);
      },
      removeGoal: function(idx) {
        this.goals.splice(idx,1);
        console.log(this.goals);
      }
    }
  });
  