import {combineReducers} from "redux";
import {authReducer} from "./authReducer";
import {userReducer} from "./userReducer";
import {vacancyReducer} from "./vacancyReducer";

export const rootReducer = combineReducers({
	auth: authReducer,
	user: userReducer,
	vacancy: vacancyReducer,
});

export type RootState = ReturnType<typeof rootReducer>;
