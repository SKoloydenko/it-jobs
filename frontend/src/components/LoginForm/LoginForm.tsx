import React, {useState} from "react";
import {Button, Input, Message, Spacer} from "components";
import {ButtonTheme} from "components/Button/Button";
import {SpacerAxis} from "components/Spacer/Spacer";
import {useActions} from "hooks/useActions";
import {useTypedSelector} from "hooks/useTypedSelector";
import {LoginRequest} from "services/authService";
import style from "./LoginForm.module.scss";

const LoginForm: React.FC = () => {
	const { error } = useTypedSelector((state) => state.auth);
	const { login } = useActions();
	const [form, setForm] = useState<LoginRequest>({
		email: "",
		password: "",
	});

	const handleLogin = (event: any) => {
		event.preventDefault();
		login(form);
	};

	return (
		<form
			className={`${style.container} ${error ? style.error : ""}`}
			onSubmit={(event) => handleLogin(event)}
		>
			<div className={style.title}>Войти</div>
			<Spacer size={24} axis={SpacerAxis.VERTICAL} />
			<Input
				type="email"
				placeholder="Введите email"
				value={form.email}
				setValue={(event) => setForm({ ...form, email: event.target.value })}
				required={true}
			/>
			<Spacer size={24} axis={SpacerAxis.VERTICAL} />
			<Input
				type="password"
				placeholder="Введите пароль"
				value={form.password}
				setValue={(event) => setForm({ ...form, password: event.target.value })}
				required={true}
				minLength={8}
			/>
			<Message message={error} size={48} />
			<Button type="submit" theme={ButtonTheme.DARK} text="Войти" />
		</form>
	);
};

export default LoginForm;
