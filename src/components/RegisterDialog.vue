<template>
  <div class="register-dialog">
    <div class="close-btn" @click="closeDialog">×</div>
    <h2>注册</h2>
    <form @submit.prevent="submitForm">
      <input type="text" v-model="username" placeholder="用户名" required />
      <input type="password" v-model="password" placeholder="密码" required />
      <button type="submit">提交</button>
    </form>
    <p class="message" :style="{ color: messageColor }">{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      message: "",
      messageColor: "red"
    };
  },
  methods: {
    submitForm() {
      this.username = this.username.trim();
      this.password = this.password.trim();

      if (this.username.length < 5 || this.username.length > 16 || this.password.length < 5 || this.password.length > 16) {
        this.message = "用户名和密码长度必须在5-16之间";
        return;
      }

      fetch("http://localhost:8080/user/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `username=${encodeURIComponent(this.username)}&password=${encodeURIComponent(this.password)}`
      })
          .then(response => response.json())
          .then(data => {
            if (data.code === 0) {
              this.messageColor = "green";
              this.message = "注册成功！";
              setTimeout(() => {
                this.closeDialog();
              }, 1000);
            } else {
              this.message = data.message;
            }
          })
          .catch(error => {
            this.message = "请求失败，请检查网络";
            console.error("请求失败:", error);
          });
    },
    closeDialog() {
      this.$emit("close");
    }
  }
};
</script>

<style scoped>
.register-dialog {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 400px; /* 固定宽度 */
  height: 300px; /* 固定高度 */
  text-align: center;
  position: relative;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 35px;
  height: 35px;
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #999;
}

.close-btn:hover {
  color: #333;
}

h2 {
  color: #333;
  margin-bottom: 20px;
}

input[type="text"],
input[type="password"] {
  width: 80%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

button {
  width: 100%;
  padding: 10px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s ease;
}

button:hover {
  background: #6da2db;
}

.message {
  margin-top: 10px;
}
</style>