import {Dispatch} from "redux";
import VacancyService from "../../../services/vacancyService";
import {VacancyAction, VacancyActionType} from "../../types/vacancyTypes";

export const getVacancies = () => {
    return async (dispatch: Dispatch<VacancyAction>) => {
        try {
            dispatch({ type: VacancyActionType.GET_VACANCIES });
            const response = await VacancyService.getVacancies();
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

const getVacancyError = () => {
    return "Ошибка";
};
