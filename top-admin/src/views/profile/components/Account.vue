<template>
  <el-form>
    <el-form-item label="原密码">
      <el-input v-model.trim="account.oldPassword" show-password clearable type="password" placeholder="原密码" auto-complete="on" tabindex="2" />
    </el-form-item>
    <el-form-item label="新密码">
      <el-input v-model.trim="account.password" show-password clearable type="password" placeholder="新密码" auto-complete="on" tabindex="2" />
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input v-model.trim="account.confirmPassword" show-password clearable type="password" placeholder="确认密码" auto-complete="on" tabindex="2" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">更新</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { updatePassword } from '@/api/admin/user'

const defaultAccount = {
  password: '',
  oldPassword: '',
  confirmPassword: ''
}
export default {
  data() {
    return {
      account: Object.assign({}, defaultAccount)
    }
  },
  methods: {
    async submit() {
      await updatePassword(this.account).then(() => {
        this.$message({
          message: '修改密码成功',
          type: 'success',
          duration: 5 * 1000
        })
        this.account = Object.assign({}, defaultAccount)
      })
      await this.$store.dispatch('user/logout')
      await this.$store.dispatch('authorize/removeAuthorize')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>
