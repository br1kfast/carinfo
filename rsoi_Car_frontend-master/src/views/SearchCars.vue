

<template>
  <div>

    <h4>Search Car By Brand:</h4>
    <el-input v-model="input" placeholder="please input car brand"></el-input>
    <el-button type="primary" size="mini" @click="submitForm()">search</el-button>


    <h4>Cars info:</h4>

    <el-table
        :data="tableData"
        border
        style="width: 100%" height="500px">
      <el-table-column
          fixed
          prop="id"
          label="id"
          width="100"
      >
      </el-table-column>

      <el-table-column
          prop="carUid"
          label="carUid"
          width="100"
      >
      </el-table-column>

      <el-table-column
          prop="brand"
          label="brand"
          style="width: 20%"
      >
      </el-table-column>
      <el-table-column
          prop="model"
          label="model"
          width="200"
      >
      </el-table-column>
      <el-table-column
          prop="power"
          label="power"
          width="200"
      >
      </el-table-column>

      <el-table-column
          prop="type"
          label="type"
          width="200"
      >
      </el-table-column>
    </el-table>


  </div>
</template>


<script>
export default {
  methods: {
    submitForm() {
      const _this = this
          axios.get('http://localhost:10010/car/cars/carBrand/' + this.input).then(function(resp){
            if (resp.status === 200){
              _this.$alert('we find!')
              if(resp.data){
                _this.tableData = resp.data
                console.log(resp.data)
                console.log(resp)
              }
            }
          });
    },

  },
  data() {
    return {
      input: '',
      tableData: [{
        id: '',
        carUid: '',
        brand: '',
        model:'',
        power:'',
        type:''
      }]


    }
  }
}
</script>
