package co.jp.expense.Helper;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import co.jp.expense.model.ExpenseDay;
import co.jp.expense.model.ExpenseMonth;
import co.jp.expense.model.dto.ExpenseDayDto;
import co.jp.expense.model.dto.ExpenseMonthDto;

/**
 * 
 * @author ASUS_james
 *
 */
public class Helper {
	/**
	 * 
	 * @param d : from ExpenseDay.amount
	 * @return formatted currency amount
	 */
	public static String currencyFomat(double d) {
		DecimalFormatSymbols customSymbols = DecimalFormatSymbols.getInstance(Locale.JAPAN);
		customSymbols.setCurrencySymbol(" JPY");

		DecimalFormat jpFormat = new DecimalFormat("#,##0.00", customSymbols);
		jpFormat.setNegativePrefix("-");
		jpFormat.setNegativeSuffix(" JPY");
		jpFormat.setPositiveSuffix(" JPY");
		return jpFormat.format(d);
	}

	/**
	 * 
	 * @param date : from ExpenseDay.Date
	 * @return formatted date String "2023/31/5"
	 */
	public static String dateFomatDetailList(Date date) {
		SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy/dd/M");
		return dateFomat.format(date);
	}

	/**
	 * 
	 * @param data : from ExpenseMonth.Date
	 * @return formatted date String for ExpenseMonth List "2023/5"
	 */
	public static String dateFomatMainList(Date date) {
		SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy/M");
		return dateFomat.format(date);
	}

	/**
	 * 
	 * @param dto : from ExpenseDayDto
	 * @return ExpenseDay object for data insert and update
	 */
	public static ExpenseDay dtoToEntity(ExpenseDayDto dto) {
		ExpenseDay ed = new ExpenseDay();
		ed.setAmount(Double.parseDouble(dto.getAmount()));
		ed.setDate(Date.valueOf(dto.getDate()));
		ed.setDetail(dto.getDetail());
		ed.setId_ed(dto.getId_ed());
		Optional.ofNullable(dto.getId_em()).ifPresent(id -> ed.setId_em(Integer.parseInt(id)));
		return ed;
	}

	/**
	 * 
	 * @param list : List of ExpenseMonth objects
	 * @return Expense Month Dto List
	 */
	public static List<ExpenseMonthDto> dtoToEntityExpenseMonth(List<ExpenseMonth> list) {
		List<ExpenseMonthDto> dto = new ArrayList<>();
		for (ExpenseMonth e : list) {
			ExpenseMonthDto d = new ExpenseMonthDto();
			d.setDate(Helper.dateFomatMainList(e.getDate()));
			d.setId_em(e.getId_em());
			d.setTotal(Helper.currencyFomat(e.getTotal()));
			dto.add(d);
		}
		return dto;
	}
}
