import { axiosPrivate, axiosPublic } from "../utils";

const PUBLIC_API_URL = "/api/v1/public/vacancy";
const USER_API_URL = "/api/v1/user/vacancy";

const getVacanciesForPublic = (
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
  return axiosPublic.get(`${PUBLIC_API_URL}?page=${page}&size=10${query}`);
};

const getVacanciesForUser = (
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
  return axiosPrivate.get(`${USER_API_URL}?page=${page}&size=10${query}`);
};

const createFavouriteVacancy = (vacancyId: number) => {
  return axiosPrivate.post(`${USER_API_URL}/${vacancyId}/favourite`);
};

const deleteFavouriteVacancy = (vacancyId: number) => {
  return axiosPrivate.delete(`${USER_API_URL}/${vacancyId}/favourite`);
};

export default {
  getVacanciesForPublic,
  getVacanciesForUser,
  createFavouriteVacancy,
  deleteFavouriteVacancy,
};
