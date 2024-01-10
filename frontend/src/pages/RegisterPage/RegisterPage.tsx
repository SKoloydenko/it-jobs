import React, { useEffect } from "react";
import { Button, Header, RegisterForm } from "components";
import { ButtonTheme } from "components/Button/Button";
import style from "./RegisterPage.module.scss";
import { useActions } from "hooks/useActions";

const RegisterPage: React.FC = () => {
  const { resetAuthError } = useActions();

  useEffect(() => {
    resetAuthError();
  }, []);

  return (
    <div className={style.container}>
      <Header>
        <div className={style.button}>
          <Button theme={ButtonTheme.DARK} text="Войти" link="/login" />
        </div>
      </Header>
      <div className={style.form}>
        <RegisterForm />
      </div>
    </div>
  );
};

export default RegisterPage;
