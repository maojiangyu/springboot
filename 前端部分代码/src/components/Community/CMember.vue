<template>
  <el-container>
    <el-main>
      <el-table
        :data="memberList"
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
              @click="handleEdit(scope.$index,scope.row)">转让</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">踢出</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
export default {
name: "CMember",
  data(){
    return{
      memberList:[]
    }
  },
  methods:{
    getMemberList(){
      this.memberList = []
      var url = 'community/' + this.$store.state.whichCommunity+ '/getMember'
      this.$axios.get(url).then(
        resp=>{
          const moment = require('moment')
          if (resp.data.length>0){
            for (let i = 0; i < resp.data.length;i++){
              if (resp.data[i].id!=this.$store.state.user.id){
                var temp = {}
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
    handleEdit(index, row) {
      var _id = row.applyId;
      this.$confirm('请再次确认是否转让社长', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ucid = 0;
        for (let i = 0; i < this.$store.state.myCommunity.length;i++){
          if (this.$store.state.myCommunity[i].id==this.$store.state.whichCommunity){
            ucid = this.$store.state.myCommunity[i].ucid;
            this.$store.state.myCommunity[i].isLeader = false;
            break;
          }
        }
        this.$axios.post('/changeLeader',{
          id:row.applyId,
          myID:ucid
        })
        this.$message({
          type: 'success',
          message: '转让成功!'
        });

        this.$router.replace({path:'/community'})
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleDelete(index, row) {
      var _id = row.applyId;
      this.$confirm('此操作将踢出成员, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/deleteMember',{
          id:row.applyId
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
    this.getMemberList();
  }
}
</script>

<style scoped>

</style>
