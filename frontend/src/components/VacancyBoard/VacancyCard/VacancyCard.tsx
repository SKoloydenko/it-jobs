import React from "react";
import style from "./VacancyCard.module.scss";
import Button from "../../Button";
import {ButtonTheme} from "../../Button/Button";

interface VacancyCardProps {
	id: number;
	title: string;
	minSalary: number;
	maxSalary: number;
	employer: string;
	url: string;
}

const VacancyCard: React.FC<VacancyCardProps> = ({
	id,
    title,
    minSalary,
    maxSalary,
    employer,
    url,
}) => {

	return (
		<div
			key={id}
			className={style.container}
		>
			<div className={style.top}>
				<div className={style.content}>
                    <div className={style.title}>{title}</div>
					{minSalary !== null && minSalary !== 0 && <div className={style.text}>Минимальная зарплата: {minSalary}</div>}
					{maxSalary !== null && maxSalary !== 0 && <div className={style.text}>Максимальная зарплата: {maxSalary}</div>}
                    <div className={style.text}>Работодатель: {employer}</div>
				</div>
			</div>
			<div className={style.bottom}>
				<Button type="button" theme={ButtonTheme.DARK} text="Перейти к вакансии" link={url} target="_blank" />
			</div>
		</div>
	);
};

export default VacancyCard;
