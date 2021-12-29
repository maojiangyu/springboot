<template>
<div>
  <el-container>
    <el-image :src="require('@/assets/commu.png')" style="opacity: 0.15;position: absolute;height: 450px;width:70%;margin-top: -1%"></el-image>
    <el-main>
      <div v-for="item in communityList">
        <div style="margin-top: 2%;border: 1px solid #000000;box-shadow:4px 4px 5px #999;border-radius: 10px;height: 200px" @click="goToCommunity(item)">
          <div style="margin-bottom: 2%;margin-top: 5%">
            <el-row :gutter="0" style="margin-left: 38%">
              <el-col :span="4" style="font-weight: bolder;font-size: larger"><div>社团名:</div></el-col>
              <el-col :span="6" style="font-size: larger;"><div>{{item.name}}</div></el-col>
            </el-row>
          </div>
          <div style="color: #b4b4b4;margin-top: 7%;font-size: 14px">
            于 {{item.joinTime}} 加入该社团
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</div>
</template>

<script>
export default {
  props:['communityList'],
  name: "CInformation",
  data(){
    return {
      show:false,
      leftBg: {
        background: " url(" + require("@/assets/commu.png") + ") no-repeat scroll 50% 0%",
        height:'500px',
        opacity:0.5
      },
      myCommunity:this.$store.state.myCommunity
    }
  },
  methods:{
    goToCommunity(item){
      if (item.isLeader==1){
        this.$store.commit('setCommunity',item.id)
        this.$router.push({path:'/Community/information',query: {list: item}})
      }else {
        this.$store.commit('setCommunity',item.id)
        this.$router.push({path:'/Community/myCommunity',query: {list: item}})
      }

    }
  },
  mounted() {
    this.myCommunity = this.$store.state.myCommunity
    if (this.myCommunity.length>0)
      this.show = true;
    else
      this.show = false
  }
}
</script>

<style scoped>
[v-cloak]{
  display: none;
}
.test{
  background: url("/src/assets/commu.png");

}
.test2 :hover{
  font-weight: bolder;
}
</style>
