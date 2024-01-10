import {
  FiltersAction,
  FiltersActionType,
  FiltersState,
} from "../../types/filtersTypes";

const initialState = {
  programmingLanguage: null,
  minSalary: null,
  maxSalary: null,
  aggregator: null,
};

export const filtersReducer = (
  state: FiltersState = initialState,
  action: FiltersAction,
): FiltersState => {
  switch (action.type) {
    case FiltersActionType.RESET:
      return {
        ...state,
        programmingLanguage: null,
        minSalary: null,
        maxSalary: null,
        aggregator: null,
      };

    case FiltersActionType.SET_PROGRAMMING_LANGUAGE:
      return {
        ...state,
        programmingLanguage: action.payload.programmingLanguage,
      };

    case FiltersActionType.SET_MIN_SALARY:
      return { ...state, minSalary: action.payload.minSalary };

    case FiltersActionType.SET_MAX_SALARY:
      return { ...state, maxSalary: action.payload.maxSalary };

    case FiltersActionType.SET_AGGREGATOR:
      return { ...state, aggregator: action.payload.aggregator };

    default:
      return state;
  }
};
