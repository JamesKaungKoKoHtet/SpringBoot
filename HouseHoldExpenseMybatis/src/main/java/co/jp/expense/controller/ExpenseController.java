package co.jp.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.expense.Helper.Helper;
import co.jp.expense.model.dto.ExpenseDayDto;
import co.jp.expense.service.ExpenseDayService;
import co.jp.expense.service.ExpenseMonthService;
import jakarta.validation.Valid;
/**
 * 
 * @author ASUS james
 *
 */
@Controller
public class ExpenseController {
	@Autowired
	ExpenseMonthService expenseMonthService;
	@Autowired
	ExpenseDayService expenseDayService;
	
	/**
	 * 
	 * @param model : view model
	 * @return Expense Month View with ExpenseMonthDto arrtibute
	 */
	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("emList", this.expenseMonthService.getList());
		return "index";
	}

	/**
	 * 
	 * @param model : view model
	 * @param id : ExpenseMonthDto id from index page
	 * @return detail list view of the month 
	 * with attributes of total and expense day list
	 */
	@GetMapping(value = "/detail/{id}")
	public String monthDetail(Model model, @PathVariable int id) {
		model.addAttribute("total", Helper.currencyFomat(this.expenseDayService.getTotal(id)));
		model.addAttribute("expenseDetailList", this.expenseDayService.getExpenseDayList(id));
		return "expenseDetail";
	}
	
	/**
	 * 
	 * @param model : view model
	 * @return  Add ExpenseDayDto to model and return add expense page
	 */
	@GetMapping(value = "/addExpense")
	public String addExpense(Model model) {
		model.addAttribute("expenseDto", new ExpenseDayDto());
		return "addExpense";
	}
	/**
	 * 
	 * @param model : view model
	 * @param ed : ExpenseDayDto from view model
	 * @param result : error binding object
	 * @return : return add expense page if there was validation error
	 * otherwise call addExpense and redirect to detial page of the month
	 */
	@PostMapping(value = "/addExpense")
	public String addConfirm(Model model, @Valid @ModelAttribute("expenseDto") ExpenseDayDto ed, BindingResult result) {
		if (result.hasErrors()) {
			return "addExpense"; 
		}
		int id_em = this.expenseDayService.addExpense(ed);
		return "redirect:/detail/" + id_em;
	}

	/**
	 * 
	 * @param model : view model
	 * @param id : ExpenseDayDto id from Expense Month Detail Page
	 * @return ExpenseDay update view with attribute of expenseDaydto searched by id
	 */
	@GetMapping(value = "/update")
	public String update(Model model, @RequestParam("id") int id) {
		model.addAttribute("expenseDto", this.expenseDayService.getExpenseDayById(id));
		return "updateExpense";
	}
	/**
	 * 
	 * @param model : view model
	 * @param ed : ExpenseDayDto object from updateExpense view
	 * @param result view validation result
	 * @return redirect to detail page of the month of updated expense day
	 */
	@PostMapping(value = "/update")
	public String update(Model model, @Valid @ModelAttribute("expenseDto") ExpenseDayDto ed, BindingResult result) {
		if (result.hasErrors()) {
			return "updateExpense";
		}
		this.expenseDayService.updateExpense(ed);
		return "redirect:/detail/" + ed.getId_em();
	}

	
	@GetMapping(value = "/test")
	public void testing() {
		//TO TEST
	}

}
