<template>
  <!--  el-container  构建整个⻚⾯框架。-->
  <el-container style="height: 700px; border: 1px solid #eee">
    <!--  el-aside：构建左侧菜单-->
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

      <el-menu router :default-openeds="['0','1','2']">
        <el-submenu v-for="(item, index) in $router.options.routes" :key="index" :index="index+''" v-if="index>0 && index<3">
          <template slot="title"><i class="el-icon-s-fold"></i>{{ item.name }}</template>
<!--          :class="$route.path==item2.path?'isActive':''"-->
          <el-menu-item v-for="(item2, index2) in item.children" :key="index2" :index="item2.path"
          :class="$route.path===item2.path?'is-active':''">{{ item2.name }}</el-menu-item>
        </el-submenu>
        <el-button type="danger" @click="logout">logout</el-button>
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
  data() {
    return {
      carInfo: {
        // carUid: '',
        brand: '',
        model: '',
        power: '',
        type: '',
        // officeUid: [],
      },
      ruleForm: {
        // carUid: '',
        brand: '',
        model: '',
        power: '',
        type: ''
      },

      rules: {
        brand: [
          {required: true, message: 'please enter brand', trigger: 'blur'},
          {min: 1, max: 10, message: '3 to 8 characters in length', trigger: 'blur'}
        ],
        model: [
          {required: true, message: 'please enter model', trigger: 'blur'},
          {min: 1, max: 5, message: '3 to 8 characters in length', trigger: 'blur'}
        ],
        power: [
          {required: true, message: 'please enter power', trigger: 'blur'},
          {min: 1, max: 9, message: '3 to 8 characters in length', trigger: 'blur'}
        ],
        type: [
          {required: true, message: 'please select type', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    logout() {
      window.localStorage.clear();
      this.$router.push('/');
    },
    test() {
      // this.carInfo.carUid = this.ruleForm.carUid
      this.carInfo.brand = this.ruleForm.brand
      this.carInfo.model = this.ruleForm.model
      this.carInfo.power = this.ruleForm.power
      this.carInfo.type = this.ruleForm.type
      console.log(this.carInfo)

    },

    submitForm(formName) {
      this.carInfo.brand = this.ruleForm.brand
      this.carInfo.model = this.ruleForm.model
      this.carInfo.power = this.ruleForm.power
      this.carInfo.type = this.ruleForm.type
      console.log(this.carInfo)


    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

  },

}
</script>

<style scoped>

</style>
