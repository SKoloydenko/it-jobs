import React from "react";
import style from "./Filters.module.scss";
import { useTypedSelector } from "../../hooks/useTypedSelector";
import { Aggregator } from "../../utils/Aggregator";
import { useActions } from "../../hooks/useActions";
import { ProgrammingLanguage } from "../../utils/ProgrammingLanguage";
import Button from "../Button";
import { ButtonTheme } from "../Button/Button";
import Input from "../Input";

const Filters: React.FC = () => {
  const { programmingLanguage, minSalary, maxSalary, aggregator } =
    useTypedSelector((state) => state.filters);
  const {
    reset,
    setProgrammingLanguage,
    setMinSalary,
    setMaxSalary,
    setAggregator,
    getVacancies,
  } = useActions();

  return (
    <div className={style.container}>
      <div className={style.title}>Фильтры</div>
      <div className={style.filters}>
        <div className={style.filter}>
          <div className={style.text}>Язык программирования</div>
          <div className={style.options}>
            {Object.keys(ProgrammingLanguage).map(
              (programmingLanguageValue) => (
                <button
                  key={programmingLanguageValue}
                  className={
                    programmingLanguageValue === programmingLanguage
                      ? style.active
                      : style.disabled
                  }
                  onClick={() =>
                    setProgrammingLanguage(programmingLanguageValue)
                  }
                >
                  {programmingLanguageValue}
                </button>
              ),
            )}
          </div>
        </div>
        <div className={style.filter}>
          <div className={style.text}>Зарплата</div>
          <Input
            type="number"
            placeholder="Минимальная зарплата"
            setValue={(event) => setMinSalary(event.target.value)}
          />
          <Input
            type="number"
            placeholder="Максимальная зарплата"
            setValue={(event) => setMaxSalary(event.target.value)}
          />
        </div>
        <div className={style.filter}>
          <div className={style.text}>Агрегатор</div>
          <div className={style.options}>
            {Object.entries(Aggregator).map(
              ([aggregatorKey, aggregatorValue]) => (
                <button
                  key={aggregatorKey}
                  className={
                    aggregatorKey === aggregator ? style.active : style.disabled
                  }
                  onClick={() => setAggregator(aggregatorKey)}
                >
                  {aggregatorValue}
                </button>
              ),
            )}
          </div>
        </div>
      </div>
      <div className={style.buttons}>
        <Button
          text="Применить"
          theme={ButtonTheme.DARK}
          onClick={() => {
            getVacancies(
              1,
              programmingLanguage,
              minSalary,
              maxSalary,
              aggregator,
            );
          }}
        />
        <Button
          text="Сброс"
          theme={ButtonTheme.LIGHT_BORDER}
          onClick={() => {
            reset();
            getVacancies(1);
          }}
        />
      </div>
    </div>
  );
};

export default Filters;
