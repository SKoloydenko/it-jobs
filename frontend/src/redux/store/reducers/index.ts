import { combineReducers } from "redux";
import { authReducer } from "./authReducer";
import { userReducer } from "./userReducer";
import { vacancyReducer } from "./vacancyReducer";
import { filtersReducer } from "./filtersReducer";

export const rootReducer = combineReducers({
  auth: authReducer,
  user: userReducer,
  vacancy: vacancyReducer,
  filters: filtersReducer,
});

export type RootState = ReturnType<typeof rootReducer>;
