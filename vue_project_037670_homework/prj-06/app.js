var app = new Vue({
    el: '#game',
    data:{
        // 角色數值初始化        
        playerHealth:100,
        monsterHealth:100,
        currentRound:0,
        winner: null,
        logMessages:[]
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
          //當回合數不是3的倍數時，disable爆擊按鈕
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
    },
    methods:{
        // 注意玩家攻擊後會觸動怪獸攻擊，加入 this.attackPlayer();
        // 攻擊怪獸method
        startGame: function(){
            this.playerHealth=100,
            this.monsterHealth=100,
            this.currentRound=0,
            this.winner= null,
            this.logMessages = []
        },
        attackMonster: function(){
            //console.log('attackMonstser()');
            this.currentRound++;
            const attackValue = getRandomValue(5, 12);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
            this.addLogMessage('player', 'attack', attackValue);
        },
        // 攻擊玩家method
        attackPlayer: function(){
            //console.log('attackPlayer()');
            const attackValue = getRandomValue(8, 15);
            this.playerHealth -= attackValue;
            this.addLogMessage('monster', 'attack', attackValue);
        },
        // 玩家對怪獸爆擊
        specialAttackMonster: function() {
            //console.log('specialAtacck()');
            this.currentRound++;
            const attackValue = getRandomValue(10, 25);
            this.monsterHealth -= attackValue;
            this.addLogMessage('player', 'attack', attackValue);
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
            this.addLogMessage('player', 'attack', healValue);
            this.attackPlayer();
          },
            surrender: function(){
                this.winner='monster';
            //console.log(this.winner);
        },
        // prj-06 addLogMessage(who,what,value)
        addLogMessage: function(who, what, value){
            this.logMessages.unshift({
                actionBy: who,
                actionType: what,
                actionValue:value
            });
        },
    },
});

//設定亂數method
function getRandomValue(min,max){
    return Math.floor(Math.random()*(max-min))+min;
}