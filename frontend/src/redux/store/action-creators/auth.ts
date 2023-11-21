import {Dispatch} from "redux";
import {AuthAction, AuthActionType,} from "redux/types/authTypes";
import {getCurrentUser, resetUser} from "./user";
import AuthService, {LoginRequest, RegisterRequest,} from "services/authService";

export const register = (registerRequest: RegisterRequest) => {
	return async (dispatch: Dispatch<AuthAction>) => {
		try {
			dispatch({ type: AuthActionType.REGISTER });
			await AuthService.register(registerRequest);
			dispatch({
				type: AuthActionType.REGISTER_SUCCESS,
			});
		} catch (error: any) {
			dispatch({
				type: AuthActionType.REGISTER_ERROR,
				payload: { error: getAuthError() },
			});
		}
	};
};

export const login = (loginRequest: LoginRequest) => {
	return async (dispatch: any) => {
		try {
			dispatch({ type: AuthActionType.LOGIN });
			const response = await AuthService.login(loginRequest);
			dispatch({
				type: AuthActionType.LOGIN_SUCCESS,
				payload: response.data
			});
			dispatch(getCurrentUser());
		} catch (error: any) {
			dispatch({
				type: AuthActionType.LOGIN_ERROR,
				payload: { error: getAuthError() },
			});
		}
	};
};

export const logout = () => {
	return async (dispatch: any) => {
		try {
			dispatch({ type: AuthActionType.LOGOUT });
			await AuthService.logout();
			dispatch({
				type: AuthActionType.LOGOUT_SUCCESS,
			});
			dispatch(resetUser());
		} catch (error: any) {
			dispatch({
				type: AuthActionType.LOGOUT_ERROR,
				payload: { error: getAuthError() },
			});
		}
	};
};

export const resetAuthError = () => ({
	type: AuthActionType.RESET_ERROR,
});

const getAuthError = () => {
	return "Ошибка";
};
