package co.jp.expense.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import co.jp.expense.entity.ExpenseDay;
import co.jp.expense.repository.ExpenseDayRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpenseDayService {
	@Autowired
	ExpenseDayRepository expenseDayRepository;
	@Autowired
	ExpenseMonthService expenseMonthService;


	public void addExpense(ExpenseDay ed) {
		int id_em = checkExpenseMonth(ed);
		ed.setMonth(id_em);
		try {
			this.expenseDayRepository.save(ed);
			this.expenseMonthService.addMonthExpense(checkExpenseMonth(ed),ed.getAmount());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

	public int checkExpenseMonth(ExpenseDay ed) {
		LocalDate date = LocalDate.parse(ed.getDate().toString());
		int id_em = this.expenseMonthService.findMonthAndDate(date);
		return id_em;
	}

	public ExpenseDay getDetailById(int id) {
		Optional<ExpenseDay> ed = this.expenseDayRepository.findById(id);
		return ed.get();
	}

	public List<ExpenseDay> getExpenseDayList(int id) {
		List<ExpenseDay> ed = this.expenseDayRepository.findByMonthId(id);
		return ed;
	}
	
	public double getTotalByMonthId(int id) {
		return this.expenseDayRepository.getTotalByMonthId(id);
	}
}
