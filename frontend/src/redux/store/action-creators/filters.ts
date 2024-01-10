import { Dispatch } from "redux";
import { FiltersAction, FiltersActionType } from "../../types/filtersTypes";

export const reset = () => {
  return (dispatch: Dispatch<FiltersAction>) => {
    dispatch({ type: FiltersActionType.RESET });
  };
};

export const setProgrammingLanguage = (programmingLanguage: string) => {
  return (dispatch: Dispatch<FiltersAction>) => {
    dispatch({
      type: FiltersActionType.SET_PROGRAMMING_LANGUAGE,
      payload: { programmingLanguage },
    });
  };
};

export const setMinSalary = (minSalary: number) => {
  return (dispatch: Dispatch<FiltersAction>) => {
    dispatch({
      type: FiltersActionType.SET_MIN_SALARY,
      payload: { minSalary },
    });
  };
};

export const setMaxSalary = (maxSalary: number) => {
  return (dispatch: Dispatch<FiltersAction>) => {
    dispatch({
      type: FiltersActionType.SET_MAX_SALARY,
      payload: { maxSalary },
    });
  };
};

export const setAggregator = (aggregator: string) => {
  return (dispatch: Dispatch<FiltersAction>) => {
    dispatch({
      type: FiltersActionType.SET_AGGREGATOR,
      payload: { aggregator },
    });
  };
};
