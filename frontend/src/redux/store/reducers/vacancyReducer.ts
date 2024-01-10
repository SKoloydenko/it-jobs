import {
  VacancyAction,
  VacancyActionType,
  VacancyState,
} from "../../types/vacancyTypes";

const initialState = {
  vacancies: null,
  loading: false,
  error: null,
};

export const vacancyReducer = (
  state: VacancyState = initialState,
  action: VacancyAction,
): VacancyState => {
  switch (action.type) {
    case VacancyActionType.GET_VACANCIES_FOR_PUBLIC:
      return { ...state, loading: true, error: null };
    case VacancyActionType.GET_VACANCIES_FOR_USER:
      return { ...state, loading: true, error: null };
    case VacancyActionType.GET_VACANCIES_SUCCESS:
      return { ...state, vacancies: action.payload.response, loading: false };
    case VacancyActionType.GET_VACANCIES_ERROR:
      return { ...state, loading: false, error: action.payload.error };
    case VacancyActionType.CREATE_FAVOURITE_VACANCY:
      return { ...state, loading: true, error: null };
    case VacancyActionType.DELETE_FAVOURITE_VACANCY:
      return { ...state, loading: true, error: null };
    case VacancyActionType.FAVOURITE_VACANCY_SUCCESS:
      return { ...state, loading: false };
    case VacancyActionType.FAVOURITE_VACANCY_ERROR:
      return { ...state, loading: false, error: action.payload.error };

    default:
      return state;
  }
};
