import * as authActionCreators from "./auth";
import * as userActionCreators from "./user";
import * as vacancyActionCreators from "./vacancy";

const actionCreators = {
	...authActionCreators,
	...userActionCreators,
	...vacancyActionCreators,
};

export default actionCreators;
