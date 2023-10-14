<template>
  <div style=" font-size: 12px; line-height: 60px;display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 20px" @click="collapse"></span>

        <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px " >
          <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
          <el-breadcrumb-item >{{$route.name}}</el-breadcrumb-item>


        </el-breadcrumb>


    </div>
    <el-dropdown style=" cursor: pointer;text-align: right" >
      <div style="display: inline-block">
      <img :src="user.avatarUrl" alt=""
           style="width: 40px; border-radius: 50%; position: relative; top: 10px; right: 5px">
      <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
  </div>
<!--      <div>-->
<!--&lt;!&ndash;        <img :src="user">&ndash;&gt;-->
<!--        <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
<!--        <i class="el-icon-setting" style="margin-right: 15px"></i>-->

<!--      </div>-->

      <el-dropdown-menu slot="dropdown" style="width: 150px;text-align: center">
        <el-dropdown-item style="font-size: 14px;padding: 5px 0"><router-link to="/person">个人信息</router-link></el-dropdown-item>
        <el-dropdown-item style="font-size: 14px;padding: 5px 0"><router-link to="/password">修改密码</router-link></el-dropdown-item>
        <el-dropdown-item style="font-size: 14px;padding: 5px 0" ><router-link to="/front/home" v-show="user.role === 'ROLE_STUDENT'">返回首页</router-link></el-dropdown-item>
        <el-dropdown-item style="font-size: 14px;padding: 5px 0"><span style="text-decoration: none" @click="logout">退出</span></el-dropdown-item>

      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass:String,
    collapse: Function,
    // user: Object

  },

  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;
    }
  },
  watch:{
    '$router' :function (){
      this.currentPathName = localStorage.getItem("currentPathName")
    }
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

    }

  },
  created() {

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

</style>
