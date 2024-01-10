import { axiosPublic } from "../utils";

const API_URL = "/api/v1/public/vacancy";

const getVacancies = (
  page: number,
  programmingLanguage?: string | null,
  minSalary?: number | null,
  maxSalary?: number | null,
  aggregator?: string | null,
) => {
  let query = "";
  if (programmingLanguage) {
    query += `&programmingLanguage=${programmingLanguage}`;
  }
  if (minSalary) {
    query += `&minSalary=${minSalary}`;
  }
  if (maxSalary) {
    query += `&maxSalary=${maxSalary}`;
  }
  if (aggregator) {
    query += `&aggregator=${aggregator}`;
  }
  return axiosPublic.get(`${API_URL}?page=${page}&size=10${query}`);
};

export default {
  getVacancies,
};
