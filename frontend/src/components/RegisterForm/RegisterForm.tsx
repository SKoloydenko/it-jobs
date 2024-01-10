import React, { useState } from "react";
import { Button, Input, MatchingInputs, Message, Spacer } from "components";
import { ButtonTheme } from "../Button/Button";
import { SpacerAxis } from "../Spacer/Spacer";
import { useActions } from "hooks/useActions";
import { useTypedSelector } from "hooks/useTypedSelector";
import { RegisterRequest } from "services/authService";
import style from "./RegisterForm.module.scss";

const RegisterForm: React.FC = () => {
  const { error } = useTypedSelector((state) => state.auth);
  const { register } = useActions();
  const [form, setForm] = useState<RegisterRequest>({
    email: "",
    password: "",
  });
  const [confirmPassword, setConfirmPassword] = useState<string>("");

  const handleRegister = (event: any) => {
    event.preventDefault();
    register(form);
  };

  return (
    <form
      className={`${style.container} ${error ? style.error : ""}`}
      onSubmit={(event) => handleRegister(event)}
    >
      <div className={style.title}>Регистрация</div>
      <Spacer size={24} axis={SpacerAxis.VERTICAL} />
      <Input
        type="email"
        placeholder="Введите email"
        value={form.email}
        setValue={(event) => setForm({ ...form, email: event.target.value })}
        required={true}
      />
      <Spacer size={24} axis={SpacerAxis.VERTICAL} />
      <MatchingInputs
        type="password"
        placeholders={["Введите пароль", "Подтвердите пароль"]}
        input={form.password}
        changeInput={(event) =>
          setForm({ ...form, password: event.target.value })
        }
        matchingInput={confirmPassword}
        changeMatchingInput={(event) => setConfirmPassword(event.target.value)}
        message="Пароли должны совпадать"
        required={true}
      />
      <Message message={error} size={48} />
      <Button
        type="submit"
        theme={ButtonTheme.DARK}
        text="Зарегистрироваться"
      />
    </form>
  );
};

export default RegisterForm;
