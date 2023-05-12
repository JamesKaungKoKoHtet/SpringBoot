package co.jp.expense.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jp.expense.Helper.Helper;
import co.jp.expense.model.ExpenseDay;
import co.jp.expense.model.ExpenseMonth;
import co.jp.expense.model.dto.ExpenseMonthDto;
import co.jp.expense.repo.ExpenseMonthMapper;

/**
 * 
 * @author ASUS james
 *
 */
@Service
@Transactional
public class ExpenseMonthService {

	@Autowired
	ExpenseMonthMapper expenseMonthMapper;
	/*
	 * return value when searching for specific month and year in ExpenseMonth
	 */
	private int id_em;

	/**
	 * get list of all from expense_month Helper method is used to convert dto to
	 * entity
	 * 
	 * @return
	 */
	public List<ExpenseMonthDto> getList() {
		return Helper.dtoToEntityExpenseMonth(this.expenseMonthMapper.getList());
	}

	/*
	 * find month and year in expense_month and return id if doesn't exist create
	 */
	public int findExpenseMonth(ExpenseDay ed) {
		LocalDate date = LocalDate.parse(ed.getDate().toString());
		try {
			id_em = this.expenseMonthMapper.findYearAndMonth(date.getMonthValue(), date.getYear());

		} catch (Exception e) {
			this.expenseMonthMapper.insertExpenseMonth(date);
			findExpenseMonth(ed);
		}
		return id_em;
	}

}
