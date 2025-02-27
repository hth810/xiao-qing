<template>
  <div>
    <h2>活动总参与人数</h2>
    <p>总参与人数: {{ total }}</p>
  </div>
</template>

<script>
import { getTotalParticipants } from "../api";

export default {
  data() {
    return {
      total: 0,
    };
  },
  mounted() {
    this.fetchTotalParticipants();
  },
  methods: {
    async fetchTotalParticipants() {
      try {
        const response = await getTotalParticipants();
        this.total = response.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>