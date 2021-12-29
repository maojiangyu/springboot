this.$axios.post('/login',{
  SSID:this.name,
  pwd:this.password
}).then(successResponse=>{
  if (successResponse.data.isUser==true){
    user.id = successResponse.data.id;
    user.ssid = successResponse.data.ssid;
  }else {
    window.alert("错误")
  }

})
