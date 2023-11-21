import {axiosPublic} from "../utils";

const API_URL = "/api/v1/public/vacancy";

const getVacancies = () => {
    return axiosPublic.get(`${API_URL}?size=40`);
};

export default {
    getVacancies,
};
