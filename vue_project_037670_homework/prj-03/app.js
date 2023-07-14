var app = new Vue({
    el: '#game',
    data:{
        // 角色數值初始化        
        playerHealth:100,
        monsterHealth:100,
        currentRoud:0
    },
    methods:{
        // 注意玩家攻擊後會觸動怪獸攻擊，加入 this.attackPlayer();
        // 攻擊怪獸method
        attackMonster: function(){
            console.log('attackMonstser()');
            this.currentRound++;
            const attackValue = getRandomValue(5, 12);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
        },
        // 攻擊玩家method
        attackPlayer: function(){
            console.log('attackPlayer()');
            const attackValue = getRandomValue(8, 15);
            this.playerHealth -= attackValue;
        },
        // 玩家對怪獸爆擊
        specialAttackMonster: function() {
            console.log('specialAtacck()');
            this.currentRound++;
            const attackValue = getRandomValue(10, 25);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
        },
        //加入 healPlayer 功能，設置修復範圍。可能會超過100%，所以加入判斷條件，超過 100 就會等於 100。
        healPlayer: function(){
            console.log('specialAtacck()');
            const healValue = getRandomValue(30,80);
            if(this.playerHealth+healValue >=100){
                this.playerHealth = 100;
            }else{
                this.healPlayer += healValue;
            }
        } 
    },
    computed:{
        monsterBarStyles: function() {
            return { width: this.monsterHealth + '%' };
        },
        playerBarStyles: function() {
            return { width: this.playerHealth + '%' };
        },
        mayUseSpecialAttack: function() {
            return this.currentRound % 3 !== 0;
        }
    }

});

//設定亂數method
function getRandomValue(min,max){
    return Math.floor(Math.random()*(max-min))+min;
}