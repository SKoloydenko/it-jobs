export interface FiltersState {
  programmingLanguage: string | null;
  minSalary: number | null;
  maxSalary: number | null;
  aggregator: string | null;
}

export enum FiltersActionType {
  RESET = "RESET",

  SET_PROGRAMMING_LANGUAGE = "SET_PROGRAMMING_LANGUAGE",

  SET_MIN_SALARY = "SET_MIN_SALARY",
  SET_MAX_SALARY = "SET_MAX_SALARY",

  SET_AGGREGATOR = "SET_AGGREGATOR",
}

interface Reset {
  type: FiltersActionType.RESET;
}

interface SetProgrammingLanguage {
  type: FiltersActionType.SET_PROGRAMMING_LANGUAGE;
  payload: { programmingLanguage: string };
}

interface SetMinSalary {
  type: FiltersActionType.SET_MIN_SALARY;
  payload: { minSalary: number };
}

interface SetMaxSalary {
  type: FiltersActionType.SET_MAX_SALARY;
  payload: { maxSalary: number };
}

interface SetAggregator {
  type: FiltersActionType.SET_AGGREGATOR;
  payload: { aggregator: string };
}

export type FiltersAction =
  | Reset
  | SetProgrammingLanguage
  | SetMinSalary
  | SetMaxSalary
  | SetAggregator;
