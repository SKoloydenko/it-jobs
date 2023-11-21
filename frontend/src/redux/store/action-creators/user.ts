import {Dispatch} from "redux";
import {UserAction, UserActionType,} from "redux/types/userTypes";
import UserService from "services/userService";

export const getCurrentUser = () => {
	return async (dispatch: Dispatch<UserAction>) => {
		try {
			dispatch({ type: UserActionType.GET_USER });
			const response = await UserService.getCurrentUser();
			dispatch({
				type: UserActionType.GET_USER_SUCCESS,
				payload: { response: response.data },
			});
		} catch (error: any) {
			dispatch({
				type: UserActionType.GET_USER_ERROR,
				payload: { error: getUserError() },
			});
		}
	};
};

export const resetUser = () => ({
	type: UserActionType.RESET_USER,
});

const getUserError = () => {
	return "Ошибка";
};
