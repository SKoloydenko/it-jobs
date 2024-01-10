import * as authActionCreators from "./auth";
import * as userActionCreators from "./user";
import * as vacancyActionCreators from "./vacancy";
import * as filtersActionCreators from "./filters";

const actionCreators = {
  ...authActionCreators,
  ...userActionCreators,
  ...vacancyActionCreators,
  ...filtersActionCreators,
};

export default actionCreators;
