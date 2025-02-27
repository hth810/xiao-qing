import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "path";

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 3000, // 前端运行端口
    proxy: {
      "/user": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      "/clap": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"), // 配置 @ 指向 src 目录
    },
  },
});