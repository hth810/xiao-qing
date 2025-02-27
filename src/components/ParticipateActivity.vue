<template>
  <div>
    <h2>参与活动</h2>
    <button @click="participate">参与活动</button>
    <p>{{ message }}</p>
  </div>
</template>

<script>
import { getUserInfo, participateActivity, getTotalParticipants } from "../api";

export default {
  data() {
    return {
      message: "",
      totalParticipants: 0,
    };
  },
  methods: {
    async participate() {
      try {
        // Step 1: 获取用户信息
        const token = localStorage.getItem("token");
        if (!token) {
          this.message = "未登录或 Token 不存在";
          return;
        }

        const userInfoResponse = await fetch(
            "http://localhost:8080/user/userInfo",
            {
              method: "GET",
              headers: {
                "Authorization": `${token}`
              },
              body: null
            }
        );

        if (userInfoResponse.code !== 0) {
          throw new Error(userInfoResponse.message);
        }
        const userInfo = userInfoResponse.data;

        // Step 2: 使用用户信息参与活动
        const participateResponse = await fetch(
            "http://localhost:8080/clap/participate",
            {
              method: "POST",
              headers: {
                "Authorization": `${token}`
              },
              body: null
            }
        );
        if (participateResponse.code !== 0) {
          throw new Error(participateResponse.message);
        }
        this.message = "参与成功";

        // Step 3: 获取总参与人数
        const totalParticipantsResponse = await getTotalParticipants();
        if (totalParticipantsResponse.code !== 0) {
          throw new Error(totalParticipantsResponse.message);
        }
        this.totalParticipants = totalParticipantsResponse.data;
        console.log("总参与人数:", this.totalParticipants);
      } catch (error) {
        this.message = error.message || "操作失败，请稍后重试";
        console.error("错误:", error);
      }
    },
  },
};
</script>