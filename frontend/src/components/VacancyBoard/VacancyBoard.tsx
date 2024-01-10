import React, { useEffect, useState } from "react";
import { Filters, Loader, Pagination, Spacer } from "components";
import { useActions } from "hooks/useActions";
import { useTypedSelector } from "hooks/useTypedSelector";
import style from "./VacancyBoard.module.scss";
import VacancyCard from "./VacancyCard";
import { SpacerAxis } from "../Spacer/Spacer";

const VacancyBoard: React.FC = () => {
  const [page, setPage] = useState(1);
  const { vacancies, loading } = useTypedSelector((state) => state.vacancy);
  const { getVacancies } = useActions();

  useEffect(() => {
    getVacancies(page);
  }, [page]);

  if (loading) {
    return (
      <div style={{ position: "absolute", top: "45%", left: "45%" }}>
        <Loader />
      </div>
    );
  }

  const onPageChange = (page: number) => setPage(page);

  return (
    <div className={style.container}>
      <Filters />
      <div className={style.content}>
        {vacancies?.content.map((vacancy) => (
          <VacancyCard
            key={vacancy.id}
            id={vacancy.id}
            programmingLanguage={vacancy.programmingLanguage}
            title={vacancy.title}
            minSalary={vacancy.minSalary}
            maxSalary={vacancy.maxSalary}
            employer={vacancy.employer}
            url={vacancy.url}
          />
        ))}
        <Spacer size={24} axis={SpacerAxis.VERTICAL} />
        {vacancies && (
          <Pagination
            page={page}
            totalPages={vacancies?.totalPages}
            onPageChange={onPageChange}
          />
        )}
      </div>
    </div>
  );
};

export default VacancyBoard;
