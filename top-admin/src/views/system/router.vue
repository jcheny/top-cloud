<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="路由id">
        <el-input v-model="listQuery.routeId" placeholder="路由id" clearable />
      </el-form-item>
      <el-form-item label="转发目标uri">
        <el-input
          v-model="listQuery.routeUri"
          placeholder="转发目标uri"
          clearable
        />
      </el-form-item>
      <el-form-item label="路由执行顺序">
        <el-input
          v-model="listQuery.routeOrder"
          placeholder="路由执行顺序"
          clearable
        />
      </el-form-item>
      <el-form-item label="访问路径">
        <el-input
          v-model="listQuery.predicates"
          placeholder="访问路径"
          clearable
        />
      </el-form-item>
      <el-form-item label="过滤">
        <el-input v-model="listQuery.filters" placeholder="过滤" clearable />
      </el-form-item>
      <el-form-item label="是否统计">
        <el-input
          v-model="listQuery.isStatistic"
          placeholder="是否统计"
          clearable
        />
      </el-form-item>
      <el-form-item label="是否计费">
        <el-input
          v-model="listQuery.isBilling"
          placeholder="是否计费"
          clearable
        />
      </el-form-item>
      <el-form-item label="是否启用">
        <el-input v-model="listQuery.isEbl" placeholder="是否启用" clearable />
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
      <el-table-column label="路由执行顺序" align="center">
        <template slot-scope="scope">
          {{ scope.row.routeOrder }}
        </template>
      </el-table-column>
      <el-table-column label="访问路径" align="center">
        <template slot-scope="scope">
          {{ scope.row.predicates }}
        </template>
      </el-table-column>
      <el-table-column label="过滤" align="center">
        <template slot-scope="scope">
          {{ scope.row.filters }}
        </template>
      </el-table-column>
      <el-table-column label="是否统计" align="center">
        <template slot-scope="scope">
          {{ scope.row.isStatistic }}
        </template>
      </el-table-column>
      <el-table-column label="是否计费" align="center">
        <template slot-scope="scope">
          {{ scope.row.isBilling }}
        </template>
      </el-table-column>
      <el-table-column label="是否启用" align="center">
        <template slot-scope="scope">
          {{ scope.row.isEbl }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.updateTime }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope)">编辑</el-button>
          <el-button type="danger" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType === 'edit' ? '编辑' : '新增'"
    >
      <el-form :model="putInfo" label-width="auto" label-position="left">
        <el-form-item v-if="dialogType === 'edit' " label="id">
          <el-input v-model="putInfo.id" placeholder="id" disabled clearable />
        </el-form-item>
        <el-form-item label="路由id">
          <el-input v-model="putInfo.routeId" placeholder="路由id" clearable />
        </el-form-item>
        <el-form-item label="转发目标uri">
          <el-input
            v-model="putInfo.routeUri"
            placeholder="转发目标uri"
            clearable
          />
        </el-form-item>
        <el-form-item label="路由执行顺序">
          <el-input
            v-model="putInfo.routeOrder"
            placeholder="路由执行顺序"
            clearable
          />
        </el-form-item>
        <el-form-item label="访问路径">
          <el-input
            v-model="putInfo.predicates"
            placeholder="访问路径"
            clearable
          />
        </el-form-item>
        <el-form-item label="过滤">
          <el-input v-model="putInfo.filters" placeholder="过滤" clearable />
        </el-form-item>
        <el-form-item label="是否统计">
          <el-input
            v-model="putInfo.isStatistic"
            placeholder="是否统计"
            clearable
          />
        </el-form-item>
        <el-form-item label="是否计费">
          <el-input
            v-model="putInfo.isBilling"
            placeholder="是否计费"
            clearable
          />
        </el-form-item>
        <el-form-item label="是否启用">
          <el-input v-model="putInfo.isEbl" placeholder="是否启用" clearable />
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
  deleteGatewayRoutes
} from '@/api/system/router'
import Pagination from '@/components/Pagination'
const defaultGatewayRoutes = {
  id: '',
  routeId: '',
  routeUri: '',
  routeOrder: '',
  predicates: '',
  filters: '',
  isStatistic: '',
  isBilling: '',
  isEbl: '',
  isDeleted: '',
  createTime: '',
  updateTime: ''
}
export default {
  components: { Pagination },
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
      putInfo: Object.assign({}, defaultGatewayRoutes)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
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
    }
  }
}
</script>
