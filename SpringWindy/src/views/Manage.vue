<template>

    <el-container style="min-height: 100vh" >
      <el-aside :width="sideWith+'px'" style="background-color: rgb(238, 241, 246);" >
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
      </el-aside>

      <el-container>

        <el-header style="  border-bottom:  1px solid #ccc"  >
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user"/>

        </el-header>

        <el-main>
                <router-view />
        </el-main>
      </el-container>
    </el-container>

</template>

<script>
// @ is an alias to /src



import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  components: {Header, Aside},
  data(){

    return {

      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWith:200,
      logoTextShow: true,
      user:  {},
      multipleSelection:[]

    }

  },
  created() {
    this.getUser()
  },
  // created() {
  //   this.load()
  //
  // },

  methods:{
    collapse() {
      this.isCollapse = !this.isCollapse
      if(this.isCollapse) {
        this.sideWith = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }else {
        this.sideWith = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }

    },
    getUser() {
      let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
      if (username) {
        // 从后台获取User数据
        this.request.get("/user/username/" + username).then(res => {
          // 重新赋值后台的最新User数据
          this.user = res.data
        })
      }
    }
    // getUser() {
    //   let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
    //   this.request.get("/user/username/" + this.user.username).then(res=>{
    //     this.user = res.data
    //
    //   })
    // }

  }

}
</script>
<style>

</style>
