package co.jp.expense.model;

import java.sql.Date;
import lombok.Data;

@Data
public class ExpenseDay {

	private int id_ed;
	private Date date;
	private String detail;
	private double amount;
	private int id_em;

}
