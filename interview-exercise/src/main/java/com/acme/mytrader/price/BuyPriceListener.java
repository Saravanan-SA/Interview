package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

public class BuyPriceListener implements PriceListener {

	private final String security;
	private final double triggerLevel;
	private final int qtyToBuy;
	private final ExecutionService executionService;

	private boolean isTradeExecuted;

	@Override
	public void priceUpdate(String security, double price) {
		if (buySecurity(security, price)) {
			executionService.buy(security, price, qtyToBuy);
			isTradeExecuted = true;
		}
	}

	private boolean buySecurity(String security, double price) {
		return (!isTradeExecuted) && this.security.equals(security) && (price < this.triggerLevel);
	}

	public BuyPriceListener(String security, double triggerLevel, int qtyToBuy, ExecutionService executionService,
			boolean isTradeExecuted) {
		super();
		this.security = security;
		this.triggerLevel = triggerLevel;
		this.qtyToBuy = qtyToBuy;
		this.executionService = executionService;
		this.isTradeExecuted = isTradeExecuted;
	}
}
