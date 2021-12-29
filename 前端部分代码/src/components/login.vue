<template>
<div style="text-align: center">
  <h1 style="position: absolute;top: 10%;left:40%;z-index: 1;font-size: xx-large;color: black">学生社团管理系统</h1>
  <div :style="leftBg">
    <el-container>
      <el-header style="margin-bottom: 13%;">
      </el-header>
      <el-main >
        <div>
          <el-input v-model="name" placeholder="学生编号" style="width: 40%"></el-input><br>
          <el-input v-model="password" placeholder="请输入密码" style="margin-top: 1%;width: 40%"></el-input><br>
          <div style="margin-top: 1%">
            <el-button @click="checkUser">登录</el-button>
            <el-button @click="ToRegister">注册</el-button>
          </div>

        </div>
      </el-main>


    </el-container>
  </div>

</div>
</template>

<script>
export default {
name: "login",
  data(){
    return {
      name:'',
      password:'',
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
    checkUser(){
      /*
      通过认证发布权限。分配权限并且装载在main.js中的user中
       */
      if (this.name=='1'&&this.password=='1'){
        var user = {
          id:1,
          join:true,
          name:'1',
          community:1,
          isLeader:true
        }
        this.$store.commit('setUser',user)
        this.loginIn()
      }else if (this.name=='2'&&this.password=='2'){
        var user = {
          id:2,
          join:true,
          name:'2',
          community:1,
          isLeader:false
        }
        this.$store.commit('setUser',user)
        this.loginIn()
      }else if (this.name=='3'&&this.password=='3'){
        var user = {
          id:3,
          join:false,
          name:'3',
          community:1,
          isLeader:false
        }
        this.$store.commit('setUser',user)
        this.loginIn()
      }else {
        this.$message.error('用户不正确')
      }
    },
    loginIn(){
      if (this.$store.state.user.join==false){
        this.$store.commit('getKey',1);
        this.$router.replace({path:'/community'})
      }else if (this.$store.state.user.isLeader==false){
        this.$store.commit('getKey',2)
        this.$router.replace({path:'/community'})
      }else {
        this.$store.commit('getKey',3)
        this.$router.replace({path:'/community'})
      }
      console.log(this.$store.state.key)
    },
    ToRegister(){
      this.$router.push({path:'/register'})
    }
  }
}
</script>

<style scoped>

</style>
