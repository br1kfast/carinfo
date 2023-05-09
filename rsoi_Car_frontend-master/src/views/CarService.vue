<template>
  <div>
    <el-table
        :data="tableData"
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

      <!--    <el-table-column-->
      <!--        prop="officeUid"-->
      <!--        label="officeUid"-->
      <!--        width="100"-->
      <!--    >-->
      <!--    </el-table-column>-->

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

<!--      <el-table-column-->
<!--          fixed="right"-->
<!--          label="Operation"-->
<!--          width="150">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button @click="search(scope.row)" type="text" size="small">view office</el-button>-->
<!--&lt;!&ndash;          <el-button @click="del(scope.row)" type="text" size="small">删除</el-button>&ndash;&gt;-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
  </div>

</template>

<script>
export default {
  data() {
    return {
      // total:null,
      tableData: [{
        id: '',
        brand: '',
        model:'',
        power:'',
        type:''
      }]
    }
  },
    created() {
      this.tableData=[]
    const _this = this
    axios.get('http://localhost:10010/car/cars/findall').then(function (resp) {
      _this.tableData = resp.data
      console.log(resp);
    })
  },

}
</script>

<style scoped>

</style>
