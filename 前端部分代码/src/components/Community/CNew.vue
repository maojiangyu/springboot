<template>
  <el-container>
    <el-main>
      <el-table
        :data="applyList"
        style="width: 100%"
        :default-sort = "{prop: 'createTime', order: 'descending'}">
        <el-table-column
          prop="createTime"
          label="日期"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="username"
          label="姓名"
          sortable
          width="180">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index,scope.row)">通过</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
export default {
name: "CNew",
  props:['communityID'],
  data(){
    return{
      applyList:[],
      Cid:this.communityID
    }
  },
  methods:{
    getApplyList(){
      this.applyList = []
      //console.log(this.Cid)
      var url = 'community/' + this.$store.state.whichCommunity+ '/apply'
      this.$axios.get(url).then(
        resp=>{
          const moment = require('moment')
          if (resp.data.length>0){
            for (let i = 0; i < resp.data.length;i++){
              var temp = {}
              temp.username = resp.data[i].user.sname;
              temp.sid = resp.data[i].user.id;
              var _date1 = moment(resp.data[i].createTime).format('YYYY-MM-DD mm:ss')
              temp.createTime = _date1;
              temp.applyId = resp.data[i].id
              this.applyList.push(temp)
            }
          }

          console.log(this.applyList)
        })
    },
    handleEdit(index, row) {
      //console.log(index, row);
      this.$confirm('确认通过申请吗', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/passApply',{
          sid:row.sid,
          cid:this.$store.state.whichCommunity,
          id:row.applyId
        })
        this.$message({
          type: 'success',
          message: '操作成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    handleDelete(index, row) {
      //console.log(index, row);
      var _id = row.applyId;
      this.$confirm('此操作将永久删除该申请, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/deleteApply',{
          id:_id
        }).then(successResponse=>{
          this.getApplyList()

        })
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getApplyList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  mounted() {
  this.getApplyList();
  }
}
</script>

<style scoped>
.test{
  background: url("/src/assets/commu.png");

}
</style>
