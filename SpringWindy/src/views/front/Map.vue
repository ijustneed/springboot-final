<template>
  <div id="container" style="width: 100%;height: 100vh">

  </div>
</template>

<script>
export default {
  name: "Map",
  data() {
    return{}
  },
  mounted() {
    var map = new AMap.Map("container", {
      zoom: 13,
      center: [119.637199, 29.061899],
      // resizeEnable: true,
      // mapStyle: 'amap://styles/whitesmoke'
    });
    var marker = new AMap.Marker({
      position: new AMap.LngLat(119.637199, 29.061899),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
      title: '金华职业技术学院'
    });
    map.add(marker);
  }
}
</script>

<style scoped>

</style>
