import React from "react";
import { Input, Spacer } from "components";
import { SpacerAxis } from "components/Spacer/Spacer";
import style from "./MatchingInputs.module.scss";

export interface MatchingInputsProps {
  type: string;
  placeholders: string[];
  input: string;
  changeInput: (event: any) => void;
  matchingInput: string;
  changeMatchingInput: (event: any) => void;
  message: string;
  required?: boolean;
  minLength?: number;
}

const MatchingInputs: React.FC<MatchingInputsProps> = ({
  type,
  placeholders,
  input,
  changeInput,
  matchingInput,
  changeMatchingInput,
  required,
  minLength,
  message,
}) => {
  const validateInputs = (input: string, matchingInput: string) => {
    const element: any = document.getElementsByClassName(style.container)[0]
      .firstElementChild;
    if (input !== matchingInput) {
      element.setCustomValidity(message);
    } else {
      element.setCustomValidity("");
    }
  };

  return (
    <>
      <Input
        type={type}
        placeholder={placeholders[0]}
        value={input}
        setValue={changeInput}
        required={required}
        minLength={minLength}
      />
      <Spacer size={24} axis={SpacerAxis.VERTICAL} />
      <div className={style.container}>
        <Input
          type={type}
          placeholder={placeholders[1]}
          value={matchingInput}
          setValue={(event) => {
            validateInputs(input, event.target.value);
            changeMatchingInput(event);
          }}
          required={required}
          minLength={minLength}
        />
      </div>
    </>
  );
};

export default MatchingInputs;
