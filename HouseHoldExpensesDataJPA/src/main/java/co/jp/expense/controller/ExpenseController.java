package co.jp.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.expense.service.ExpenseDayService;
import co.jp.expense.service.ExpenseMonthService;
import co.jp.expense.entity.*;

/**
 * 
 * @author ASUS_James
 *
 */
@Controller
public class ExpenseController {

	@Autowired
	private ExpenseDayService expenseDayService;

	@Autowired
	private ExpenseMonthService expenseMonthService;

	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("emList", this.expenseMonthService.getExpenseMonthList());
		return "index";
	}

	@GetMapping(value = "/addExpense")
	public String addExpense(Model model) {
		model.addAttribute("expenseForm", new ExpenseDay());
		return "addExpense";
	}

	@PostMapping(value = "/addExpense")
	public String addConfirm(Model model, @ModelAttribute("expenseForm") ExpenseDay ed) {
		this.expenseDayService.addExpense(ed);
		return "redirect:/detail/" + ed.getMonth();
	}

	@GetMapping(value = "/detail/{id}")
	public String monthDetail(Model model, @PathVariable int id) {
		model.addAttribute("total", this.expenseDayService.getTotalByMonthId(id));
		model.addAttribute("expenseDetailList", this.expenseDayService.getExpenseDayList(id));
		return "expenseDetail";
	}

	@GetMapping(value = "/update")
	public String update(Model model, @RequestParam("id") int id) {
		ExpenseDay ed = this.expenseDayService.getDetailById(id);
		model.addAttribute("expenseForm", ed);
		return "updateExpense";
	}

	@PostMapping(value = "/update")
	public String update(Model model, @ModelAttribute("expenseForm") ExpenseDay ed) {
		this.expenseDayService.addExpense(ed);
		return "redirect:/detail/" + ed.getMonth();
	}
}
