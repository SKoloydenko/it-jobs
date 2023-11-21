import React from "react";
import {Link} from "react-router-dom";
import style from "./Header.module.scss";

interface HeaderProps {
	children?: React.ReactNode;
}

const Header: React.FC<HeaderProps> = ({ children }) => {
	return (
		<header className={style.container}>
			<Link to={"/"} className={style.title}>
				IT-Jobs
			</Link>
			<div className={style.buttons}>{children}</div>
		</header>
	);
};

export default Header;
