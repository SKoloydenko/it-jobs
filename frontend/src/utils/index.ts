import axios from "axios";
import { store } from "../redux/store";

export const axiosPublic = axios.create();

export const axiosPrivate = axios.create();

axiosPrivate.interceptors.request.use(
  async (config) => {
    const auth = store.getState().auth;

    if (auth?.accessToken) {
      if (config?.headers) {
        config.headers["Authorization"] = `Bearer ${auth.accessToken}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);
