var app = new Vue({
    el: '#game',
    data:{
        // 角色數值初始化        
        playerHealth:100,
        monsterHealth:100,
        currentRound:0,
        winner: null
    },
    methods:{
        // 注意玩家攻擊後會觸動怪獸攻擊，加入 this.attackPlayer();
        // 攻擊怪獸method
        startGame: function(){
            this.playerHealth=100,
            this.monsterHealth=100,
            this.currentRound=0,
            this.winner= null
        },
        attackMonster: function(){
            //console.log('attackMonstser()');
            this.currentRound++;
            const attackValue = getRandomValue(5, 12);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
        },
        // 攻擊玩家method
        attackPlayer: function(){
            //console.log('attackPlayer()');
            const attackValue = getRandomValue(8, 15);
            this.playerHealth -= attackValue;
            console.log('the winner:'+ this.winner);
        },
        // 玩家對怪獸爆擊
        specialAttackMonster: function() {
            //console.log('specialAtacck()');
            this.currentRound++;
            const attackValue = getRandomValue(10, 25);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
        },
        //加入 healPlayer 功能，設置修復範圍。可能會超過100%，所以加入判斷條件，超過 100 就會等於 100。
        healPlayer: function() {
            this.currentRound++;
            const healValue = getRandomValue(8, 20);
            if (this.playerHealth + healValue > 100) {
              this.playerHealth = 100;
            } else {
              this.playerHealth += healValue;
            }
            this.attackPlayer();
          },
        surrender: function(){
            this.winner='monster';
            console.log(this.winner);
        }
    },
    // computed具有"暫存"的特性，其更新條件為"原始資料"變更時，才會進行作動。
    computed:{
        monsterBarStyles() {
            if(this.monsterHealth<0){
                return{ width: '0%'};
            }
            return { width: this.monsterHealth + '%' };
          },
          playerBarStyles() {
            if(this.playerHealth<0){
                return{ width: '0%'};
            }
            return { width: this.playerHealth + '%' };
          },
          mayUseSpecialAttack() {
            return this.currentRound % 3 !== 0;
          },
    },
    //透過watch不斷監視playerHealth、monster的數值，進而更新this.winner的數值。
    watch: {
        playerHealth(value){
            if(value <= 0 && this.monsterHealth <= 0){
                this.winner = 'draw';
            }else if (value <=0)
            {
                this.winner = 'monster';
                console.log(this.playerHealth);
            }
        },
        monsterHealth(value){
            if(value <= 0){
                this.winner = 'player';
                console.log(this.monsterHealth);
            }
        }
    }

});

//設定亂數method
function getRandomValue(min,max){
    return Math.floor(Math.random()*(max-min))+min;
}