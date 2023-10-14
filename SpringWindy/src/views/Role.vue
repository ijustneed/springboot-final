<template>
  <div>



    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称"  suffix-icon="el-icon-search" class="ml-5" v-model="name"></el-input>

      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>

    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="danger" @click="delBatch">批量删除<i class="el-icon-remove-outline " ></i></el-button>
<!--      <el-upload-->
<!--          action="http://localhost:8081/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block"  class="ml-5">-->
<!--        <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>-->
<!--      </el-upload>-->
<!--      <el-button type="primary" @click="exp" class="ml-5">导出<i class="el-icon-top"></i></el-button>-->



    </div>



    <el-table :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="name" label="名称" >
      </el-table-column>
      <el-table-column prop="flag" label="唯一标识" >
      </el-table-column>
      <el-table-column prop="description" label="描述" >
      </el-table-column>

      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="info" @click="selectMenu(scope.row)">
            分配菜单<i class="el-icon-menu"></i>
          </el-button>
          <el-button type="primary" @click="handleEdit(scope.row)">
            编辑<i class="el-icon-edit"></i>
          </el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">
              删除<i class="el-icon-remove-outline"></i>
            </el-button></el-popconfirm>
        </template>

      </el-table-column>

    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"

          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total ">
      </el-pagination>
    </div>
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="唯一标识" >
          <el-input v-model="form.flag" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


    <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="30%">
      <el-tree
          :data="menuData"
          :props="props"

          :default-expanded-keys="expends"
          :default-checked-keys="checks"
          node-key="id"
          ref="tree"

          show-checkbox>
<!--          @check-change="handleCheckChange">-->
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span><i :class="data.icon"/>{{ data.name }}</span>
          </span>
      </el-tree>

      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Role",
  data(){
    return{
      tableData: [],
      total: 0,
      dialogFormVisible:false,
      menuDialogVis:false,
      pageNum:1,
      pageSize:10,
      form:{},
      menuData:[
      ],
      roleId: 0,
      name:'',
      expends:[],
      checks:[],
      roleFlag: '',
      ids: [],
      props:{
        label:"name",
      }
    }

  },
  created() {
    this.load()
  },
  methods:{
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate);
    },
    handleSelectionChange(val) {
      this.multipleSelection = val

    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true


    },
    del(id) {
      this.request.delete("/role/" + id).then(res=>{
        if (res.code === '200' ) {
          this.$message.success("删除成功")

          this.load()
        }else {
          this.$message.success("删除失败")
        }
      })
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/role/del/batch" ,ids).then(res=>{
        if (res.code ==='200') {
          this.$message.success("批量删除成功")

          this.load()
        }else {
          this.$message.success("批量删除失败")
        }
      })
    },

    save() {
      this.request.post("/role", this.form).then(res=>{
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$message.success("失败")
        }
      })

    },
    saveRoleMenu(){
      this.request.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
        if (res.code === '200') {
          this.$message.success("绑定成功")
          this.menuDialogVis = false
          // 操作管理员角色后需要重新登录
          if (this.roleFlag === 'ROLE_ADMIN') {
            this.$store.commit("logout")
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    reset() {
      this.name = ""
      this.address = ""
      this.email = ""
      this.load()
    },
    load(){
      this.request.get("/role/page", {
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,




        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })

      // fetch("http://localhost:8081/user/page?pageNum=" + this.pageNum+"&pageSize=" +this.pageSize+ "&username=" + this.username).then(res=> res.json()).then(res => {
      //   console.log(res)
      //   this.tableData = res.data
      //   this.total = res.total
      // })

    },

    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()

    },
    selectMenu(role){

      this.roleId = role.id
      this.roleFlag = role.flag


      this.request.get("/menu", {
      }).then(res => {
        console.log(res)

        this.menuData = res.data
        //后台返回的菜单数据处理成数组
        this.expends = this.menuData.map(v => v.id)


      })
      this.request.get("/role/roleMenu/" + this.roleId).then(res =>{
        this.menuDialogVis = true
        this.checks = res.data
        this.request.get("menu/ids").then(r =>{
          const ids = r.data
          ids.forEach(id => {
            if (!this.checks.includes(id)) {

              this.$refs.tree.setChecked(id,false);
            }
          })
        })


      })


    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()


    },
    exp() {
      window.open("http://localhost:8081/user/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("文件导入成功")
      this.load()
    }

  }
}
</script>

<style scoped>

</style>
