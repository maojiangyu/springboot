<template>
<div>
  <el-container style=" height:500px;border: 1px solid #5cbce0">
    <el-aside width="20%" style="background-color: rgb(248,248,248);">
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        :unique-opened="true">
        <div>
          <el-menu-item index="1" @click="setActivityKey(1)">
            <template slot="title">
              <i class="el-icon-school"></i>
              <span>我的社团</span>
            </template>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-date"></i>
              <span>校园活动</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="setActivityKey(2)">活动列表</el-menu-item>
              <el-menu-item index="2-2" @click="setActivityKey(3)">活动报名</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>个人中心</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="3-1">信息修改</el-menu-item>
              <el-menu-item index="3-2">创建社团</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </div>

      </el-menu>
    </el-aside>

    <el-container>
      <el-main>
        <div v-cloak>
          <div v-show="show===1">
            <c-information ref="CInfo" v-bind:community-list="myCommunity"></c-information>
          </div>
          <div v-show="show===2">
          </div>
          <div v-show="show===3">
          </div>
        </div>

      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<script>
import CInformation from "./Community/CInformation";
export default {
  name: "CommunityIndex",
  components: {CInformation},
  data(){
    return {
      show:1,
      show1:true,
      show2:false,
      show3:false,
      activityKey:1,
      community:[],
      myCommunity:[],
      announcement:{
        name:'',
        describeA:''
      },
      labelPosition:'right',
      announcementList:[],
      dialogVisible:false,
      form:{
        id:1,
        name:'',
        describeA:'',
        community:{}
      },
      formLabelWidth: '100px',
    }
  },
  methods:{
    handleOpen(key, keyPath) {
      //console.log(key, keyPath);
      // if (key==1){
      //   this.show = 1
      // }
      // else if (key==2){
      //   this.show = 2
      // }else if (key==3){
      //   this.show = 3
      // }
      // console.log(typeof this.show)
      // console.log(this.show)
      // console.log(typeof 3)
    },
    setActivityKey(val){
      this.show = val
    },
    handleClose(key, keyPath) {
      //console.log(key, keyPath);
    },
    listByCommunity () {
      var _this = this
      var cid = this.community
      var url = 'community/' + cid + '/announcement'
      this.$axios.get(url).then(resp => {
        //console.log(resp);
        if (resp && resp.status === 200) {
          _this.announcementList = resp.data
          //console.log(_this.announcementList)
        }
      })
    },
    handleClose2(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    EditAnnouncement(){
      var _CID = this.form.community.id;
      this.$axios.post('/editAnnouncement',{
        name:this.form.name,
        describeA:this.form.describeA,
        id:this.form.id,
        CID:_CID
      }).then(successResponse=>{
        //console.log(successResponse)
        this.listByCommunity()
        this.dialogVisible = false;
      })
    },
    getCommunity(){
      var url = 'community/' + this.$store.state.user.id + '/community'
      this.$axios.get(url)
        .then(resp => {
          //console.log(resp)
          if (resp && resp.status === 200) {
            const moment = require('moment')
            for (let i = 0; i < resp.data.length;i++){
              for (let j = 0;j<this.community.length;j++){
                if (this.community[j].id==resp.data[i].community.id){
                  this.community[j].id = resp.data[i].community.id
                  this.community[j].add = true;
                  this.community[j].isLeader = resp.data[i].leader;
                  var _date1 = moment(resp.data[i].createTime).format('YYYY-MM-DD')
                  this.community[j].joinTime = _date1;
                  this.community[j].ucid = resp.data[i].id
                  this.myCommunity.push(this.community[j])
                  break;
                }
              }

            }
            this.$store.commit('getMyCommunty',this.myCommunity)
            //console.log(this.$store.state.myCommunity)
          }
        })
    },
    NewCommunity(){
      this.$axios.get('/communityAll')
        .then(resp => {
          if (resp && resp.status === 200) {
            const moment = require('moment')
            for (let i = 0; i < resp.data.length; i ++){
              var temp = {
                id:0,
                name:'',
                teacher:'',
                createTime:'',
                describeC:'',
                isLeader:false,
                add:false,
              }
              temp.id = resp.data[i].id;
              temp.name = resp.data[i].name;
              temp.teacher = resp.data[i].teacher;
              var _date1 = moment(resp.data[i].createTime).format('YYYY-MM-DD')
              temp.createTime = _date1;
              temp.describeC = resp.data[i].describeC;
              this.community.push(temp);

            }
          }
        })
    },
  },
  mounted() {
    this.NewCommunity();
    this.getCommunity();

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
