<template>
<div>
  <el-main>
    <el-table
      :data="announcementList.slice((currentPage-1)*pagesize,currentPage*pagesize)"
      style="width: 100%"
      :default-sort = "{prop: 'createTime', order: 'descending'}">
      <el-table-column
        prop="createTime"
        label="日期"
        sortable
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        sortable
        width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index,scope.row)">查看</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20, 40]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="announcementList.length">
    </el-pagination>
    <el-dialog
      title="公告详情"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="公告名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="公告内容" :label-width="formLabelWidth">
          <el-input v-model="form.describeA" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="EditAnnouncement">编辑</el-button>
    <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
  </span>
    </el-dialog>
  </el-main>
</div>
</template>

<script>
export default {
  name: "AInformation",
  data(){
    return{
      announcementList:[],
      dialogVisible:false,
      form:{
        id:1,
        name:'',
        describeA:'',
        oldName:'',
        oldDescribe:'',
        community:{}
      },
      input1:'q',
      currentPage:1, //初始页
      pagesize:5,    //    每页的数据
      formLabelWidth: '100px',
    }
  },
  methods:{
    searcherAnnouncement(){
      var _this = this
      var cid = this.$store.state.whichCommunity
      var url = 'community/' + cid + '/' + this.input1 + '/announcement'
      this.$axios.get(url).then(resp => {
        //console.log(resp);
        const moment = require('moment')
        if (resp && resp.status === 200) {
          console.log('test')
          console.log(resp)
        }
      })
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
    handleEdit(index, row) {
      this.dialogVisible =  true
      console.log(index, row);
      this.form.community = row.community;
      this.form.name = row.name;
      this.form.id = row.id;
      this.form.describeA = row.describeA;
      this.form.oldDescribe = row.describeA;
      this.form.oldName = row.name
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该公告, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/deleteAnnouncement',{
          id:row.id
        }).then(successResponse=>{
          console.log(successResponse)
          this.listByCommunity()
        })
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.listByCommunity()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    EditAnnouncement(){
      var _CID = this.$store.state.whichCommunity
      this.$confirm('确认重新发布公告?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
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
        this.$message({
          type: 'success',
          message: '编辑成功!'
        });
        this.listByCommunity()
      }).catch(() => {
        this.form.describeA = this.form.oldDescribe;
        this.form.name = this.form.oldName
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function (size) {
      this.pagesize = size;
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
    },
  },
  mounted() {
    this.searcherAnnouncement();
  }
}
</script>

<style scoped>

</style>
