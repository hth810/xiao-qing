<template>
  <div>
    <h2>更新用户信息</h2>
    <form @submit.prevent="update">
      <input v-model="username" placeholder="新用户名" />
      <button type="submit">更新</button>
    </form>
    <p>{{ message }}</p>
  </div>
</template>

<script>
import { updateUserInfo } from "../api";

export default {
  data() {
    return {
      username: "",
      message: "",
    };
  },
  methods: {
    async update() {
      try {
        await updateUserInfo({ username: this.username });
        this.message = "更新成功";
      } catch (error) {
        this.message = error.response.data.message;
      }
    },
  },
};
</script>