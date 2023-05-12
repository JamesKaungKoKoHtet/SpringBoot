package co.jp.expense.model;

import java.sql.Date;
import lombok.Data;

@Data
public class ExpenseMonth {

	private int id_em;
	private Date date;
	private double total;

}
