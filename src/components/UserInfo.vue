<template>
  <div>
    <h2>用户信息</h2>
    <button @click="fetchUserInfo">获取用户信息</button>
    <div v-if="loading">加载中...</div>
    <div v-else-if="userInfo">
      <p>用户ID: {{ userInfo.id }}</p>
      <p>用户名: {{ userInfo.username }}</p>
    </div>
    <div v-else>
      <p>获取用户信息失败: {{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userInfo: null,
      loading: false,
      errorMessage: '',
    };
  },
  methods: {
    async fetchUserInfo() {
      this.loading = true;
      this.errorMessage = '';
      this.userInfo = null;

      try {
        const token = localStorage.getItem('token');
        console.log('token', token);
        if (!token) {
          throw new Error('未登录或 Token 不存在');
        }

        const response = await axios.get('http://localhost:8080/user/userInfo', {
          headers: {
            Authorization: `${token}`,
          },
        });

        if (response.data.code === 0) {
          this.userInfo = response.data.data;
        } else {
          throw new Error(response.data.message);
        }
      } catch (error) {
        this.errorMessage = error.message;
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
/* 添加一些简单的样式 */
button {
  margin-top: 10px;
}
div {
  margin-top: 10px;
}
</style>