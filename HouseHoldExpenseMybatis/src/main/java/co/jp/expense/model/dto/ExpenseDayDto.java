package co.jp.expense.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExpenseDayDto {

	private int id_ed;
	@NotBlank(message = "日付を入力してくだい。")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String date;
	@NotBlank(message = "項目を入力してください。")
	private String detail;
	@NotBlank(message = "費用を入力してください。")
	@Positive(message = "費用をゼロ以上入力してください。")
	private String amount; 
	private String id_em;
}
