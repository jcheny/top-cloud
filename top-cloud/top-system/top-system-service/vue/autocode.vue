<template>
    <div class="app-container">
        <el-form :inline="true" :model="listQuery" class="demo-form-inline">
                        <el-form-item label="id">
                <el-input v-model="listQuery.id" placeholder="id" clearable />
            </el-form-item>
                        <el-form-item label="数据库表名">
                <el-input v-model="listQuery.tableName" placeholder="数据库表名" clearable />
            </el-form-item>
                        <el-form-item label="模块名">
                <el-input v-model="listQuery.modelName" placeholder="模块名" clearable />
            </el-form-item>
                        <el-form-item label="api网关名称">
                <el-input v-model="listQuery.gatewayName" placeholder="api网关名称" clearable />
            </el-form-item>
                        <el-form-item label="前端模块名">
                <el-input v-model="listQuery.vueName" placeholder="前端模块名" clearable />
            </el-form-item>
                        <el-form-item label="创建时间">
                <el-input v-model="listQuery.createTime" placeholder="创建时间" clearable />
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
                        <el-table-column label="id" align="center">
                <template slot-scope="scope">
                    {{ scope.row.id }}
                </template>
            </el-table-column>
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
                    <el-button type="primary" @click="handleEdit(scope)">编辑</el-button>
                    <el-button type="danger" @click="handleDelete(scope)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :title="dialogType === 'edit' ? '编辑' : '新增'"
        >
            <el-form :model="putInfo" label-width="60px" label-position="left">
                                    <el-form-item label="id">
                        <el-input v-model="putInfo.id" placeholder="id" clearable />
                    </el-form-item>
                                    <el-form-item label="数据库表名">
                        <el-input v-model="putInfo.tableName" placeholder="数据库表名" clearable />
                    </el-form-item>
                                    <el-form-item label="模块名">
                        <el-input v-model="putInfo.modelName" placeholder="模块名" clearable />
                    </el-form-item>
                                    <el-form-item label="api网关名称">
                        <el-input v-model="putInfo.gatewayName" placeholder="api网关名称" clearable />
                    </el-form-item>
                                    <el-form-item label="前端模块名">
                        <el-input v-model="putInfo.vueName" placeholder="前端模块名" clearable />
                    </el-form-item>
                                    <el-form-item label="创建时间">
                        <el-input v-model="putInfo.createTime" placeholder="创建时间" clearable />
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
        getAutoCodeList,
        saveAutoCode,
        updateAutoCode,
        deleteAutoCode
    } from '@/api/system/top_auto_code'
    import Pagination from '@/components/Pagination'
    const defaultAutoCode = {
                id: '',
                tableName: '',
                modelName: '',
                gatewayName: '',
                vueName: '',
                createTime: '',
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
                putInfo: Object.assign({}, defaultAutoCode)
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
                this.btnLoading = true
                const isEdit = this.dialogType === 'edit'

                if (isEdit) {
                    await updateAutoCode(this.putInfo).then(() => {
                        this.fetchData()
                    })
                } else {
                    await saveAutoCode(this.putInfo).then(() => {
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
            }
        }
    }
</script>
