import React, {useEffect} from "react";
import {Loader} from "components";
import {useActions} from "hooks/useActions";
import {useTypedSelector} from "hooks/useTypedSelector";
import style from "./VacancyBoard.module.scss";
import VacancyCard from "./VacancyCard";

const VacancyBoard: React.FC = () => {
	const { vacancies, loading } = useTypedSelector((state) => state.vacancy);
	const { getVacancies } = useActions();

	useEffect(() => {
		if (vacancies == null) {
			getVacancies();
		}
	}, []);

	if (loading) {
		return <Loader />;
	}

	return (
		<div className={style.container}>
			<div className={style.board}>
				{vacancies?.content.map((vacancy) => (
					<VacancyCard
						key={vacancy.id}
						id={vacancy.id}
						title={vacancy.title}
						minSalary={vacancy.minSalary}
						maxSalary={vacancy.maxSalary}
						employer={vacancy.employer}
						url={vacancy.url}
					/>
				))}
			</div>
		</div>
	);
};

export default VacancyBoard;
