<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="角色名称">
        <el-input v-model="listQuery.name" placeholder="角色名称" clearable />
      </el-form-item>
      <el-form-item label="角色代码">
        <el-input v-model="listQuery.code" placeholder="角色代码" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleAddRole">新增</el-button>
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
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="角色名称" align="center" width="95">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="角色代码" align="center" width="95">
        <template slot-scope="scope">
          <span>{{ scope.row.code }}</span>
        </template>
      </el-table-column>
      <el-table-column label="角色描述" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
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
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope)"
          >编辑</el-button>
          <el-button
            type="text"
            icon="el-icon-delete-solid"
            @click="handleDelete(scope)"
          >删除</el-button>
          <el-button
            type="text"
            icon="el-icon-s-tools"
            @click="openAuthorize(scope)"
          >授权</el-button>
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
        <el-form-item label="角色名称">
          <el-input v-model="putInfo.name" placeholder="角色名称" clearable />
        </el-form-item>
        <el-form-item label="角色代码">
          <el-input v-model="putInfo.code" placeholder="角色代码" clearable />
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input
            v-model="putInfo.description"
            placeholder="角色描述"
            type="textarea"
            clearable
          />
        </el-form-item>
      </el-form>
      <div style="text-align: right">
        <el-button type="danger" @click="dialogVisible = false">取消</el-button>
        <el-button
          type="primary"
          :loading="btnLoading"
          @click="confirmRole"
        >提交</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="授权"
      :fullscreen="fullscreen"
      :visible.sync="dialogVisibleAuth"
    >
      <el-form ref="authorizeData" :model="authorizeData">
        <el-tree
          ref="tree"
          :data="menu"
          show-checkbox
          default-expand-all
          node-key="id"
          :default-checked-keys="authorizeData.roleHasMenu"
          highlight-current
          :props="defaultProps"
        />
      </el-form>
      <div style="text-align: right">
        <el-button
          type="info"
          @click="dialogVisibleAuth = false"
        >取消</el-button>
        <el-button
          type="primary"
          :loading="btnLoading"
          @click="doAuthorize"
        >授权</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getRoleList,
  saveRole,
  updateRole,
  deleteRole,
  authorizeRole
} from '@/api/admin/role'
import { menuAndUserTree } from '@/api/admin/menu'
const defaultRole = {
  id: '',
  name: '',
  code: '',
  description: ''
}
export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'gray'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      fullscreen: true,
      dialogVisibleAuth: false,
      dialogVisible: false,
      dialogType: 'new',
      list: null,
      listLoading: true,
      listQuery: {},
      roleData: [],
      putInfo: Object.assign({}, defaultRole),
      btnLoading: false,
      menu: {},
      authorizeData: { roleId: '', roleHasMenu: [] },
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  created() {
    this.fetchData()
    this.fetchRoleData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getRoleList(this.listQuery).then((response) => {
        this.list = response.data
        this.listLoading = false
      })
    },
    async fetchRoleData() {
      getRoleList().then((response) => {
        this.roleData = response.data
      })
    },
    handleAddRole() {
      this.putInfo = Object.assign({}, defaultRole)
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

    async confirmRole() {
      this.btnLoading = true
      const isEdit = this.dialogType === 'edit'

      if (isEdit) {
        await updateRole(this.putInfo).then(async() => {
          this.fetchData()
        })
      } else {
        await saveRole(this.putInfo).then(async() => {
          this.fetchData()
        })
      }
      this.dialogVisible = false
      const { name, code } = this.putInfo
      this.$notify({
        title: 'Success',
        dangerouslyUseHTMLString: true,
        message: `
            <div>角色名称: ${name}</div>
            <div>角色代码: ${code}</div>
          `,
        type: 'success'
      })
      this.btnLoading = false
    },
    handleDelete({ row }) {
      this.$confirm('确定删除角色?', '删除', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          var param = [row.id]
          await deleteRole(param)
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
    openAuthorize(scope) {
      var param = {
        roleId: scope.row.id,
        type: 2 // 表示查询节点
      }
      menuAndUserTree(param).then((response) => {
        this.dialogVisibleAuth = true
        console.log(response.data.menuTree)
        this.menu = response.data.menuTree
        if (response.data.roleHas != null) {
          this.authorizeData.roleHasMenu = response.data.roleHas.map((x) => {
            return x.id
          })
        } else {
          this.authorizeData.roleHasMenu = null
        }
        this.authorizeData.roleId = scope.row.id
      })
      false
    },
    async doAuthorize() {
      this.btnLoading = true
      var checkedKeys = this.$refs.tree.getHalfCheckedKeys()
      this.authorizeData.roleHasMenu = checkedKeys.concat(
        this.$refs.tree.getCheckedKeys()
      )
      await authorizeRole(this.authorizeData).then(async() => {
        this.fetchData()
      })
      this.dialogVisibleAuth = false
      this.$message({
        type: 'success',
        message: '授权成功!'
      })
      this.btnLoading = false
    }
  }
}
</script>
