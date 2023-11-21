import React from "react";
import style from "./Input.module.scss";

interface InputProps {
	type: string;
	placeholder?: string;
	value?: string;
	setValue: (event: any) => void;
	required?: boolean;
	minLength?: number;
	maxLength?: number;
}

const Input: React.FC<InputProps> = ({
	type,
	placeholder,
	value,
	setValue,
	required = false,
	minLength,
	maxLength,
}) => {
	const pattern =
		type === "email"
			? "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
			"((([0-1]?\\d{1,2}|25[0-5]|2[0-4]\\d)\\.([0-1]?" +
			"\\d{1,2}|25[0-5]|2[0-4]\\d)\\." +
			"([0-1]?\\d{1,2}|25[0-5]|2[0-4]\\d)\\.([0-1]?" +
			"\\d{1,2}|25[0-5]|2[0-4]\\d))|" +
			"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
			: undefined;

	return (
		<input
			type={type}
			className={style.input}
			placeholder={placeholder}
			autoCorrect="off"
			autoComplete="off"
			spellCheck="false"
			value={value}
			onChange={setValue}
			required={required}
			pattern={pattern}
			minLength={minLength}
			maxLength={maxLength}
		/>
	);
};

export default Input;
