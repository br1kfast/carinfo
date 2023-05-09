
<template>

  <div>
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
          prop="methodType"
          label="methodType"
          width="200"
      >
      </el-table-column>


      <el-table-column
          prop="name"
          label="name"
          width="250"
      >
      </el-table-column>
      <el-table-column
          prop="path"
          label="path"
          width="400"
      >
      </el-table-column>


    </el-table>
  </div>
</template>


<script>
export default {
    created() {
      const _this = this
      axios.get('http://localhost:10010/reports/reports//getReports/all').then(function (resp) {
        _this.tableData = resp.data
      })
    },
    data() {
      return {
        // total:null,
        tableData: [{
          id: '',
          methodType: '',
          name: '',
          path: ''
        }]
      }
    }

}
</script>
