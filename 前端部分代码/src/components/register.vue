<template>
  <div style="text-align: center">
    <h1 style="position: absolute;top: 10%;left:40%;z-index: 1;font-size: xx-large;color: black">学生社团管理系统</h1>
    <div :style="leftBg">
      <el-container>
        <el-header style="margin-bottom: 13%;">
        </el-header>
        <el-main >
          <div>
            <el-input v-model="name" placeholder="请输入学生编号" style="width: 40%"></el-input><br>
            <el-input v-model="password" placeholder="请输入密码" style="margin-top: 1%;width: 40%"></el-input><br>
            <el-input v-model="password2" placeholder="请再次输入密码" style="margin-top: 1%;width: 40%"></el-input><br>
            <div style="margin-top: 1%">
              <el-button>登录</el-button>
              <el-button>注册</el-button>
            </div>

          </div>
        </el-main>


      </el-container>
    </div>

  </div>
</template>

<script>
export default {
name: "register",
  data(){
    return {
      name:'',
      password:'',
      password2:'',
      admin:false,
      leftBg: {
        background: " url(" + require("../assets/commu.png") + ") no-repeat scroll 50% 0%",
        height:'500px',
        opacity:0.7
      },
      user:{}
    }
  },
  methods:{
    checkInfo(){
      if (this.name==''||this.password==''||this.password2=='')
        this.$message.error('用户名或者密码不可为空')
      else{
        if (this.password!=this.password2)
          this.$message.error('两次密码输入不相符')
        else {
          this.registerUser();
        }
      }
    },
    registerUser(){}
  }
}
</script>

<style scoped>

</style>
