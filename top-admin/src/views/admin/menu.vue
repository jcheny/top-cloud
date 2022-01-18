<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="handleSave">新增根节点</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column prop="name" label="菜单名称" />
      <el-table-column prop="name" align="center" label="菜单属性">
        <template slot-scope="scope">
          <el-tag :type="scope.row.type | statusFilter">
            {{ scope.row.type | statusTextFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="菜单排序" align="center" />
      <el-table-column prop="icon" label="菜单icon" align="center">
        <template slot-scope="scope">
          <el-button type="info" circle :icon="scope.row.icon" plain />
        </template>
      </el-table-column>
      <el-table-column prop="key" label="菜单权限" align="center" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.type === 1"
            icon="el-icon-plus"
            type="text"
            @click="handleSaveNode(scope)"
          >添加下级</el-button>
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope)"
          >编辑</el-button>
          <el-button
            v-if="scope.row.children === undefined"
            icon="el-icon-delete"
            type="text"
            @click="handleDelete(scope)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType === 'edit' ? '编辑' : '新增'"
    >
      <el-form :model="putInfo" label-width="auto" label-position="left">
        <el-form-item v-if="dialogType !== 'new'" label="id">
          <el-input v-model="putInfo.id" placeholder="id" disabled clearable />
        </el-form-item>
        <el-form-item label="菜单名称">
          <el-input v-model="putInfo.name" placeholder="菜单名称" clearable />
        </el-form-item>
        <el-form-item label="菜单权限">
          <el-input v-model="putInfo.key" placeholder="菜单权限" clearable />
        </el-form-item>
        <el-form-item v-if="dialogType !== 'edit'" label="菜单类型">
          <el-radio-group v-model="putInfo.type">
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          v-if="putInfo.type === 1 || dialogType === 'edit'"
          label="菜单图标"
        >
          <el-input v-model="putInfo.icon" placeholder="菜单图标" clearable />
        </el-form-item>
        <el-form-item v-if="putInfo.type === 1" label="菜单路由">
          <el-input
            v-model="putInfo.targetUrl"
            placeholder="菜单路由"
            clearable
          />
        </el-form-item>
        <el-form-item label="上级路由">
          <el-cascader
            v-model="putInfo.parentId"
            :options="list"
            placeholder="上级路由"
            :props="{ value: 'id', label: 'name', checkStrictly: true }"
            clearable
          />
        </el-form-item>
        <el-form-item label="是否为左侧菜单">
          <el-radio-group v-model="putInfo.shows">
            <el-radio :label="0">显示</el-radio>
            <el-radio :label="2">不显示</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单排序（同级）">
          <el-input-number v-model="putInfo.sort" :min="1" :max="10" />
        </el-form-item>
        <el-form-item label="菜单描述">
          <el-input v-model="putInfo.desc" type="textarea" />
        </el-form-item>
      </el-form>
      <el-divider v-if="dialogType !== 'edit'" />
      <el-checkbox
        v-if="
          putInfo.targetUrl != '' && putInfo.type === 1 && dialogType !== 'edit'
        "
        v-model="checkedAutoMenu"
      >是否为上述菜单生成对于的增删改查菜单</el-checkbox>
      <el-checkbox
        v-if="dialogType !== 'edit'"
        v-model="checkedAutoAuth"
      >是否为当前用户添加上述菜单权限</el-checkbox>
      <div style="text-align: right">
        <el-button type="danger" @click="dialogVisible = false">取消</el-button>
        <el-button
          type="primary"
          :loading="btnLoading"
          @click="confirm"
        >提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const defaultMenu = {
  id: '',
  name: '',
  type: 1,
  targetUrl: '',
  desc: '',
  icon: '',
  key: '',
  parentId: 0,
  shows: 0,
  sort: 1
}
const caseStatusMap = [
  {
    code: 1,
    message: '菜单'
  },
  {
    code: 2,
    message: '按钮'
  }
]
import { authorizeRoleRun } from '@/api/admin/role'
import {
  getMenuTree,
  deleteMenu,
  updateMenu,
  saveMenu,
  saveMenuBatch
} from '@/api/admin/menu'
export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'primary',
        2: 'success'
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
      checkedAutoAuth: false,
      checkedAutoMenu: false,
      dialogVisible: false,
      dialogType: 'new',
      listLoading: false,
      listQuery: {},
      list: [],
      btnLoading: false,
      putInfo: Object.assign({}, defaultMenu)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getMenuTree().then((response) => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      for (const key in this.putInfo) {
        this.putInfo[key] = scope.row[key]
      }
    },
    handleSave() {
      this.putInfo = Object.assign({}, defaultMenu)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleSaveNode(scope) {
      this.putInfo = Object.assign({}, defaultMenu)
      this.putInfo.parentId = scope.row.id
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    async confirm() {
      this.btnLoading = true
      const isEdit = this.dialogType === 'edit'
      if (this.putInfo.parentId instanceof Array) {
        this.putInfo.parentId =
          this.putInfo.parentId[this.putInfo.parentId.length - 1]
      }
      if (isEdit) {
        await updateMenu(this.putInfo).then(() => {
          this.fetchData()
        })
      } else {
        await saveMenu(this.putInfo).then((response) => {
          if (response.data != null) {
            if (this.checkedAutoMenu) {
              var crud = this.buildCRUD(response.data)
              saveMenuBatch(crud).then((res) => {
                var authRole = []
                res.data.forEach((item) => {
                  authRole.push(item.id)
                })
                if (this.checkedAutoAuth) {
                  var param = {
                    roleId: this.$store.getters.roleId,
                    roleHasMenu: authRole
                  }
                  authorizeRoleRun(param).then(() => {
                    true
                  })
                }
              })
            }
            if (this.checkedAutoAuth) {
              var param = {
                roleId: this.$store.getters.roleId,
                roleHasMenu: [response.data.id]
              }
              authorizeRoleRun(param).then(() => {
                true
              })
            }
          }
          this.checkedAutoAuth = false
          this.checkedAutoMenu = false
          this.fetchData()
        })
      }
      this.$message({
        type: 'success',
        message: '操作成功!'
      })
      this.btnLoading = false
      this.dialogVisible = false
    },
    handleDelete({ row }) {
      this.$confirm('确定删除菜单?', '删除', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          var param = [row.id]
          await deleteMenu(param)
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
    buildCRUD(menuTop) {
      var keyTop = menuTop.key.split('_')[0]
      var arr = []
      var c = {
        name: menuTop.name + '查询',
        type: 2,
        icon: 'el-icon-search',
        key: keyTop + '_QUERY',
        parentId: menuTop.id
      }
      var r = {
        name: menuTop.name + '新增',
        type: 2,
        icon: 'el-icon-circle-plus-outline',
        key: keyTop + '_SAVE',
        parentId: menuTop.id
      }
      var u = {
        name: menuTop.name + '修改',
        type: 2,
        icon: 'el-icon-brush',
        key: keyTop + '_EDIT',
        parentId: menuTop.id
      }
      var d = {
        name: menuTop.name + '删除',
        type: 2,
        icon: 'el-icon-delete',
        key: keyTop + '_DELETE',
        parentId: menuTop.id
      }
      arr.push(c)
      arr.push(r)
      arr.push(u)
      arr.push(d)
      return arr
    }
  }
}
</script>

<style></style>
