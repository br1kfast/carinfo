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

    <el-table-column
        fixed="right"
        label="operation"
        width="150">
      <template slot-scope="scope">
        <el-button @click="edit(scope.row)" type="text" size="small">modify</el-button>
        <el-button @click="del(scope.row)" type="text" size="small">delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>


<script>
export default {
  methods: {
    edit(row) {
      this.$router.push({
        path:'/admin/carUpdate',
        query:{
          carUid:row.carUid
        }
      })
      // console.log(row);
    },
    del(row){
      const _this = this
      axios.delete('http://localhost:10010/car/cars/delete/' + row.id).then(function (resp){
        console.log(resp)
        if (resp.status === 200){
          _this.$alert('success', 'Delete Car', {
            confirmButtonText: 'ok',
            callback: action => {
              window.location.reload()
            }
          });
        }
      })
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:10010/car/cars/findall').then(function (resp) {
      _this.tableData = resp.data
    })
  },
  data() {
    return {
      // total:null,
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