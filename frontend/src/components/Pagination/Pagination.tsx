import React from "react";
import style from "./Pagination.module.scss";

interface PaginationProps {
  page: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({
  page,
  totalPages,
  onPageChange,
}) => {
  const paginationNumbers = [];

  for (let i = 1; i <= totalPages; i++) {
    paginationNumbers.push(i);
  }

  return (
    <div className={style.container}>
      {page !== 1 && (
        <button className={style.button} onClick={() => onPageChange(page - 1)}>
          &lt;
        </button>
      )}
      {paginationNumbers.map((pageNumber) => (
        <button
          className={page === pageNumber ? style.active : style.disabled}
          key={pageNumber}
          onClick={() => onPageChange(pageNumber)}
        >
          {pageNumber}
        </button>
      ))}
      {page !== totalPages && (
        <button className={style.button} onClick={() => onPageChange(page + 1)}>
          &gt;
        </button>
      )}
    </div>
  );
};

export default Pagination;
