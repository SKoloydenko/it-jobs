import { UserAction, UserActionType, UserState } from "redux/types/userTypes";

const initialState = {
  user: null,
  loading: false,
  error: null,
};

export const userReducer = (
  state: UserState = initialState,
  action: UserAction,
): UserState => {
  switch (action.type) {
    case UserActionType.RESET_USER:
      return { ...state, user: null };

    case UserActionType.GET_USER:
      return { ...state, loading: true, error: null };
    case UserActionType.GET_USER_SUCCESS:
      return { ...state, user: action.payload.response, loading: false };
    case UserActionType.GET_USER_ERROR:
      return { ...state, loading: false, error: action.payload.error };

    default:
      return state;
  }
};
