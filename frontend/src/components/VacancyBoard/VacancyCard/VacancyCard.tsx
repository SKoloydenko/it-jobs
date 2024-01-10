import React from "react";
import style from "./VacancyCard.module.scss";
import Button from "../../Button";
import { ButtonTheme } from "../../Button/Button";
import { ProgrammingLanguage } from "../../../utils/ProgrammingLanguage";
import Icon from "../../Icon";
import { useTypedSelector } from "../../../hooks/useTypedSelector";
import Favourite from "./Favourite";

interface VacancyCardProps {
  id: number;
  programmingLanguage: ProgrammingLanguage;
  title: string;
  minSalary: number;
  maxSalary: number;
  employer: string;
  url: string;
  favourite: boolean | null;
}

const getSalary = (minSalary: number, maxSalary: number): string | null => {
  if (minSalary && maxSalary) {
    return `${minSalary} - ${maxSalary}`;
  } else if (minSalary && !maxSalary) {
    return String(minSalary);
  } else if (!minSalary && maxSalary) {
    return String(maxSalary);
  } else {
    return null;
  }
};

const VacancyCard: React.FC<VacancyCardProps> = ({
  id,
  programmingLanguage,
  title,
  minSalary,
  maxSalary,
  employer,
  url,
  favourite,
}) => {
  const { user } = useTypedSelector((state) => state.user);
  const salary = getSalary(minSalary, maxSalary);

  return (
    <div key={id} className={style.container}>
      <div className={style.top}>
        <Icon programmingLanguage={programmingLanguage} />

        <div className={style.content}>
          <div className={style.title}>{title}</div>
          {salary && <div className={style.salary}>Зарплата: {salary}</div>}
          <div className={style.employer}>Работодатель: {employer}</div>
        </div>
      </div>
      <div className={style.bottom}>
        <Button
          type="button"
          theme={ButtonTheme.DARK}
          text="Перейти к вакансии"
          link={url}
          target="_blank"
        />
        {user && (
          <div>
            <Favourite vacancyId={id} favourite={favourite} />
          </div>
        )}
      </div>
    </div>
  );
};

export default VacancyCard;
