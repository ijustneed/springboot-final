<template>
  <div>
    <div style="display: flex;height: 60px;line-height: 60px;border-bottom: 1px solid #ccc">
      <div style="width: 300px;text-align: center;display: flex;padding-left: 30px">
        <div style="width: 60px">
          <img src="../../assets/img_1.png" alt="" style="width: 50px;position: relative;top: 5px;right: 0" >
        </div>
        <div style="flex: 1;padding-right: 60px">欢迎来到我的系统</div>

      </div>
      <div style="flex: 1">
        <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/front/home">首页</el-menu-item>
          <el-menu-item index="/front/video">视频播放</el-menu-item>
          <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <el-menu-item  @click="$router.push('/front/map')">查看学校位置</el-menu-item>

          </el-submenu>
          <el-menu-item  @click="$router.push('/course')">选课系统</el-menu-item>

        </el-menu>
      </div>
      <div style="width: 200px">
        <div v-if="!user.username" style="text-align: right;padding-right: 30px">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style=" cursor: pointer;text-align: right" >
            <div style="display: inline-block">
              <img :src="user.avatarUrl" alt=""
                   style="width: 40px; border-radius: 50%; position: relative; top: 10px; right: 5px">
              <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 150px;text-align: center">
              <el-dropdown-item style="font-size: 14px;padding: 5px 0"><router-link to="/person">个人信息</router-link></el-dropdown-item>
              <el-dropdown-item style="font-size: 14px;padding: 5px 0"><span style="text-decoration: none" @click="logout">退出</span></el-dropdown-item>

            </el-dropdown-menu>
          </el-dropdown>
        </div>

      </div>

    </div>
    <div style=" margin: 0 auto;" >
      <router-view />
    </div>
  </div>


</template>

<script>
export default {
  name: "Front",
  data(){
  return{
    user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

  }
  },
  methods:{
    logout() {
      this.$store.commit("logout")
      this.$message.success("退出成功")
      // this.$router.push("/login")
      // localStorage.removeItem("user")
      // this.$message.success("退出成功")
    }
  }

}
</script>

<style scoped>
.item{
  display: inline-block;
  width: 100px;
  color: #1E90FF;
  cursor: pointer;
  align-items: center;
}
.item:hover{
  background-color: LightPink;
}
.item a{
  color: #1E90FF;
}
</style>
