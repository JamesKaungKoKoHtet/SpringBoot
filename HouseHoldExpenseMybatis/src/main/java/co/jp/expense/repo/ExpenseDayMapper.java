package co.jp.expense.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.jp.expense.model.ExpenseDay;

/**
 * 
 * @author ASUS james
 *
 */
@Mapper
public interface ExpenseDayMapper {

	public int insertExpenseDay(ExpenseDay ed);

	public List<ExpenseDay> getListByIdEm(@Param("id_em") int id_em);

	public ExpenseDay getExpenseDayByIdEd(@Param("id_ed") int id_ed);

	public int updateExpenseDay(ExpenseDay ed);

	public double getTotalByIdEm(int id_em);

}
