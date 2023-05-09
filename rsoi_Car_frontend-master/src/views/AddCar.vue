<template>
  <el-form style="width: 70%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">


    <el-form-item label="brand" prop="brand">
      <el-input v-model="ruleForm.brand"></el-input>
    </el-form-item>
    <el-form-item label="model" prop="model">
      <el-input v-model="ruleForm.model"></el-input>
    </el-form-item>
    <el-form-item label="power" prop="power">
      <el-input v-model="ruleForm.power"></el-input>
    </el-form-item>

    <el-form-item label="type" prop="type">
      <el-select v-model="ruleForm.type" placeholder="请选择type">
        <el-option label="SEDAN" value="SEDAN"></el-option>
        <el-option label="SUV" value="SUV"></el-option>
        <el-option label="MINIVAN" value="MINIVAN"></el-option>
        <el-option label="ROADSTER" value="ROADSTER"></el-option>
      </el-select>
    </el-form-item>


    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">submit</el-button>
      <el-button @click="resetForm('ruleForm')">reset</el-button>
      <el-button @click="test()">test</el-button>
    </el-form-item>
  </el-form>
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
          {required: true, message: 'Please enter brand', trigger: 'blur'},
          {min: 1, max: 100, message: '3 to 8 characters in length', trigger: 'blur'}
        ],
        model: [
          {required: true, message: 'Please enter model', trigger: 'blur'},
          {min: 1, max: 100, message: '3 to 8 characters in length', trigger: 'blur'}
        ],
        power: [
          {required: true, message: 'Please enter power', trigger: 'blur'},
          {min: 1, max: 100, message: '3 to 8 characters in length', trigger: 'blur'}
        ],
        type: [
          {required: true, message: 'please select type', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
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

      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          // console.log(_this.ruleForm)
          axios.post('http://localhost:10010/car/cars/create', this.carInfo).then(function (resp) {
            console.log(resp)
            if (resp.data === 'success') {
              _this.$alert('success', 'AddCar', {
                confirmButtonText: 'ok',
                callback: action => {
                  _this.$router.push('/admin/car')
                }
              });
            }
          })
        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

  },

}

</script>
