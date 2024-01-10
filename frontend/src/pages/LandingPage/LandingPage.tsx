import React from "react";
import { Button, Header, Spacer } from "components";
import { ButtonTheme } from "components/Button/Button";
import { useActions } from "hooks/useActions";
import { useTypedSelector } from "hooks/useTypedSelector";
import style from "./LandingPage.module.scss";
import { SpacerAxis } from "../../components/Spacer/Spacer";
import VacancyBoard from "../../components/VacancyBoard";

const LandingPage: React.FC = () => {
  const { user } = useTypedSelector((state) => state.user);
  const { logout } = useActions();

  return (
    <div className={style.container}>
      <Header>
        {!user && (
          <div className={style.button}>
            <Button theme={ButtonTheme.DARK} text="Войти" link="/login" />
          </div>
        )}
        {!user && (
          <div className={style.button}>
            <Button
              theme={ButtonTheme.LIGHT}
              text="Регистрация"
              link="/register"
            />
          </div>
        )}
        {user && (
          <div className={style.button}>
            <Button theme={ButtonTheme.DARK} text="Выйти" onClick={logout} />
          </div>
        )}
      </Header>

      <div className={style.section}>
        <Spacer size={92} axis={SpacerAxis.VERTICAL} />
        <VacancyBoard />
        <Spacer size={64} axis={SpacerAxis.VERTICAL} />
      </div>
    </div>
  );
};

export default LandingPage;
