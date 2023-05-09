<template>
<div>
  <el-form style="width: 70%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="name" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="password" prop="password">
      <el-input v-model="ruleForm.password"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">login</el-button>
      <el-button @click="register('ruleForm')">registration</el-button>
<!--            <el-button @click="test()">test</el-button>-->
    </el-form-item>
  </el-form>
</div>
</template>


<script>
export default {
  data() {
    return {
      ruleForm: {
        name: '',
        password: '',
        role:'user',
      },
      rules: {
        name: [
          { required: true, message: 'please enter name', trigger: 'blur' },
          { min: 1, max: 10, message: '1 to 10 characters in length', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'please enter password', trigger: 'blur' },
          { min: 1, max: 10, message: '1 to 10 characters in length', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    test(){
      console.log(this.ruleForm)
    },
    submitForm: function (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => { //表单验证
        if (valid) {
          axios.post('http://localhost:10010/session/users/login', this.ruleForm).then(function (resp) {
            console.log(resp)
            console.log(resp.data.msg) //msg存放是是role信息
            if (resp.data.code === 0) {
              if(resp.data.data){
                console.log(resp.data.data);
                localStorage.setItem('JWT',resp.data.data)
              }
              if (resp.data.msg === 'user') {
                console.log("true")
                _this.$alert('success', 'Login', { //通知组件
                  confirmButtonText: 'ok',
                  callback: action => {
                    _this.$router.push('/user') //$表示实例属性
                  }
                });
              }
              if (resp.data.msg === 'admin') {
                console.log("true")
                _this.$alert('success', 'Login', {
                  confirmButtonText: 'ok',
                  callback: action => {
                    _this.$router.push('/admin')
                  }
                });
              }

            }else{
              console.log("false")
              _this.$alert('Incorrect username or password', 'Login', {
                confirmButtonText: 'ok',
                callback: action => {
                  window.location.reload()
                }
              });
            }
          })
        } else {
          return false;
        }
      });
    },
    register(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          // console.log(_this.ruleForm)
          axios.post('http://localhost:10010/session/users', this.ruleForm).then(function (resp){
            console.log(resp)
            if (resp.data === 'success'){
              _this.$alert('success', 'Register', {
                confirmButtonText: 'ok',
                callback: action => {
                  _this.$router.push('/user')
                }
              });
            }
          })
        } else {
          // console.log('error submit!!');
          return false;
        }
      });

      // this.$refs[formName].resetFields();
    }
  }
}
</script>
