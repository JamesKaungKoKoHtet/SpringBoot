package co.jp.expense.repo;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.jp.expense.model.ExpenseMonth;
import co.jp.expense.model.dto.ExpenseMonthDto;
/**
 * 
 * @author ASUS james
 *
 */
@Mapper
public interface ExpenseMonthMapper {

	public List<ExpenseMonth> getList();

	public int findYearAndMonth( int month,int year);

	public void insertExpenseMonth(LocalDate date);

	public void updateExpenseMonthTotal(@Param("id_em")int id_em);

}
