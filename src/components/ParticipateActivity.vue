<template>
  <div>
    <!-- 欢迎语和参与按钮 -->
    <div v-if="username" class="welcome">
      <h1>欢迎，{{ username }}</h1>
    </div>
    <!-- 第一个背景 -->
    <div class="background background-1"></div>

    <!-- 第二个背景，包含动态内容 -->
    <div class="background background-2">
      <!-- 动态文字 -->
      <div class="text" v-if="hasParticipated">
        「你是北洋头条人物」 <br>
        第  {{ place }} 位突破时空壁垒的击掌者你好，<br>
        恭喜你与其他 {{ totalParticipants-1 }} 名 TJUER 共同为天大庆生
      </div>

      <!-- 按钮，绑定点击事件 -->
      <div class="button" @click="participate"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import totalParticipants from "@/components/TotalParticipants.vue";

export default {
  data() {
    return {
      username: "",
      totalParticipants: 0,
      userInfo: null, // 用于存储用户信息
      hasParticipated: false,
      place: 0,
    };
  },
  created() {
    // 页面加载时获取用户信息和总参与人数
    this.getUserInfo();
    this.getTotalParticipants();
  },
  methods: {
    async getUserInfo() {
      try {
        const token = localStorage.getItem("token");
        if (!token) {
          this.message = "未登录或 Token 不存在";
          return;
        }
        const response = await axios.get("http://localhost:8080/user/userInfo", {
          headers: {
            Authorization: token,
          },
        });
        this.userInfo = response.data.data;
        this.username = this.userInfo.username;
        this.hasParticipated = this.userInfo.participated;
        this.place = this.userInfo.place;
      } catch (error) {
        console.error("获取用户信息失败", error);
      }
    },
    async getTotalParticipants() {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get("http://localhost:8080/clap/total-participants", {
          headers: {
            Authorization: token,
          },
        });
        this.totalParticipants = response.data.data;
      } catch (error) {
        console.error("获取总参与人数失败", error);
      }
    },
    async participate() {
      if (!this.userInfo) {
        alert("用户信息未加载完成，请稍后再试！");
        return;
      }

      try {
        const token = localStorage.getItem("token");

        const response = await axios.post(
            "http://localhost:8080/clap/participate",
            this.userInfo,
            {
              headers: {
                "Content-Type": "application/json",
                Authorization: token,
              },
            }
        );

        if (response.data.code === 0) {
          // 成功参与，更新总参与人数
          this.totalParticipants += 1;
          this.place = this.totalParticipants;
          alert("参与活动成功！");
          this.hasParticipated = true;
        } else {
          alert("您已参与过活动！");
        }
      } catch (error) {
        console.error("参与活动失败", error);
        alert("参与活动失败，请稍后再试！");
      }
    },
  },
};
</script>

<style scoped>
/* 设置页面基本样式 */
body, html {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: auto; /* 允许滚动 */
  display: flex;
  flex-direction: column;
  align-items: center; /* 内容居中 */
  background-color: #f0f0f0; /* 页面背景色 */
}

/* 背景容器 */
.background {
  position: relative; /* 相对定位，用于放置文字和按钮 */
  width: 100vw; /* 宽度为视口宽度的 80% */
  height: 60vh; /* 高度为视口高度的 40% */
  min-width: 300px; /* 最小宽度 */
  min-height: 200px; /* 最小高度 */
  background-size: cover; /* 背景图片覆盖整个容器 */
  background-position: center; /* 背景图片居中 */
  background-repeat: no-repeat; /* 防止背景图片重复 */
  margin-bottom: 20px; /* 在第一个背景下方添加 20px 的空白 */
  border-radius: 15px; /* 添加圆角 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 添加阴影 */
}

/* 第一个背景 */
.background-1 {
  background-image: url('@/assets/images/banner1.jpg'); /* 修改图片路径为 Vue 资源路径 */
}

/* 第二个背景 */
.background-2 {
  background-image: url('@/assets/images/banner2改.jpg'); /* 修改图片路径为 Vue 资源路径 */
}

/* 文字样式 */
.text {
  position: absolute; /* 绝对定位，放置在背景上 */
  top: 10px; /* 距离顶部 10px */
  right: 40px; /* 距离右侧 5px */
  color: rgb(231, 47, 142); /* 文字颜色 */
  font-size: 1.2rem; /* 文字大小 */
  font-family: Arial, sans-serif; /* 字体 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 文字阴影 */
  text-align: right; /* 文字右对齐 */
}

/* 按钮样式 */
.button {
  position: absolute; /* 绝对定位，放置在背景上 */
  width: 99%; /* 按钮宽度 */
  height: 99%; /* 按钮高度 */
  background-image: url('@/assets/images/banner2改.png'); /* 修改图片路径为 Vue 资源路径 */
  background-size: cover; /* 按钮图片覆盖整个元素 */
  background-position: center; /* 按钮图片居中 */
  cursor: pointer; /* 鼠标悬停时显示为手型 */
  top: 50%; /* 垂直居中 */
  left: 50%; /* 水平居中 */
  transform: translate(-50%, -50%); /* 精确居中 */
  border-radius: 15px; /* 添加圆角 */
  transition: transform 0.3s ease; /* 添加动画效果 */
}

/* 按钮悬停效果 */
.button:hover {
  transform: translate(-50%, -50%) scale(1.05); /* 鼠标悬停时放大 */
}

/* 动态欢迎语样式 */
.welcome {
  text-align: center;
  margin-top: 20px;
  color: #333; /* 文字颜色 */
  font-size: 0.5rem; /* 文字大小 */
  font-weight: bold; /* 加粗文字 */
}


</style>