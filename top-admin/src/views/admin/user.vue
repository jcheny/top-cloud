<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input v-model="listQuery.username" placeholder="用户名" clearable />
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="listQuery.mobile" placeholder="手机号" clearable />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="listQuery.email" placeholder="邮箱" clearable />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="listQuery.status" placeholder="是否可用" clearable>
          <el-option
            v-for="item in statusMap"
            :key="item.code"
            :label="item.message"
            :value="item.code"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="listQuery.roleId" placeholder="角色" clearable>
          <el-option
            v-for="item in roleData"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button v-if="$store.getters.hasPermission('USER_QUERY')" type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="$store.getters.hasPermission('USER_SAVE')" type="primary" @click="handleSave">新增</el-button>
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
      <el-table-column label="用户名" align="center">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.mobile }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="角色" align="center">
        <template slot-scope="scope">
          {{ scope.row.roleName }}
        </template>
      </el-table-column>
      <el-table-column
        class-name="status-col"
        label="状态"
        width="110"
        align="center"
      >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">
            {{ scope.row.status | statusTextFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="createTime"
        label="创建时间"
        width="200"
      >
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="$store.getters.hasPermission('USER_EDIT')" type="primary" @click="handleEdit(scope)">编辑</el-button>
          <el-button v-if="$store.getters.hasPermission('USER_DELETE')" type="danger" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType === 'edit' ? '编辑' : '新增'"
    >
      <el-form :model="putInfo" label-width="60px" label-position="left">
        <el-form-item v-if="dialogType !== 'new'" label="id">
          <el-input v-model="putInfo.id" placeholder="id" disabled clearable />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="putInfo.username" placeholder="用户名" clearable />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="putInfo.mobile" placeholder="手机号" clearable />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="putInfo.email" placeholder="邮箱" clearable />
        </el-form-item>
        <el-form-item v-if="dialogType === 'new'" label="密码">
          <el-input v-model="putInfo.password" placeholder="密码" clearable />
        </el-form-item>
        <el-form-item v-if="dialogType !== 'new'" label="状态">
          <el-select v-model="putInfo.status" placeholder="是否可用" clearable>
            <el-option
              v-for="item in statusMap"
              :key="item.code"
              :label="item.message"
              :value="item.code"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="putInfo.roleId" placeholder="角色" clearable>
            <el-option
              v-for="item in roleData"
              :key="item.id"
              :label="item.name"
              :value="item.id"
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
  getUserList,
  saveUser,
  updateUser,
  deleteUser
} from '@/api/admin/user'
import { getRoleList } from '@/api/admin/role'
import Pagination from '@/components/Pagination'
const caseStatusMap = [
  {
    code: 0,
    message: '正常'
  },
  {
    code: 1,
    message: '冻结'
  }
]
const defaultUser = {
  id: '',
  roleId: '',
  username: '',
  mobile: '',
  email: '',
  status: 0,
  password: ''
}
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
    statusTextFilter(status) {
      let value = null
      caseStatusMap.forEach((arg) => {
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
      roleData: [],
      statusMap: caseStatusMap,
      putInfo: Object.assign({}, defaultUser)
    }
  },
  created() {
    this.fetchData()
    this.fetchRoleData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getUserList(this.listQuery).then((response) => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    async fetchRoleData() {
      getRoleList().then((response) => {
        this.roleData = response.data
      })
    },
    handleSave() {
      this.putInfo = Object.assign({}, defaultUser)
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
        await updateUser(this.putInfo).then(() => {
          this.fetchData()
        })
      } else {
        await saveUser(this.putInfo).then(() => {
          this.fetchData()
        })
      }
      this.dialogVisible = false
      const { username, mobile, email } = this.putInfo
      this.$notify({
        title: 'Success',
        dangerouslyUseHTMLString: true,
        message: `
            <div>用户名: ${username}</div>
            <div>手机号: ${mobile}</div>
            <div>邮箱: ${email}</div>
          `,
        type: 'success'
      })
      this.btnLoading = false
    },
    handleDelete({ row }) {
      this.$confirm('确定删除用户?', '删除', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          var param = [row.id]
          await deleteUser(param)
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
