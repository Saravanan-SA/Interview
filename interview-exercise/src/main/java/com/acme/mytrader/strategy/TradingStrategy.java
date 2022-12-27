package com.acme.mytrader.strategy;

import static java.util.Arrays.asList;

import java.util.List;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.TradeExecutionService;
import com.acme.mytrader.price.BuyPriceListener;
import com.acme.mytrader.price.PriceSourceImpl;
import com.acme.mytrader.price.PriceSourceRunnable;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */

public class TradingStrategy {

	public TradingStrategy(ExecutionService tradeExecutionService, PriceSourceRunnable priceSource) {
		super();
		this.tradeExecutionService = tradeExecutionService;
		this.priceSource = priceSource;
	}

	public ExecutionService getTradeExecutionService() {
		return tradeExecutionService;
	}

	public PriceSourceRunnable getPriceSource() {
		return priceSource;
	}

	private final ExecutionService tradeExecutionService;
	private final PriceSourceRunnable priceSource;

	public void triggerAutoBuy(List<SecurityDTO> requestList) throws InterruptedException {

		requestList.stream().map(r -> new BuyPriceListener(r.getSecurity(), r.getPriceThreshold(), r.getVolume(),
				tradeExecutionService, false)).forEach(priceSource::addPriceListener);
		Thread thread = new Thread(priceSource);
		thread.start();
		thread.join();
		requestList.stream().map(r -> new BuyPriceListener(r.getSecurity(), r.getPriceThreshold(), r.getVolume(),
				tradeExecutionService, false)).forEach(priceSource::removePriceListener);
	}

	public static void main(String[] args) throws InterruptedException {
		TradingStrategy tradingStrategy = new TradingStrategy(new TradeExecutionService(1), new PriceSourceImpl());
		final SecurityDTO ibm = new SecurityDTO("IBM", 100.00, 16);
		final SecurityDTO tata = new SecurityDTO("TATA", 100.00, 21);
		tradingStrategy.triggerAutoBuy(asList(ibm, tata));
	}

}

class SecurityDTO {

	private final String security;
	private final double priceThreshold;
	private final int volume;

	public SecurityDTO(String security, double priceThreshold, int volume) {
		super();
		this.security = security;
		this.priceThreshold = priceThreshold;
		this.volume = volume;
	}

	public String getSecurity() {
		return security;
	}

	public double getPriceThreshold() {
		return priceThreshold;
	}

	public int getVolume() {
		return volume;
	}
}
