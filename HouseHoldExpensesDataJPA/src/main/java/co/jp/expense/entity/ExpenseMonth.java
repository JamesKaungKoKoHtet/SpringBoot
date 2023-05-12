package co.jp.expense.entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.type.descriptor.jdbc.NumericJdbcType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class ExpenseMonth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_em")
	private int id_em;

	@Column(name = "date")
	private Date date;

	@Column(name = "total")
	private double total;

}
