import style from "./Favourite.module.scss";
import { useActions } from "../../../../hooks/useActions";
import React from "react";

interface FavouriteProps {
  vacancyId: number;
  favourite: boolean | null;
}

const Favourite: React.FC<FavouriteProps> = ({ vacancyId, favourite }) => {
  const { createFavouriteVacancy, deleteFavouriteVacancy } = useActions();

  return (
    <div>
      {favourite && (
        <img
          src={`/favourite_enabled.png`}
          alt=""
          className={style.enabled}
          onClick={() => {
            deleteFavouriteVacancy(vacancyId);
          }}
        />
      )}
      {!favourite && (
        <img
          src={`/favourite_disabled.png`}
          alt=""
          className={style.disabled}
          onClick={() => {
            createFavouriteVacancy(vacancyId);
          }}
        />
      )}
    </div>
  );
};

export default Favourite;
