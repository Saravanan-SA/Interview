package com.acme.mytrader.execution;

public class TradeExecutionService implements ExecutionService {

	private final int id;

	public TradeExecutionService(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public void buy(String security, double price, int volume) {
		System.out.printf("\n BUY Trade executed for the stock of %s @ Rs. %.2f for %d number of securities", security, price, volume);
	}

	@Override
	public void sell(String security, double price, int volume) {
		throw new UnsupportedOperationException("Functionality Not Available");
	}
}
