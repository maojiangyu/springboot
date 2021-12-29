<template>
  <div>
    <el-container style=" height:500px;border: 1px solid #5cbce0">
      <el-aside width="20%" style="background-color: rgb(151,168,198);">
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :unique-opened="true">
          <el-menu-item index="1" @click="changeShow(1)">
            <i class="el-icon-user-solid"></i>
            <span slot="title">我的同伴</span>
          </el-menu-item>
          <el-menu-item index="2" @click="changeShow(2)">
            <i class="el-icon-s-order"></i>
            <span slot="title">社团公告</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-main>
          <div v-show="show1">
                <el-table
                  :data="memberList"
                  style="width: 100%"
                  :default-sort = "{prop: 'createTime', order: 'descending'}">
                  <el-table-column
                    prop="createTime"
                    label="入社日期"
                    sortable
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="username"
                    label="姓名"
                    sortable>
                  </el-table-column>
                </el-table>
          </div>
          <div v-show="show2">
              <div style="margin-top: 2%;height: 150px;border: 1px solid #000000" v-for="item in announcementList">
                <el-row :gutter="0" style="margin-left: 38%;margin-top: 2%">
                  <el-col :span="4" style="font-weight: bolder;font-size: larger"><div>公告名:</div></el-col>
                  <el-col :span="6" style="font-size: larger;"><div>{{item.name}}</div></el-col>
                </el-row>
                <div style="margin-top: 2%">{{item.describeA}}</div>
                <div></div>
              </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
name: "normalC",
  data(){
    return{
      memberList:[],
      community:{},
      show1:true,
      show2:false,
      announcementList:[],
    }
  },
  methods:{
    handleOpen(key, keyPath) {
      //console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      //console.log(key, keyPath);
    },
    getMemberList(){
      this.memberList = []
      var url = 'community/' + this.$store.state.whichCommunity+ '/getMember'
      this.$axios.get(url).then(
        resp=>{
          const moment = require('moment')
          if (resp.data.length>0){
            for (let i = 0; i < resp.data.length;i++){
              var temp = {}
              if (resp.data[i].id!=this.$store.state.user.id){
                temp.username = resp.data[i].sname;
                temp.sid = resp.data[i].id;
                var _date1 = moment(resp.data[i].createTime).format('YYYY-MM-DD')
                temp.createTime = _date1;
                temp.applyId = resp.data[i].ucid
                this.memberList.push(temp)
              }

            }
          }

        })
    },
    changeShow(id){
      if (id==1){
        this.show2 = false
        this.show1 = true
      }else if (id==2){
        this.show1 = false
        this.show2 = true
      }
    },
    listByCommunity () {
      var _this = this
      var cid = this.$store.state.whichCommunity
      var url = 'community/' + cid + '/announcement'
      this.$axios.get(url).then(resp => {
        //console.log(resp);
        const moment = require('moment')
        if (resp && resp.status === 200) {
          _this.announcementList = resp.data
          for (let i = 0;i<_this.announcementList.length;i++){
            var _date1 = moment(_this.announcementList[i].createTime).format('YYYY-MM-DD mm:ss')
            _this.announcementList[i].createTime = _date1
          }
          //console.log(_this.announcementList)
        }
      })
    },
  },
  mounted() {
    this.community = this.$route.query.list
    this.getMemberList();
    this.listByCommunity();
  }
}
</script>

<style scoped>

</style>
