<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="主键">
        <el-input v-model="listQuery.id" placeholder="主键" clearable />
      </el-form-item>
      <el-form-item label="组">
        <el-input v-model="listQuery.group" placeholder="组" clearable />
      </el-form-item>
      <el-form-item label="用户Id">
        <el-input v-model="listQuery.userId" placeholder="用户Id" clearable />
      </el-form-item>
      <el-form-item label="日志类型">
        <el-input
          v-model="listQuery.type"
          placeholder="日志类型"
          clearable
        />
      </el-form-item>
      <el-form-item label="方法">
        <el-input v-model="listQuery.method" placeholder="方法" clearable />
      </el-form-item>
      <el-form-item label="参数">
        <el-input v-model="listQuery.params" placeholder="参数" clearable />
      </el-form-item>
      <el-form-item label="时间">
        <el-input v-model="listQuery.time" placeholder="时间" clearable />
      </el-form-item>
      <el-form-item label="IP地址">
        <el-input v-model="listQuery.ip" placeholder="IP地址" clearable />
      </el-form-item>
      <el-form-item label="描述">
        <el-input
          v-model="listQuery.description"
          placeholder="描述"
          clearable
        />
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="listQuery.remark" placeholder="备注" clearable />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input
          v-model="listQuery.createTime"
          placeholder="创建时间"
          clearable
        />
      </el-form-item>
      <el-form-item label="代理">
        <el-input v-model="listQuery.agent" placeholder="代理" clearable />
      </el-form-item>
      <el-form-item label="接口路径">
        <el-input v-model="listQuery.url" placeholder="接口路径" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
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
      <el-table-column label="主键" align="center">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
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
          {{ scope.row.type }}
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
export default {
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      btnLoading: false,
      listQuery: {
        current: 1,
        size: 20
      }
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
