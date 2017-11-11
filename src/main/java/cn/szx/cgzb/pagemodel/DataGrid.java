package cn.szx.cgzb.pagemodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataGrid<T> implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Long total = 0L;
	private Double amount = 0.00;
	private List<T> rows = new ArrayList<T>();

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
