<template>
  <div>
    <el-container>
      <el-image :src="require('@/assets/commu.png')" style="opacity: 0.15;position: absolute;height: 450px;width:70%;margin-top: -1%;margin-left: 5%;z-index: -1"></el-image>
      <el-main>
        <div>
          <el-form :model="announcement">
            <el-form-item label="公告名" :label-width="formLabelWidth">
              <el-input v-model="announcement.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="公告内容" :label-width="formLabelWidth">
              <el-input v-model="announcement.describeA" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div style="margin-top: 5%">
            <el-button @click="clearAnnouncement">清除</el-button>
            <el-button @click="addAnno">发布</el-button>
          </div>
        </div>

      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "AddA",
  data(){
    return {
      announcement:{
        name:'',
        describeA:''
      },
      formLabelWidth: '100px',
    }
  },
  methods:{
    clearAnnouncement(){
      this.announcement = {
        name:'',
        describeA:''
      }
    },
    addAnno(){
      //console.log(this.announcement.name.length)
      if (this.announcement.name.length>0){
        this.$axios.post('/addAnnouncement',{
          CID:this.$store.state.whichCommunity,
          name:this.announcement.name,
          describeA:this.announcement.describeA
        }).then(reps=>{
          console.log(reps)
          if (reps.status==200){
            this.$message.success("发布成功")
            this.announcement = {
              name:'',
              describeA:''
            }
          }else {
            this.$message.error("发布失败")
          }
        })
      }else{
        this.$message.error("公告名不能为空")
      }

    }
  }
}
</script>

<style scoped>

</style>
