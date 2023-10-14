<template >
  <div style="display: flex;justify-content: space-between;" class="wrapper"  >

    <div style="width: 26%;height: 100vh" class="test">
      <div style="display: flex;justify-content: center;padding-bottom:10vh" ><h1>小日历</h1></div>
      <el-calendar v-model="value" style="padding-top: 5vh">
    </el-calendar></div>
    <div style="width: 40%" >






      <div style="margin: 10px 0; ">
        <el-divider content-position="center">动态图片展示</el-divider>

        <el-carousel height="450px">
          <el-carousel-item v-for="item in imgs" :key="item">
            <img :src="item" style="width: 100%">
          </el-carousel-item>
        </el-carousel>


        <el-divider content-position="center">文件图片展示</el-divider>
      </div>
      <div style="margin: 10px 0">
        <el-row :gutter="5">
          <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px">
            <div style="border: 1px solid #ccc">
              <img :src="item.url" alt="" style="width: 100%" height="244">
              <div style="color: #666;padding: 10px" >{{item.name}}</div>
            </div>




          </el-col>

        </el-row>

      </div>


    </div>
    <div style="width: 26%;" >
      <h1 style="display: flex;justify-content: center;">励志故事</h1>

      <span style="font-size:2em">春秋战国时代，一位父亲和他的儿子出征打战。父亲已做了将军，儿子还只是马前卒。又一阵号角吹响，战鼓雷鸣了，父亲庄严地托起一个箭囊，其中插着一只箭。父亲郑重对儿子说：“这是家袭宝箭，配带身边，力量无穷，但千万不可抽出来。”那是一个极其精美的箭囊，厚牛皮打制，镶着幽幽泛光的铜边儿，再看露出的箭尾。一眼便能认定用上等的孔雀羽毛制作。

儿子喜上眉梢，贪婪地推想箭杆、箭头的模样，耳旁仿佛嗖嗖地箭声掠过，敌方的主帅应声折马而毙。果然，配带宝箭的儿子英勇非凡，所向披靡。当鸣金收兵的号角吹响时，儿子再也禁不住得胜的豪气，完全背弃了父亲的叮嘱，强烈的欲望驱赶着他呼一声就拔出宝箭，试图看个究竟。骤然间他惊呆了。一只断箭，箭囊里装着一只折断的箭。

我一直挎着只断箭打仗呢！儿子吓出了一身冷汗，仿佛顷刻间失去支柱的房子，轰然意志坍塌了。

结果不言自明，儿子惨死于乱军之中。

拂开蒙蒙的硝烟，父亲拣起那柄断箭，沉重地啐一口道：“不相信自己的意志，永远也做不成将军。”

把胜败寄托在一只宝箭上，多么愚蠢，而当一个人把生命的核心与把柄交给别人，又多么危险！比如把希望寄托在儿女身上；把幸福寄托在丈夫身上；把生活保障寄托在单位身上……</span>

    </div>
  </div>




</template>

<script>
export default {
  name: "FrontHome",
  created() {
    this.request.get("/echarts/file/front/all").then(res => {
          console.log(res.data)
      this.files = res.data.filter(v => v.type === 'png' || v.type === 'jpg' || v.type === 'webp')
    })
  },
  data() {
    return{
      value: new Date(),
      imgs:[
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F4k%2Fs%2F01%2F210924164432Cc-0-lp.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672836228&t=1030f74c64ad8e6a147301af2949aa0c',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile.moyublog.com%2Fd%2Ffile%2F2020-12-19%2F85bcaa1cca144bb65991b949a99f57b6.jpg&refer=http%3A%2F%2Ffile.moyublog.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672836228&t=63f579150c84a71d14b533e142197d78',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile.moyublog.com%2Fd%2Ffile%2F2020-12-08%2F2f378b79c603156a7ee157a8a0fead67.jpg&refer=http%3A%2F%2Ffile.moyublog.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672836228&t=5d697dee263598a2b2f7093760420f98',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.wenytao.com%2Fzb_users%2Fupload%2F2021%2F02%2F202102081612793643645682.jpg&refer=http%3A%2F%2Fwww.wenytao.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672836228&t=28c658a08bd52123b89b7418f07c7035'
      ],
      files: []
    }
  }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B , #3F5EFB);
  overflow: hidden;
}
#test /deep/ .el-calendar .el-calendar__header .el-calendar__body{
  height: 100vh;

}



</style>
