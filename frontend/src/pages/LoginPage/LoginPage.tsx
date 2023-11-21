import React, {useEffect} from "react";
import {Button, Header, LoginForm} from "components";
import {ButtonTheme} from "components/Button/Button";
import {useActions} from "hooks/useActions";
import style from "./LoginPage.module.scss";

const LoginPage: React.FC = () => {
	const { resetAuthError } = useActions();

	useEffect(() => {
		resetAuthError();
	}, []);

	return (
		<div className={style.container}>
			<Header>
				{
					<div className={style.button}>
						<Button
							theme={ButtonTheme.LIGHT}
							text="Регистрация"
							link="/register"
						/>
					</div>
				}
			</Header>
			<div className={style.form}>
				<LoginForm />
			</div>
		</div>
	);
};

export default LoginPage;
