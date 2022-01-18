<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="路由id">
        <el-input v-model="listQuery.routeId" placeholder="路由id" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSave">新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="路由id" align="center">
        <template slot-scope="scope">
          {{ scope.row.routeId }}
        </template>
      </el-table-column>
      <el-table-column label="转发目标uri" align="center">
        <template slot-scope="scope">
          {{ scope.row.routeUri }}
        </template>
      </el-table-column>
      <el-table-column label="访问路径" align="center">
        <template slot-scope="scope">
          {{ scope.row.predicates }}
        </template>
      </el-table-column>
      <el-table-column label="是否统计" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isStatistic | booFilter">
            {{ scope.row.isStatistic | booStatusMapFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否计费" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isBilling | booFilter">
            {{ scope.row.isBilling | booStatusMapFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否启用" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isEbl | statusFilter">
            {{ scope.row.isEbl | statusTextFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.updateTime }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.isEbl === 1" type="text" icon="el-icon-top" @click="operline(scope)">上线</el-button>
          <el-button v-if="scope.row.isEbl === 0" type="text" icon="el-icon-bottom" @click="operline(scope)">下线</el-button>
          <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope)">编辑</el-button>
          <el-button type="text" icon="el-icon-delete-solid" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType === 'edit' ? '编辑' : '新增'"
    >
      <el-form :model="putInfo" label-width="auto" label-position="left">
        <el-form-item v-if="dialogType === 'edit'" label="id">
          <el-input v-model="putInfo.id" placeholder="id" disabled clearable />
        </el-form-item>
        <el-form-item v-if="dialogType !== 'edit'" label="路由id">
          <el-input v-model="putInfo.routeId" placeholder="路由id" clearable />
        </el-form-item>
        <el-form-item v-if="dialogType !== 'edit'" label="转发目标uri">
          <el-input
            v-model="putInfo.routeUri"
            placeholder="转发目标uri"
            clearable
          >
            <template v-if="dialogType !== 'edit'" slot="prepend">lb://</template>
          </el-input>
        </el-form-item>
        <el-form-item v-if="dialogType !== 'edit'" label="访问路径">
          <el-input
            v-model="putInfo.predicates"
            placeholder="访问路径"
            clearable
          />
        </el-form-item>
        <el-form-item label="是否统计">
          <el-select v-model="putInfo.isStatistic" placeholder="是否统计" clearable>
            <el-option
              v-for="item in booStatusMap"
              :key="item.code"
              :label="item.message"
              :value="item.code"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否计费">
          <el-select v-model="putInfo.isBilling" placeholder="是否计费" clearable>
            <el-option
              v-for="item in booStatusMap"
              :key="item.code"
              :label="item.message"
              :value="item.code"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div style="text-align: right">
        <el-button type="danger" @click="dialogVisible = false">取消</el-button>
        <el-button
          type="primary"
          :loading="btnLoading"
          @click="confirm"
        >提交</el-button>
      </div>
    </el-dialog>
    <pagination
      v-show="total > 0"
      :total="total"
      :current.sync="listQuery.current"
      :size.sync="listQuery.size"
      @pagination="fetchData"
    />
  </div>
</template>

<script>
import {
  getGatewayRoutesList,
  saveGatewayRoutes,
  updateGatewayRoutes,
  deleteGatewayRoutes,
  pushGatewayRoutes,
  outlineGatewayRoutes
} from '@/api/system/router'
import Pagination from '@/components/Pagination'
const defaultGatewayRoutes = {
  routeId: '',
  routeUri: '',
  predicates: '',
  isStatistic: '',
  isBilling: ''
}
const caseStatusMap = [
  {
    code: 0,
    message: '在线'
  },
  {
    code: 1,
    message: '下线'
  }
]
const booStatusMap = [
  {
    code: 0,
    message: '正常'
  },
  {
    code: 1,
    message: '禁用'
  }
]
export default {
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'info'
      }
      return statusMap[status]
    },
    booFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'warning'
      }
      return statusMap[status]
    },
    statusTextFilter(status) {
      let value = null
      caseStatusMap.forEach((arg) => {
        if (arg.code === status) {
          value = arg.message
        }
      })
      return value
    },
    booStatusMapFilter(status) {
      let value = null
      booStatusMap.forEach((arg) => {
        if (arg.code === status) {
          value = arg.message
        }
      })
      return value
    }
  },
  data() {
    return {
      dialogVisible: false,
      dialogType: 'new',
      list: null,
      total: 0,
      listLoading: true,
      btnLoading: false,
      listQuery: {
        current: 1,
        size: 20
      },
      putInfo: Object.assign({}, defaultGatewayRoutes),
      statusMap: caseStatusMap,
      booStatusMap: booStatusMap
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.btnLoading = false
      this.listLoading = true
      getGatewayRoutesList(this.listQuery).then((response) => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },

    handleSave() {
      this.putInfo = Object.assign({}, defaultGatewayRoutes)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      for (const key in this.putInfo) {
        this.putInfo[key] = scope.row[key]
      }
    },

    async confirm() {
      this.btnLoading = true
      const isEdit = this.dialogType === 'edit'

      if (isEdit) {
        await updateGatewayRoutes(this.putInfo).then(() => {
          this.fetchData()
        })
      } else {
        await saveGatewayRoutes(this.putInfo).then(() => {
          this.fetchData()
        })
      }
      this.dialogVisible = false
      this.$message({
        type: 'success',
        message: '操作成功!'
      })
      this.btnLoading = false
    },
    handleDelete({ row }) {
      this.$confirm('确定删除网关路由表?', '删除', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          var param = [row.id]
          await deleteGatewayRoutes(param)
          this.fetchData()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        .catch((err) => {
          console.error(err)
        })
    },
    async operline(scope) {
      const row = scope.row
      // 上线
      var param = {
        id: row.id
      }
      if (row.isEbl === 1) {
        await pushGatewayRoutes(param).then(() => {
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
          this.fetchData()
        })
      } else {
        // 下线
        await outlineGatewayRoutes(param).then(() => {
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
          this.fetchData()
        })
      }
    }
  }
}
</script>
