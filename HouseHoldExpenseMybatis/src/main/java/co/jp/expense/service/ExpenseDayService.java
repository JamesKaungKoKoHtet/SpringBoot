package co.jp.expense.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import co.jp.expense.Helper.Helper;
import co.jp.expense.model.ExpenseDay;
import co.jp.expense.model.dto.ExpenseDayDto;
import co.jp.expense.repo.ExpenseDayMapper;
import co.jp.expense.repo.ExpenseMonthMapper;

/**
 * 
 * @author ASUS james
 * Expense Day service will have methods that insert and update the expense day table
 *
 */
@Service
@Transactional
public class ExpenseDayService {
	@Autowired
	ExpenseDayMapper expenseDayMapper;
	@Autowired
	ExpenseMonthMapper expenseMonthMapper;
	@Autowired
	ExpenseMonthService expenseMonthService;

	/**
	 * 
	 * @param edto : ExpenseDayDto will be converted to ExpenseDay object 
	 * @return expense month id to redirect to detail page
	 * Helper class method is used to convert dto to entity
	 * ExpenseMonthService.findExpenseMonth method is used to get the id of date in expenseMonth table
	 * set the id_em into expenseDay entity since it's a FK
	 * Insert expense day and update expense month total 
	 * 
	 */
	// get month id from expense_month and insert into expense_day
	public int addExpense(ExpenseDayDto edto) {
		ExpenseDay ed = Helper.dtoToEntity(edto);
		int id_em = this.expenseMonthService.findExpenseMonth(ed);
		ed.setId_em(id_em);
		try {
			this.expenseDayMapper.insertExpenseDay(ed);
			this.expenseMonthMapper.updateExpenseMonthTotal(id_em);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
		return id_em;

	}
	
	/**
	 * 
	 * @param id : id from ExpenseMonth List (index) view 
	 * @return List of ExpenseDayDto
	 * Helper methods are used to change data format
	 */

	public List<ExpenseDayDto> getExpenseDayList(int id) {
		List<ExpenseDayDto> dto = new ArrayList<>();
		List<ExpenseDay> ed = this.expenseDayMapper.getListByIdEm(id);
		for (ExpenseDay e : ed) {
			ExpenseDayDto d = new ExpenseDayDto();
			d.setAmount(Helper.currencyFomat(e.getAmount()));
			d.setDate(Helper.dateFomatDetailList(e.getDate()));
			d.setDetail(e.getDetail());
			d.setId_ed(e.getId_ed());
			dto.add(d);
		}
		return dto;
	}

	/**
	 * 
	 * @param id : id from ExpenseDay list 
	 * 
	 * @return return ExpenseDay object
	 */
	public ExpenseDay getExpenseDayById(int id) {
		return this.expenseDayMapper.getExpenseDayByIdEd(id);
	}

	/**
	 * 
	 * @param edto : ExpenseDayDto object from ExpenseDayList view
	 * Helper method is used to convert from dto to entity
	 */
	public void updateExpense(ExpenseDayDto edto) {
		ExpenseDay ed = Helper.dtoToEntity(edto);
		int check = this.expenseDayMapper.updateExpenseDay(ed);
		if (check > 0) {
			this.expenseMonthMapper.updateExpenseMonthTotal(ed.getId_em());
		} else {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}

	}

	/**
	 * 
	 * @param id_em : id of ExpenseMonth from detail view
	 * @return double data sum of amount in expenseDay tablae
	 */
	public double getTotal(int id_em) {
		return this.expenseDayMapper.getTotalByIdEm(id_em);
	}

}
