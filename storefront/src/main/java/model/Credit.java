package model;

import java.sql.Date;

public class Credit extends Payment {
	private String _number;
	private String _type;
	private Date _expDate;

	public void authorized() {
		throw new UnsupportedOperationException();
	}
}