<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="组">
        <el-input v-model="listQuery.group" placeholder="组" clearable />
      </el-form-item>
      <el-form-item label="用户Id">
        <el-input v-model="listQuery.userId" placeholder="用户Id" clearable />
      </el-form-item>
      <el-form-item label="方法">
        <el-select v-model="listQuery.method" placeholder="方法" clearable>
          <el-option
            v-for="item in methodMap"
            :key="item.message"
            :label="item.message"
            :value="item.message"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="代理">
        <el-input v-model="listQuery.agent" placeholder="代理" clearable />
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker v-model="listQuery.startTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间" />
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker v-model="listQuery.endTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间" />
      </el-form-item>
      <el-form-item>
        <el-button v-if="$store.getters.hasPermission('LOG_QUERY')" type="primary" @click="fetchData">查询</el-button>
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
      <el-table-column label="组" align="center">
        <template slot-scope="scope">
          {{ scope.row.group }}
        </template>
      </el-table-column>
      <el-table-column label="用户Id" align="center">
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column>
      <el-table-column
        label="日志类型"
        align="center"
      >
        <template slot-scope="scope">
          <el-tag :type="scope.row.type | statusFilter">
            {{ scope.row.type | statusTextFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="方法" align="center">
        <template slot-scope="scope">
          {{ scope.row.method }}
        </template>
      </el-table-column>
      <el-table-column label="参数" align="center">
        <template slot-scope="scope">
          {{ scope.row.params }}
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.time }}
        </template>
      </el-table-column>
      <el-table-column label="IP地址" align="center">
        <template slot-scope="scope">
          {{ scope.row.ip }}
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          {{ scope.row.remark }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column label="代理" align="center">
        <template slot-scope="scope">
          {{ scope.row.agent }}
        </template>
      </el-table-column>
      <el-table-column label="接口路径" align="center">
        <template slot-scope="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>
    </el-table>
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
import { getLogList } from '@/api/system/log'
import Pagination from '@/components/Pagination'
const caseStatusMap = [
  {
    code: 1,
    message: 'INFO'
  },
  {
    code: 2,
    message: 'DEBUG'
  },
  {
    code: 3,
    message: 'WARING'
  },
  {
    code: 4,
    message: 'ERROR'
  }
]
const methodMap = [
  {
    message: 'GET'
  },
  {
    message: 'POST'
  },
  {
    message: 'PUT'
  },
  {
    message: 'DELETE'
  }
]
export default {
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        2: 'info',
        3: 'warning',
        4: 'danger'
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
      list: null,
      total: 0,
      listLoading: true,
      btnLoading: false,
      listQuery: {
        current: 1,
        size: 20
      },
      methodMap: methodMap
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getLogList(this.listQuery).then((response) => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    }

  }
}
</script>
