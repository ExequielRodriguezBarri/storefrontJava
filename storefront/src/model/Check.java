package model;

public class Check extends Payment {
	private String _name;
	private String _bankID;

	public void authorized() {
		throw new UnsupportedOperationException();
	}
}