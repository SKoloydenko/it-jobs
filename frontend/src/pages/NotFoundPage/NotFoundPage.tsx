import React from "react";
import {Link} from "react-router-dom";
import style from "./NotFoundPage.module.scss";

const NotFoundPage: React.FC = () => {
	return (
		<div className={style.container}>
			<div className={style.mainTitle}>404</div>
			<div className={style.title}>Страница не найдена!</div>
			<Link to="/" className={style.link}>
				Вернуться на главную
			</Link>
		</div>
	);
};

export default NotFoundPage;
