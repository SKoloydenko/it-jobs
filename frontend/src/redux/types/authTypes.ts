export interface AuthState {
  accessToken: string | null;
  loading: boolean;
  error: string | null;
}

export enum AuthActionType {
  RESET_ERROR = "RESET_ERROR",

  REGISTER = "REGISTER",
  REGISTER_SUCCESS = "REGISTER_SUCCESS",
  REGISTER_ERROR = "REGISTER_ERROR",

  LOGIN = "LOGIN",
  LOGIN_SUCCESS = "LOGIN_SUCCESS",
  LOGIN_ERROR = "LOGIN_ERROR",

  LOGOUT = "LOGOUT",
  LOGOUT_SUCCESS = "LOGOUT_SUCCESS",
  LOGOUT_ERROR = "LOGOUT_ERROR",

  SET_ACCESS_TOKEN = "SET_ACCESS_TOKEN",
}

interface ResetError {
  type: AuthActionType.RESET_ERROR;
}

interface Register {
  type: AuthActionType.REGISTER;
}

interface RegisterSuccess {
  type: AuthActionType.REGISTER_SUCCESS;
}
interface RegisterError {
  type: AuthActionType.REGISTER_ERROR;
  payload: { error: string };
}

interface Login {
  type: AuthActionType.LOGIN;
}

interface LoginSuccess {
  type: AuthActionType.LOGIN_SUCCESS;
  payload: { accessToken: string };
}
interface LoginError {
  type: AuthActionType.LOGIN_ERROR;
  payload: { error: string };
}

interface Logout {
  type: AuthActionType.LOGOUT;
}

interface LogoutSuccess {
  type: AuthActionType.LOGOUT_SUCCESS;
}
interface LogoutError {
  type: AuthActionType.LOGOUT_ERROR;
  payload: { error: string };
}

interface SetAccessToken {
  type: AuthActionType.SET_ACCESS_TOKEN;
  payload: { accessToken: string };
}

export type AuthAction =
  | ResetError
  | Register
  | RegisterSuccess
  | RegisterError
  | Login
  | LoginSuccess
  | LoginError
  | Logout
  | LogoutSuccess
  | LogoutError
  | SetAccessToken;

export const AuthErrorType: Record<string, string> = {
  USER_ALREADY_EXISTS: "Такой пользователь уже существует",
  INVALID_FIELD: "Некорректное поле",
  UNAUTHORIZED: "Некорректные данные",
};
