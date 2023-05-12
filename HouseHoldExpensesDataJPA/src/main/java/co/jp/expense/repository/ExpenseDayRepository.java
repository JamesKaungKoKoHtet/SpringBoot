package co.jp.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.jp.expense.entity.ExpenseDay;

public interface ExpenseDayRepository extends JpaRepository<ExpenseDay, Integer> {

	@Query(value = "SELECT * from expense_day where id_em =:id_em", nativeQuery = true)
	List<ExpenseDay> findByMonthId(@Param("id_em") int id);
	
	@Query(value = "SELECT  sum(amount)  from expense_day where id_em =:id_em", nativeQuery = true)
	double getTotalByMonthId(@Param("id_em") int id);

}
