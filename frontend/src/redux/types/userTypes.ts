export interface UserResponse {
  userId?: number;
  email: string;
}

export interface UserState {
  user: UserResponse | null;
  loading: boolean;
  error: string | null;
}

export enum UserActionType {
  RESET_USER = "RESET_USER",

  GET_USER = "GET_USER",
  GET_USER_SUCCESS = "GET_USER_SUCCESS",
  GET_USER_ERROR = "GET_USER_ERROR",
}

interface ResetUser {
  type: UserActionType.RESET_USER;
}

interface GetUser {
  type: UserActionType.GET_USER;
}

interface GetUserSuccess {
  type: UserActionType.GET_USER_SUCCESS;
  payload: { response: UserResponse };
}

interface GetUserError {
  type: UserActionType.GET_USER_ERROR;
  payload: { error: string };
}

export type UserAction = ResetUser | GetUser | GetUserSuccess | GetUserError;

export const UserErrorType: Record<string, string> = {
  USER_DOES_NOT_EXIST: "Такого пользователя не существует",
  USER_ALREADY_EXISTS: "Такой пользователь уже существует",
  INVALID_FIELD: "Некорректное поле",
  FORBIDDEN: "Доступ запрещен",
  UNAUTHORIZED: "Требуется авторизация",
};
