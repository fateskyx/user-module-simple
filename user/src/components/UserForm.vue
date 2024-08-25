/* eslint-disable */
<template>
  <el-dialog :title="isEdit ? '编辑用户' : '新增用户'" :visible.sync="localVisible" width="50%">
    <el-form :model="userForm" ref="userForm" label-width="100px">
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="userForm.userName" />
      </el-form-item>
      <el-form-item label="真实姓名" prop="trueName">
        <el-input v-model="userForm.trueName" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userForm.password" type="password" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="userForm.email" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="userForm.gender">
          <el-radio :label="'0'">男</el-radio>
          <el-radio :label="'1'">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="userForm.roleIds" multiple placeholder="请选择角色">
          <el-option v-for="role in roles" :key="role.id" :label="role.name" :value="role.id" />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取消</el-button>
      <el-button type="primary" @click="saveUser">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { createUser, updateUser } from '@/services/userService'

export default {
  props: {
    visible: Boolean,
    user: Object,
    roles: Array
  },
  data () {
    return {
      userForm: {
        id: '',
        userName: '',
        trueName: '',
        password: '',
        email: '',
        gender: '',
        roleIds: []
      },
      localVisible: false
    }
  },
  computed: {
    isEdit () {
      return !!this.userForm.id
    }
  },
  watch: {
    visible (newVal) {
      this.localVisible = newVal
    },
    user (newVal) {
      if (newVal) {
        this.userForm = { ...newVal }
      }
    }
  },
  methods: {
    saveUser () {
      const saveAction = this.isEdit ? updateUser(this.userForm.id, this.userForm) : createUser(this.userForm)
      saveAction.then(() => {
        this.$message.success(`用户${this.isEdit ? '更新' : '创建'}成功`)
        this.$emit('refresh-list')
        this.closeDialog()
      })
    },
    closeDialog () {
      this.localVisible = false
      this.$emit('update:visible', false) // 通知父组件更新visible
    }
  }
}
</script>
