import { AuthAction, AuthActionType, AuthState } from "redux/types/authTypes";

const initialState = {
  accessToken: null,
  loading: false,
  error: null,
};

export const authReducer = (
  state: AuthState = initialState,
  action: AuthAction,
): AuthState => {
  switch (action.type) {
    case AuthActionType.RESET_ERROR:
      return { ...state, error: null };

    case AuthActionType.REGISTER:
      return { ...state, loading: true, error: null };
    case AuthActionType.REGISTER_SUCCESS:
      return { ...state, loading: false };
    case AuthActionType.REGISTER_ERROR:
      return { ...state, loading: false, error: action.payload.error };

    case AuthActionType.LOGIN:
      return { ...state, loading: true, error: null };
    case AuthActionType.LOGIN_SUCCESS:
      return {
        ...state,
        accessToken: action.payload.accessToken,
        loading: false,
      };
    case AuthActionType.LOGIN_ERROR:
      return { ...state, loading: false, error: action.payload.error };

    case AuthActionType.LOGOUT:
      return { ...state, loading: true, error: null };
    case AuthActionType.LOGOUT_SUCCESS:
      return { ...state, loading: false };
    case AuthActionType.LOGOUT_ERROR:
      return { ...state, loading: false, error: action.payload.error };

    default:
      return state;
  }
};
