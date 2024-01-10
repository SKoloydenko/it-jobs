import React from "react";
import { Spacer } from "components";
import { SpacerAxis } from "components/Spacer/Spacer";
import style from "./Message.module.scss";

interface MessageProps {
  message: string | null;
  size: number;
}

const Message: React.FC<MessageProps> = ({ message, size }) => {
  if (message) {
    return (
      <span className={style.message} style={{ height: size }}>
        {message}
      </span>
    );
  } else {
    return <Spacer size={size} axis={SpacerAxis.VERTICAL} />;
  }
};

export default Message;
