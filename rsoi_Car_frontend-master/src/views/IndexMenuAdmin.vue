<template>
  <!--  el-container  构建整个⻚⾯框架。-->
  <el-container style="height: 700px; border: 1px solid #eee">
    <!--  el-aside：构建左侧菜单-->
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

      <el-menu router :default-openeds="['3']">
        <el-submenu v-for="(item, index) in $router.options.routes" :index="index+''" v-if="index>2">
          <template slot="title"><i class="el-icon-s-fold"></i>{{ item.name }}</template>
<!--          :class="$route.path==item2.path?'isActive':''"-->
          <el-menu-item v-for="(item2, index2) in item.children" :index="item2.path"
          :class="$route.path===item2.path?'is-active':''">{{ item2.name }}</el-menu-item>
        </el-submenu>
        <el-button type="danger" @click="logout" with>logout</el-button>
      </el-menu>

    </el-aside>


      <el-main>
        <router-view></router-view>
      </el-main>
<!--    </el-container>-->

  </el-container>
</template>

<script>
export default {
  name: "IndexMenuAdmin",
  methods: {
    logout() {
      window.localStorage.clear();
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>

</style>
