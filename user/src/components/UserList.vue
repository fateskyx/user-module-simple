/* eslint-disable */
<template>
  <div>
    <el-input v-model="searchQuery" placeholder="搜索用户名" style="width: 300px; margin-bottom: 20px;" />
    <el-button @click="fetchUsers" type="primary" icon="el-icon-search" size="mini"></el-button>
    <el-table :data="users" border style="width: 100%">
      <el-table-column prop="userName" label="用户名" width="180" />
      <el-table-column prop="email" label="邮箱" width="180" />
      <el-table-column prop="gender" label="性别" width="80" :formatter="formatGender" />
      <el-table-column prop="role" label="角色" />
      <el-table-column label="操作" width="240">
        <template slot-scope="scope">
          <el-button size="mini" @click="editUser(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="pagination.page"
      :page-size="pagination.pageSize"
      layout="total, prev, pager, next"
      :total="pagination.total">
    </el-pagination>
  </div>
</template>

<script>
import { queryUsers, deleteUser } from '@/services/userService'

export default {
  data () {
    return {
      users: [],
      searchQuery: '',
      pagination: {
        page: 1,
        pageSize: 10,
        total: 0
      }
    }
  },
  methods: {
    fetchUsers () {
      const params = {
        page: this.pagination.page,
        pageSize: this.pagination.pageSize,
        search: this.searchQuery
      }
      queryUsers(params).then(response => {
        this.users = response.data
        this.pagination.total = response.data.total
      })
    },
    handleCurrentChange (page) {
      this.pagination.page = page
      this.fetchUsers()
    },
    deleteUser (id) {
      deleteUser(id).then(() => {
        this.$message.success('用户删除成功')
        this.fetchUsers()
      })
    },
    editUser (user) {
      this.$emit('edit-user', user)
    },
    formatGender (row) {
      return row.gender === '0' ? '男' : '女'
    }
  },
  mounted () {
    this.fetchUsers()
  }
}
</script>
