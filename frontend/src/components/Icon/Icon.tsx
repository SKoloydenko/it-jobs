import { ProgrammingLanguage } from "../../utils/ProgrammingLanguage";
import React from "react";
import style from "./Icon.module.scss";

interface IconProps {
  programmingLanguage: ProgrammingLanguage;
}

const getIcon = (programmingLanguage: ProgrammingLanguage): any => {
  switch (programmingLanguage) {
    case ProgrammingLanguage.JAVA:
      return { image: "java.png", color: "#F89820" };
    case ProgrammingLanguage.KOTLIN:
      return { image: "kotlin.png", color: "#0095D5" };
    case ProgrammingLanguage.PYTHON:
      return { image: "python.png", color: "#FFE873" };
    case ProgrammingLanguage.CSHARP:
      return { image: "csharp.png", color: "#9B4993" };
    case ProgrammingLanguage.GO:
      return { image: "go.png", color: "#00ADD8" };
    case ProgrammingLanguage.JAVASCRIPT:
      return { image: "javascript.png", color: "#F0DB4F" };
    case ProgrammingLanguage.PHP:
      return { image: "php.png", color: "#777BB3" };
    default:
      return { image: "default.png", color: "#000" };
  }
};

const Icon: React.FC<IconProps> = ({ programmingLanguage }) => {
  const { image, color } = getIcon(programmingLanguage);

  return (
    <div>
      <img
        src={`/${image}`}
        alt=""
        className={style.icon}
        style={{ borderColor: color }}
      />
    </div>
  );
};

export default Icon;
