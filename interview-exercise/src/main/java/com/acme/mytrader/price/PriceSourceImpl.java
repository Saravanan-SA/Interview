package com.acme.mytrader.price;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;


public class PriceSourceImpl implements PriceSourceRunnable {

	public PriceSourceImpl() {
		super();
	}

	public List<PriceListener> getPriceListeners() {
		return priceListeners;
	}

	public static List<String> getSecurities() {
		return SECURITIES;
	}

	public static double getRangeMin() {
		return RANGE_MIN;
	}

	public static double getRangeMax() {
		return RANGE_MAX;
	}

	private final List<PriceListener> priceListeners = new CopyOnWriteArrayList<>();

	private static final List<String> SECURITIES = Arrays.asList("DELL", "LENOVO", "IBM", "HP", "AMD", "INTEL", "TATA");

	private static final double RANGE_MIN = 1.00;
	private static final double RANGE_MAX = 200.00;

	@Override
	public void addPriceListener(PriceListener listener) {
		this.priceListeners.add(listener);
	}

	@Override
	public void removePriceListener(PriceListener listener) {
		this.priceListeners.remove(listener);
	}

	@Override
	public void run() {
		Random random = new Random();
		IntStream.range(0, 15).forEach(ind -> {
		    	String security = SECURITIES.get(random.nextInt(SECURITIES.size()));
				double price = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * random.nextDouble();
				priceListeners.forEach(priceListener -> priceListener.priceUpdate(security, price));
		 }); 
	}

}
