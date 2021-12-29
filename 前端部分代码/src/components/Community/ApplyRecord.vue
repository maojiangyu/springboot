<template>
<div style="text-align: center">
  <el-container>
    <el-main style="margin: auto">
      <div>
        <el-input v-model="input1" placeholder="学生名" style="width: 40%" clearable/>
        <el-button @click="searcherList" type="primary" icon="el-icon-search"><span>搜索</span></el-button>
        <el-button type="info" icon="el-icon-refresh" circle @click="getRecordList"></el-button>
      </div>
      <el-table
        :data="recordList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        size="medium"
        :default-sort = "{prop: 'createTime', order: 'descending'}">
        <el-table-column
          prop="createTime"
          label="处理日期"
          sortable>
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          sortable>
        </el-table-column>
        <el-table-column
          prop="situation"
          label="状态"
          sortable>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 40]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="recordList.length">
      </el-pagination>
    </el-main>
  </el-container>
</div>
</template>

<script>
export default {
name: "ApplyRecord",
  data(){
  return{
    recordList:[],
    currentPage:1, //初始页
    pagesize:5,    //    每页的数据
    input1:''
  }

  },
  methods:{
    getRecordList(){
      this.recordList = []
      var url = 'community/' + this.$store.state.whichCommunity+ '/getApplyRecord'
      this.$axios.get(url).then(reps=>{
        const moment = require('moment')
        for (let i = 0; i < reps.data.length;i++){
          var temp = {}
          temp.username = reps.data[i].user.sname;
          var _date1 = moment(reps.data[i].createTime).format('YYYY-MM-DD')
          temp.createTime = _date1;
          if (reps.data[i].pass==true)
            temp.situation = '已通过'
          else
            temp.situation = '未通过'
          this.recordList.push(temp)
        }
      })
    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function (size) {
      this.pagesize = size;
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
    },
    searcherList(){
      this.recordList = []
      if (this.input1==''){
        this.getRecordList()
      }else {
        this.currentPage = 1
        var url = 'community/' + this.$store.state.whichCommunity+'/'+this.input1+ '/searcherRecord'
        this.$axios.get(url).then(reps=>{
          const moment = require('moment')
          for (let i = 0; i < reps.data.length;i++){
            var temp = {}
            temp.username = reps.data[i].user.sname;
            var _date1 = moment(reps.data[i].createTime).format('YYYY-MM-DD')
            temp.createTime = _date1;
            if (reps.data[i].pass==true)
              temp.situation = '已通过'
            else
              temp.situation = '未通过'
            this.recordList.push(temp)
          }
        })
      }

    },
  },
  mounted() {
    this.getRecordList();
  }
}
</script>

<style scoped>

</style>
