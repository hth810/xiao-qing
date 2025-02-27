import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

const apiClient = axios.create({
    baseURL: API_BASE_URL,
});

export const registerUser = (userData) =>
    apiClient.post("/user/register", userData);

export const loginUser = (userData) =>
    apiClient.post("/user/login", userData);

export const getUserInfo = (token) =>
    apiClient.get("/user/userInfo", {
        headers: { Authorization: token },
    });

export const updateUserInfo = (userData) =>
    apiClient.put("/user/update", userData);

export const participateActivity = (userData) =>
    apiClient.post("/clap/participate", userData);

export const getTotalParticipants = () =>
    apiClient.get("/clap/total-participants");