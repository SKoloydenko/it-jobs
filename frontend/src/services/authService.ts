import {axiosPublic} from "../utils";

export interface LoginRequest {
    email: string;
    password: string;
}

export interface RegisterRequest {
    email: string;
    password: string;
}

const API_URL = "/api/v1/public/auth";

const register = (registerRequest: RegisterRequest) => {
    return axiosPublic.post(`${API_URL}/register`, { ...registerRequest });
};

const login = (loginRequest: LoginRequest) => {
    return axiosPublic.post(`${API_URL}/login`, { ...loginRequest });
};

const refresh = () => {
    return axiosPublic.post(`${API_URL}/refresh`);
};

const logout = () => {
    return axiosPublic.post(`${API_URL}/logout`);
};

export default {
    register,
    login,
    refresh,
    logout,
};
