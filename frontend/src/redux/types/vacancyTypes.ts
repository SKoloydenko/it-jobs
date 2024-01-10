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
  GET_VACANCIES = "GET_VACANCIES",
  GET_VACANCIES_SUCCESS = "GET_VACANCIES_SUCCESS",
  GET_VACANCIES_ERROR = "GET_VACANCIES_ERROR",
}

interface GetVacancies {
  type: VacancyActionType.GET_VACANCIES;
}

interface GetVacanciesSuccess {
  type: VacancyActionType.GET_VACANCIES_SUCCESS;
  payload: { response: VacancyPageResponse };
}

interface GetVacanciesError {
  type: VacancyActionType.GET_VACANCIES_ERROR;
  payload: { error: string };
}

export type VacancyAction =
  | GetVacancies
  | GetVacanciesSuccess
  | GetVacanciesError;
