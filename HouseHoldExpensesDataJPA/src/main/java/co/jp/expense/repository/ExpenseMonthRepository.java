package co.jp.expense.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.jp.expense.entity.ExpenseMonth;

public interface ExpenseMonthRepository extends JpaRepository<ExpenseMonth, Integer> {

	@Query(value = "SELECT em.id_em FROM expense_month em WHERE EXTRACT (MONTH FROM em.date) = :month and EXTRACT(YEAR FROM em.date) = :year", nativeQuery=true)
	int findByMonthAndYear(@Param("year") int year ,@Param("month") int month);

}
