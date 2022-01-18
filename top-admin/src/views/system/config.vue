<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="配置规则类型">
        <el-input
          v-model="listQuery.type"
          placeholder="配置规则类型"
          clearable
        />
      </el-form-item>
      <el-form-item label="配置规则代码">
        <el-input
          v-model="listQuery.code"
          placeholder="配置规则代码"
          clearable
        />
      </el-form-item>
      <el-form-item label="配置值">
        <el-input v-model="listQuery.value" placeholder="配置值" clearable />
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
      <el-table-column label="配置规则类型" align="center">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>
      <el-table-column label="配置规则代码" align="center">
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <el-table-column label="配置规则名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="配置规则描述" align="center">
        <template slot-scope="scope">
          {{ scope.row.desc }}
        </template>
      </el-table-column>
      <el-table-column label="配置值" align="center">
        <template slot-scope="scope">
          {{ scope.row.value }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
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
        <el-form-item v-if="dialogType === 'edit'" label="主键">
          <el-input v-model="putInfo.id" placeholder="主键" disabled clearable />
        </el-form-item>
        <el-form-item label="配置规则类型">
          <el-input
            v-model="putInfo.type"
            placeholder="配置规则类型"
            clearable
          />
        </el-form-item>
        <el-form-item label="配置规则代码">
          <el-input
            v-model="putInfo.code"
            placeholder="配置规则代码"
            clearable
          />
        </el-form-item>
        <el-form-item label="配置规则名称">
          <el-input
            v-model="putInfo.name"
            placeholder="配置规则名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="配置规则描述">
          <el-input
            v-model="putInfo.desc"
            placeholder="配置规则描述"
            clearable
          />
        </el-form-item>
        <el-form-item label="配置值">
          <el-input v-model="putInfo.value" placeholder="配置值" clearable />
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
  getConfigList,
  saveConfig,
  updateConfig,
  deleteConfig
} from '@/api/system/config'
import Pagination from '@/components/Pagination'
const defaultConfig = {
  id: '',
  type: '',
  code: '',
  name: '',
  desc: '',
  value: '',
  createTime: ''
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
      putInfo: Object.assign({}, defaultConfig)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getConfigList(this.listQuery).then((response) => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },

    handleSave() {
      this.putInfo = Object.assign({}, defaultConfig)
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
        await updateConfig(this.putInfo).then(() => {
          this.fetchData()
        })
      } else {
        await saveConfig(this.putInfo).then(() => {
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
      this.$confirm('确定删除平台配置信息?', '删除', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          var param = [row.id]
          await deleteConfig(param)
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
