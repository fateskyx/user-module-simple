<template>
  <div>
    <el-button type="primary" @click="showAddUserForm">新增用户</el-button>
    <user-list @edit-user="showEditUserForm" />

    <user-form :visible.sync="formVisible" :user="selectedUser" :roles="roles" @refresh-list="refreshList" />
  </div>
</template>

<script>
import UserList from '@/components/UserList'
import UserForm from '@/components/UserForm'

export default {
  components: { UserList, UserForm },
  data () {
    return {
      formVisible: false,
      selectedUser: null,
      roles: []
    }
  },
  methods: {
    showAddUserForm () {
      this.selectedUser = null
      this.formVisible = true
    },
    showEditUserForm (user) {
      this.selectedUser = user
      this.formVisible = true
    },
    refreshList () {
      this.$refs.userList.fetchUsers()
    }
  }
  // mounted () {
  //   getRoles().then(response => {
  //     this.roles = response.data
  //   })
  // }
}
</script>
