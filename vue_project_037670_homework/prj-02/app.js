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
        attackMonster: function(){
            console.log('attackMonstser()');
            this.currentRound++;
            const attackValue = getRandomValue(5, 12);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
        },
        attackPlayer: function(){
            console.log('attackPlayer()');
            const attackValue = getRandomValue(8, 15);
            this.playerHealth -= attackValue;
        },
        specialAttackMonster: function() {
            console.log('specialAtacck()');
            this.currentRound++;
            const attackValue = getRandomValue(10, 25);
            this.monsterHealth -= attackValue;
            this.attackPlayer();
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