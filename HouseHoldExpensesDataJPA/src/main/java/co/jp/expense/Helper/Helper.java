package co.jp.expense.Helper;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;

public class Helper {

	public static String currencyFomat(double d) {
		DecimalFormatSymbols customSymbols = DecimalFormatSymbols.getInstance(Locale.JAPAN);
		customSymbols.setCurrencySymbol(" JPY");

		DecimalFormat jpFormat = new DecimalFormat("#,##0.00", customSymbols);
		jpFormat.setNegativePrefix("-"); 
		jpFormat.setNegativeSuffix(" JPY");
		jpFormat.setPositiveSuffix(" JPY");
		return jpFormat.format(d);
	}

	public static String dateFomatDetailList(Date data) {
		SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy/dd/M");
		return dateFomat.format(data);
	}

	public static String dateFomatMainList(Date data) {
		SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy/M");
		return dateFomat.format(data);
	}
}
