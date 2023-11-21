import {VacancyAction, VacancyActionType, VacancyState} from "../../types/vacancyTypes";

const initialState = {
    vacancies: null,
    loading: false,
    error: null,
};

export const vacancyReducer = (
    state: VacancyState = initialState,
    action: VacancyAction
): VacancyState => {
    switch (action.type) {
        case VacancyActionType.GET_VACANCIES:
            return { ...state, loading: true, error: null };
        case VacancyActionType.GET_VACANCIES_SUCCESS:
            return { ...state, vacancies: action.payload.response, loading: false };
        case VacancyActionType.GET_VACANCIES_ERROR:
            return { ...state, loading: false, error: action.payload.error };

        default:
            return state;
    }
};
