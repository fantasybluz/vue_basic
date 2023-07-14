var app = new Vue({
        el: '#app',
        data: {
          courseGoal: '學習Vue內容',
          courseGoalA: '學習建立Vue內容',
          courseGoalB: '大師級Vue應用',
          vueLink: 'https://vuejs.org/',
          vueHTML: '<a href="https://vuejs.org/">Vue.js</a>'
        },
        methods: {
          outputGoalPre: function() {
            return this.courseGoalA;
          },
          outputGoal: function() {
            var vm = this;
            const randomNumber = Math.random();
            if (randomNumber < 0.5) {
                return vm.courseGoalA;
            } else {
                return vm.courseGoalB;
            }
        }
        }
    });
