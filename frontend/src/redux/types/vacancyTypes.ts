import { ProgrammingLanguage } from "../../utils/ProgrammingLanguage";
import { Aggregator } from "../../utils/Aggregator";

export interface VacancyResponse {
  id: number;
  programmingLanguage: ProgrammingLanguage;
  title: string;
  minSalary: number;
  maxSalary: number;
  employer: string;
  url: string;
  aggregator: Aggregator;
  favourite: boolean | null;
}

export interface VacancyPageResponse {
  content: VacancyResponse[];
  page: number;
  size: number;
  totalPages: number;
}

export interface VacancyState {
  vacancies: VacancyPageResponse | null;
  loading: boolean;
  error: string | null;
}

export enum VacancyActionType {
  GET_VACANCIES_FOR_PUBLIC = "GET_VACANCIES_FOR_PUBLIC",
  GET_VACANCIES_FOR_USER = "GET_VACANCIES_FOR_USER",
  GET_VACANCIES_SUCCESS = "GET_VACANCIES_SUCCESS",
  GET_VACANCIES_ERROR = "GET_VACANCIES_ERROR",

  CREATE_FAVOURITE_VACANCY = "CREATE_FAVOURITE_VACANCY",
  DELETE_FAVOURITE_VACANCY = "DELETE_FAVOURITE_VACANCY",
  FAVOURITE_VACANCY_SUCCESS = "FAVOURITE_VACANCY_SUCCESS",
  FAVOURITE_VACANCY_ERROR = "FAVOURITE_VACANCY_ERROR",
}

interface GetVacanciesForPublic {
  type: VacancyActionType.GET_VACANCIES_FOR_PUBLIC;
}

interface GetVacanciesForUser {
  type: VacancyActionType.GET_VACANCIES_FOR_USER;
}

interface CreateFavouriteVacancy {
  type: VacancyActionType.CREATE_FAVOURITE_VACANCY;
}

interface DeleteFavouriteVacancy {
  type: VacancyActionType.DELETE_FAVOURITE_VACANCY;
}

interface GetVacanciesSuccess {
  type: VacancyActionType.GET_VACANCIES_SUCCESS;
  payload: { response: VacancyPageResponse };
}

interface GetVacanciesError {
  type: VacancyActionType.GET_VACANCIES_ERROR;
  payload: { error: string };
}

interface FavouriteVacancySuccess {
  type: VacancyActionType.FAVOURITE_VACANCY_SUCCESS;
}

interface FavouriteVacancyError {
  type: VacancyActionType.FAVOURITE_VACANCY_ERROR;
  payload: { error: string };
}

export type VacancyAction =
  | GetVacanciesForPublic
  | GetVacanciesForUser
  | GetVacanciesSuccess
  | GetVacanciesError
  | CreateFavouriteVacancy
  | DeleteFavouriteVacancy
  | FavouriteVacancySuccess
  | FavouriteVacancyError;
