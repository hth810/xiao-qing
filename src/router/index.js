import { createRouter, createWebHistory } from "vue-router";
import UserRegister from "../components/UserRegister.vue";
import UserLogin from "../components/UserLogin.vue";
import UserInfo from "../components/UserInfo.vue";
import UpdateUser from "../components/UpdateUser.vue";
import ParticipateActivity from "../components/ParticipateActivity.vue";
import TotalParticipants from "../components/TotalParticipants.vue";

const routes = [
    { path: "/", redirect: "/login" },
    { path: "/register", component: UserRegister },
    { path: "/login", component: UserLogin },
    { path: "/user-info", component: UserInfo },
    { path: "/update-user", component: UpdateUser },
    { path: "/participate", component: ParticipateActivity },
    { path: "/total-participants", component: TotalParticipants },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;