<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="数据库表名">
        <el-input
          v-model="listQuery.tableName"
          placeholder="数据库表名"
          clearable
        />
      </el-form-item>
      <el-form-item label="模块名">
        <el-input
          v-model="listQuery.modelName"
          placeholder="模块名"
          clearable
        />
      </el-form-item>
      <el-form-item label="api网关名称">
        <el-input
          v-model="listQuery.gatewayName"
          placeholder="api网关名称"
          clearable
        />
      </el-form-item>
      <el-form-item label="前端模块名">
        <el-input
          v-model="listQuery.vueName"
          placeholder="前端模块名"
          clearable
        />
      </el-form-item>
      <el-form-item>
        <el-button v-if="$store.getters.hasPermission('AUTO_QUERY')" type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="$store.getters.hasPermission('AUTO_SAVE')" type="primary" @click="handleSave">新增</el-button>
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
      <el-table-column label="数据库表名" align="center">
        <template slot-scope="scope">
          {{ scope.row.tableName }}
        </template>
      </el-table-column>
      <el-table-column label="模块名" align="center">
        <template slot-scope="scope">
          {{ scope.row.modelName }}
        </template>
      </el-table-column>
      <el-table-column label="api网关名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.gatewayName }}
        </template>
      </el-table-column>
      <el-table-column label="前端模块名" align="center">
        <template slot-scope="scope">
          {{ scope.row.vueName }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button
            v-if="$store.getters.hasPermission('AUTO_QUERY')"
            type="text"
            icon="el-icon-video-play"
            @click="previewTable(scope)"
          >预览</el-button>
          <el-button
            v-if="$store.getters.hasPermission('AUTO')"
            type="text"
            icon="el-icon-paperclip"
            @click="autoCode(scope)"
          >生成代码</el-button>
          <el-button
            v-if="$store.getters.hasPermission('AUTO_EDIT')"
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope)"
          >编辑</el-button>
          <el-button
            v-if="$store.getters.hasPermission('AUTO_DELETE')"
            type="text"
            icon="el-icon-delete-solid"
            @click="handleDelete(scope)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType === 'edit' ? '编辑' : '新增'"
    >
      <el-form
        ref="putInfo"
        :model="putInfo"
        label-width="auto"
        label-position="left"
        :rules="rules"
      >
        <el-form-item v-if="dialogType !== 'new'" label="id" prop="id">
          <el-input v-model="putInfo.id" disabled placeholder="id" clearable />
        </el-form-item>
        <el-form-item label="数据库表名" prop="tableName">
          <el-input
            v-model="putInfo.tableName"
            placeholder="数据库表名"
            clearable
          /></el-form-item>
        <el-form-item label="模块名" prop="modelName">
          <el-input
            v-model="putInfo.modelName"
            placeholder="模块名"
            clearable
          />
        </el-form-item>
        <el-form-item label="api网关名称" prop="gatewayName">
          <el-input
            v-model="putInfo.gatewayName"
            placeholder="api网关名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="前端模块名" prop="vueName">
          <el-input
            v-model="putInfo.vueName"
            placeholder="前端模块名"
            clearable
          />
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

    <el-dialog :visible.sync="dialogPreview" title="预览">
      <el-table :data="tableInfo.columnsDesc" style="width: 100%">
        <el-table-column prop="column_name" label="列名" />
        <el-table-column prop="column_key" label="主键">
          <template slot-scope="scope">
            <i v-if="scope.row.column_key === 'PRI'" class="el-icon-check" />
            <i v-if="scope.row.column_key !== 'PRI'" class="el-icon-close" />
          </template>
        </el-table-column>
        <el-table-column prop="column_comment" label="列注释" />
        <el-table-column prop="column_type" label="数据类型" />
        <el-table-column prop="beanType" label="实体类型" />
        <el-table-column prop="beanName" label="实体bean列名" />
        <el-table-column prop="jdbcType" label="mapperxml需要类型" />
        <el-table-column prop="javaType" label="java类型" />
      </el-table>
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
  getAutoCodeList,
  saveAutoCode,
  updateAutoCode,
  deleteAutoCode,
  checkAble,
  autoAble
} from '@/api/system/auto_code'
import { validNull } from '@/utils/validate'
import Pagination from '@/components/Pagination'
const defaultAutoCode = {
  id: '',
  tableName: '',
  modelName: '',
  gatewayName: '',
  vueName: '',
  createTime: ''
}
export default {
  components: { Pagination },
  data() {
    const validateNUll = (rule, value, callback) => {
      if (!validNull(value)) {
        callback(new Error('不能空哦'))
      } else {
        callback()
      }
    }
    return {
      dialogPreview: false,
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
      putInfo: Object.assign({}, defaultAutoCode),
      rules: {
        tableName: [
          { required: true, trigger: 'blur', validator: validateNUll }
        ],
        modelName: [
          { required: true, trigger: 'blur', validator: validateNUll }
        ],
        gatewayName: [
          { required: true, trigger: 'blur', validator: validateNUll }
        ],
        vueName: [{ required: true, trigger: 'blur', validator: validateNUll }]
      },
      tableInfo: {}
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getAutoCodeList(this.listQuery).then((response) => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },

    handleSave() {
      this.putInfo = Object.assign({}, defaultAutoCode)
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
      this.$refs.putInfo.validate((valid) => {
        if (valid) {
          this.btnLoading = true
          const isEdit = this.dialogType === 'edit'

          if (isEdit) {
            updateAutoCode(this.putInfo).then(() => {
              this.fetchData()
            })
          } else {
            saveAutoCode(this.putInfo).then(() => {
              this.fetchData()
            })
          }
          this.dialogVisible = false
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
          this.btnLoading = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleDelete({ row }) {
      this.$confirm('确定删除?', '删除', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          var param = [row.id]
          await deleteAutoCode(param)
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
    async previewTable(scope) {
      await checkAble(scope.row.id).then((response) => {
        if (response.data.tableDesc != null) {
          this.tableInfo = response.data
          this.dialogPreview = true
        } else {
          this.$message({
            message: scope.row.tableName + '表还未创建，请先创建',
            type: 'warning'
          })
        }
      })
    },
    async autoCode(scope) {
      await autoAble(scope.row.id)
      this.$message({
        type: 'success',
        message: '生成成功!'
      })
    }
  }
}
</script>
