import { Dispatch } from "redux";
import VacancyService from "../../../services/vacancyService";
import { VacancyAction, VacancyActionType } from "../../types/vacancyTypes";
import { store } from "../index";

export const getVacanciesForPublic = (page?: number) => {
  return async (dispatch: Dispatch<VacancyAction>) => {
    if (!page) {
      page = store.getState().vacancy.vacancies?.page;
    }
    const { programmingLanguage, minSalary, maxSalary, aggregator } =
      store.getState().filters;
    try {
      dispatch({ type: VacancyActionType.GET_VACANCIES_FOR_PUBLIC });
      const response = await VacancyService.getVacanciesForPublic(
        page ? page : 1,
        programmingLanguage,
        minSalary,
        maxSalary,
        aggregator,
      );
      dispatch({
        type: VacancyActionType.GET_VACANCIES_SUCCESS,
        payload: { response: response.data },
      });
    } catch (error: any) {
      dispatch({
        type: VacancyActionType.GET_VACANCIES_ERROR,
        payload: { error: getVacancyError() },
      });
    }
  };
};

export const getVacanciesForUser = (page?: number) => {
  return async (dispatch: Dispatch<VacancyAction>) => {
    if (!page) {
      page = store.getState().vacancy.vacancies?.page;
    }
    const { programmingLanguage, minSalary, maxSalary, aggregator } =
      store.getState().filters;
    try {
      dispatch({ type: VacancyActionType.GET_VACANCIES_FOR_USER });
      const response = await VacancyService.getVacanciesForUser(
        page ? page : 1,
        programmingLanguage,
        minSalary,
        maxSalary,
        aggregator,
      );
      dispatch({
        type: VacancyActionType.GET_VACANCIES_SUCCESS,
        payload: { response: response.data },
      });
    } catch (error: any) {
      dispatch({
        type: VacancyActionType.GET_VACANCIES_ERROR,
        payload: { error: getVacancyError() },
      });
    }
  };
};

export const createFavouriteVacancy = (vacancyId: number) => {
  return async (dispatch: Dispatch<VacancyAction>) => {
    try {
      dispatch({ type: VacancyActionType.CREATE_FAVOURITE_VACANCY });
      await VacancyService.createFavouriteVacancy(vacancyId);
      dispatch({
        type: VacancyActionType.FAVOURITE_VACANCY_SUCCESS,
      });

      const page = store.getState().vacancy.vacancies?.page;
      const { programmingLanguage, minSalary, maxSalary, aggregator } =
        store.getState().filters;
      try {
        dispatch({ type: VacancyActionType.GET_VACANCIES_FOR_USER });
        const response = await VacancyService.getVacanciesForUser(
          page ? page : 1,
          programmingLanguage,
          minSalary,
          maxSalary,
          aggregator,
        );
        dispatch({
          type: VacancyActionType.GET_VACANCIES_SUCCESS,
          payload: { response: response.data },
        });
      } catch (error: any) {
        dispatch({
          type: VacancyActionType.GET_VACANCIES_ERROR,
          payload: { error: getVacancyError() },
        });
      }
    } catch (error: any) {
      dispatch({
        type: VacancyActionType.FAVOURITE_VACANCY_ERROR,
        payload: { error: getVacancyError() },
      });
    }
  };
};

export const deleteFavouriteVacancy = (vacancyId: number) => {
  return async (dispatch: Dispatch<VacancyAction>) => {
    try {
      dispatch({ type: VacancyActionType.DELETE_FAVOURITE_VACANCY });
      await VacancyService.deleteFavouriteVacancy(vacancyId);
      dispatch({
        type: VacancyActionType.FAVOURITE_VACANCY_SUCCESS,
      });

      const page = store.getState().vacancy.vacancies?.page;
      const { programmingLanguage, minSalary, maxSalary, aggregator } =
        store.getState().filters;
      try {
        dispatch({ type: VacancyActionType.GET_VACANCIES_FOR_USER });
        const response = await VacancyService.getVacanciesForUser(
          page ? page : 1,
          programmingLanguage,
          minSalary,
          maxSalary,
          aggregator,
        );
        dispatch({
          type: VacancyActionType.GET_VACANCIES_SUCCESS,
          payload: { response: response.data },
        });
      } catch (error: any) {
        dispatch({
          type: VacancyActionType.GET_VACANCIES_ERROR,
          payload: { error: getVacancyError() },
        });
      }
    } catch (error: any) {
      dispatch({
        type: VacancyActionType.FAVOURITE_VACANCY_ERROR,
        payload: { error: getVacancyError() },
      });
    }
  };
};

const getVacancyError = () => {
  return "Ошибка";
};
