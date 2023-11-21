import React from "react";
import {Link} from "react-router-dom";
import style from "./Button.module.scss";

export enum ButtonTheme {
	DARK = "dark",
	LIGHT = "light",
	LIGHT_BORDER = "light_border",
}

interface ButtonProps {
	type?: "button" | "submit" | "reset" | undefined;
	link?: string;
	target?: string;
	onClick?: (event?: any) => void;
	theme: ButtonTheme;
	text: string;
}

const Button: React.FC<ButtonProps> = ({
	type,
	link,
	target,
	onClick,
	theme,
	text,
}) => {
	const className = `${style.button} ${style[theme]}`;

	if (link) {
		return (
			<Link to={link} className={className} target={target}>
				{text}
			</Link>
		);
	} else if (type === "submit") {
		return <input className={className} type={type} value={text} />;
	} else {
		return (
			<button className={className} type={type} onClick={onClick}>
				{text}
			</button>
		);
	}
};

export default Button;
