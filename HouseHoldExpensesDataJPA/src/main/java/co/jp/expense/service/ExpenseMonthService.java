package co.jp.expense.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import co.jp.expense.entity.ExpenseMonth;
import co.jp.expense.repository.ExpenseMonthRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpenseMonthService {
	@Autowired
	ExpenseMonthRepository expenseMonthRepository;

	public List<ExpenseMonth> getExpenseMonthList() {
		List<ExpenseMonth> em = new ArrayList<ExpenseMonth>();
		em = this.expenseMonthRepository.findAll();
		return em;
	}

	int data = 0;
	public int findMonthAndDate(LocalDate date) {

		try {
			data = this.expenseMonthRepository.findByMonthAndYear(date.getYear(), date.getMonthValue());
		} catch (Exception e) {
			setExpenseMonth(date);
			findMonthAndDate(date);
		}
		return data;
	}

	private void setExpenseMonth(LocalDate date) {
		ExpenseMonth em = new ExpenseMonth();
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);

		em.setDate(sqlDate);
		em.setTotal(0);
	
			this.expenseMonthRepository.save(em);
		

	}
	
	public ExpenseMonth getExpenseMonth(int id) {
		Optional<ExpenseMonth> expenseMonth = this.expenseMonthRepository.findById(id);
		return expenseMonth.get();
	}
	
	 void addMonthExpense(int id_em, double amount) {
		 ExpenseMonth em = getExpenseMonth(id_em);
		 em.setTotal(em.getTotal()+amount);
		 
		 try {
			 this.expenseMonthRepository.save(em);
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

}
