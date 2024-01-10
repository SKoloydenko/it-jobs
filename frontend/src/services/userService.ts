import { axiosPrivate } from "../utils";

const API_URL = "/api/v1/user/profile";

const getCurrentUser = () => {
  return axiosPrivate.get(`${API_URL}`);
};

const deleteCurrentUser = () => {
  return axiosPrivate.delete(`${API_URL}`);
};

export default {
  getCurrentUser,
  deleteCurrentUser,
};
