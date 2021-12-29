<template>
  <div>
    <el-container style=" height:500px;border: 1px solid #07b0f8">
      <el-aside width="20%" style="background-color: rgb(151,168,198);">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :unique-opened="true">
            <el-submenu index="1">
              <template slot="title" >
                <i class="el-icon-school"></i>
                <span>社团管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1" @click="changeKey(1)">社团招新</el-menu-item>
                <el-menu-item index="1-4" @click="changeKey(6)">申请记录</el-menu-item>
                <el-menu-item index="1-2" @click="changeKey(2)">社团资料更新</el-menu-item>
                <el-menu-item index="1-3" @click="changeKey(3)">社员管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title" >
                <i class="el-icon-tickets"></i>
                <span>公告管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="2-1" @click="changeKey(4)">公告列表</el-menu-item>
                <el-menu-item index="2-2" @click="changeKey(5)">发布公告</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <div v-cloak>
            <div v-show="show===1">
              <c-new :community-i-d="community.id"></c-new>
            </div>
            <div v-show="show===2">
              <el-container>
                <el-image :src="require('@/assets/commu.png')" style="opacity: 0.15;position: absolute;height: 450px;width:70%;margin-top: -1%;margin-left: 2%;z-index: -1"></el-image>
                <el-main>
                  <div style="font-size: larger">
                    <div>社团名： {{communityInfo.name}}</div>
                    <div style="margin-top: 2%">社团简介： {{communityInfo.describeC}}</div>
                    <div style="margin-top: 2%">负责老师： {{communityInfo.teacher}}</div>
                  </div>
                  <div style="margin-top: 10%">该社团创建于{{communityInfo.createTime}}</div>
                  <div style="margin-top: 10%">
                    <el-button>修改简介</el-button>
                    <el-button type="danger"><span>删除社团</span></el-button>
                  </div>
                  <el-dialog></el-dialog>
                </el-main>
              </el-container>
            </div>
            <div v-show="show===3">
              <c-member></c-member>
            </div>
            <div v-show="show===4">
              <a-information ref="AList"></a-information>
            </div>
            <div v-show="show===5">
              <add-a></add-a>
            </div>
            <div v-show="show===6">
              <apply-record></apply-record>
            </div>
          </div>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CNew from "./CNew";
import CMember from "./CMember";
import AInformation from "../Announcement/AInformation";
import AddA from "../Announcement/AddA";
import ApplyRecord from "./ApplyRecord";
export default {
name: "CIndex",
  components: {ApplyRecord, AddA, AInformation, CMember, CNew},
  data(){
    return {
      community:{},
      show:1,
      communityInfo:{}
    }
  },
  methods:{
    handleOpen(key, keyPath) {
      //console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      //console.log(key, keyPath);
    },
    changeKey(n){
      if (n==1){
        this.show=1;
      }else if (n==2){
        this.show = 2
      }else if (n==3){
        this.show = 3
      }else if (n==4){
        this.show = 4;
        this.$refs.AList.listByCommunity();
      }else if (n==5){
        this.show = 5
      }else if (n==6){
        this.show = 6
      }
    },
    getCInfo(){
      var url = 'community/' + this.$store.state.whichCommunity+ '/getCInfo'
      this.$axios.get(url).then(
        reps=>{
          const moment = require('moment')
          this.communityInfo.id = reps.data.id;
          this.communityInfo.name = reps.data.name;
          this.communityInfo.createTime = moment(reps.data.createTime).format('YYYY-MM-DD')
          this.communityInfo.teacher = reps.data.teacher
          this.communityInfo.describeC = reps.data.describeC
        }
      )
    },
    setCDescribe(){

    }
  },
  mounted() {
    this.community = this.$route.query.list
    this.getCInfo()
  }
}
</script>

<style scoped>
[v-clock]{
  display: none;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

</style>
