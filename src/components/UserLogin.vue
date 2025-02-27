<template>
  <div class="body">
    <div class="container">
      <div class="box">
        <h2>登录</h2>
        <div class="input-box">
          <label>账号</label>
          <input type="text" v-model="username" placeholder="请输入账号" />
        </div>
        <div class="input-box">
          <label>密码</label>
          <input type="password" v-model="password" placeholder="请输入密码" />
        </div>
        <div class="btn-box">
          <a href="javascript:void(0)" class="btn" @click="forgotPassword">忘记密码？</a>
          <div>
            <button type="button" @click="login">登录</button>
            <button type="button" @click="openRegisterDialog">注册</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 注册对话框 -->
    <div class="register-dialog-container" v-if="showRegisterDialog">
      <div class="register-dialog">
        <div class="close-btn" @click="closeRegisterDialog">×</div>
        <h2>注册</h2>
        <form @submit.prevent="submitRegisterForm">
          <input type="text" v-model="registerUsername" placeholder="用户名" required />
          <input type="password" v-model="registerPassword" placeholder="密码" required />
          <button type="submit">提交</button>
          <p class="message" :style="{ color: messageColor }">{{ message }}</p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      message: "",
      registerUsername: "",
      registerPassword: "",
      messageColor: "red",
      showRegisterDialog: false
    };
  },
  methods: {
    async login() {
      const response = await fetch("http://localhost:8080/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `username=${encodeURIComponent(this.username)}&password=${encodeURIComponent(this.password)}`
      });
      if (!response.ok) {
        throw new Error("登录失败，请稍后重试");
      }
      const data = await response.json();
      if (data.code === 0) {
        localStorage.setItem("token", data.data);
        this.$router.push("/participate");
      } else {
        this.message = data.message || "登录失败，请稍后重试";
      }
    },
    openRegisterDialog() {
      this.showRegisterDialog = true;
    },
    closeRegisterDialog() {
      this.showRegisterDialog = false;
    },
    async submitRegisterForm() {
      this.registerUsername = this.registerUsername.trim();
      this.registerPassword = this.registerPassword.trim();

      if (
          this.registerUsername.length < 5 ||
          this.registerUsername.length > 16 ||
          this.registerPassword.length < 5 ||
          this.registerPassword.length > 16
      ) {
        this.message = "用户名和密码长度必须在5-16之间";
        return;
      }

      const response = await fetch("http://localhost:8080/user/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `username=${encodeURIComponent(this.registerUsername)}&password=${encodeURIComponent(this.registerPassword)}`
      })

      if (!response.ok) {
        throw new Error("请求失败，请稍后重试");
      }
      const data = await response.json();
      if (data.code === 0) {
        this.messageColor = "green";
        this.message = "注册成功！";
        setTimeout(() => {
          this.closeRegisterDialog();
        }, 1000);
      } else {
        this.messageColor = "red";
        this.message = data.message || "注册失败，请稍后重试";
      }
    },
    forgotPassword() {
      // 跳转到忘记密码页面（若需要实现）
      // this.$router.push("/forgot-password");
    }
  }
};
</script>

<style scoped>
@charset "utf-8";

.body {
  height: 100vh;
  background: radial-gradient(circle, #ff7e5f, #feb47b);
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  width: 450px;
  height: 450px;
  border-top: 1px solid rgba(255, 255, 255, 0.5);
  border-left: 1px solid rgba(255, 255, 255, 0.5);
  border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  border-right: 1px solid rgba(255, 255, 255, 0.3);
  backdrop-filter: blur(1px);
  background: rgba(240, 218, 218, 0.4);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
}

.box > h2 {
  color: rgba(255, 255, 255, 0.9);
  margin-bottom: 30px;
}

.box .input-box {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  margin-bottom: 10px;
}

.box .input-box label {
  font-size: 13px;
  color: rgba(255, 255, 255, 0.9);
  margin-bottom: 10px;
}

.box .input-box input {
  letter-spacing: 1px;
  font-size: 14px;
  box-sizing: border-box;
  width: 350px;
  height: 35px;
  border-radius: 5px;
  border: 1px solid rgba(255, 255, 255, 0.3);
  background: rgba(50, 50, 50, 0.3);
  outline: none;
  padding: 0 12px;
  color: rgba(255, 255, 255, 0.9);
  transition: 0.2s;
}

.box .input-box input:focus {
  border: 1px solid rgba(255, 255, 255, 0.8);
}

.box .btn-box {
  width: 250px;
  display: flex;
  flex-direction: column;
}

.box .btn-box > a {
  outline: none;
  display: block;
  width: 250px;
  text-align: end;
  text-decoration: none;
  font-size: 13px;
  color: rgba(255, 255, 255, 0.8);
  cursor: pointer;
}

.box .btn-box > a:hover {
  color: rgba(255, 255, 255, 1);
}

.box .btn-box > div {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.box .btn-box > div > button {
  outline: none;
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  display: block;
  font-size: 14px;
  border-radius: 5px;
  transition: 0.2s;
  cursor: pointer;
}

.box .btn-box > div > button:nth-of-type(1) {
  width: 120px;
  height: 35px;
  color: rgba(255, 255, 255, 0.9);
  border: 1px solid rgba(0, 96, 96, 0.7);
  background: rgba(0, 96, 96, 0.5);
}

.box .btn-box > div > button:nth-of-type(2) {
  width: 120px;
  height: 35px;
  margin-left: 10px;
  color: rgba(255, 255, 255, 0.9);
  border: 1px solid rgba(0, 96, 96, 0.7);
  background: rgba(0, 96, 96, 0.5);
}

.box .btn-box > div > button:hover {
  border: 1px solid rgba(0, 96, 96);
  background: rgba(0, 96, 96);
}

/* 注册对话框样式 */
.register-dialog-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.register-dialog {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  position: relative;
  text-align: center;
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

.register-dialog h2 {
  color: #333;
  margin-bottom: 20px;
}

.register-dialog input[type="text"],
.register-dialog input[type="password"] {
  width: 80%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

.register-dialog button {
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

.register-dialog button:hover {
  background: #6da2db;
}

.register-dialog .message {
  margin-top: 10px;
}
</style>