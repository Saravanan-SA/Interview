package com.acme.mytrader.price;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BuyPriceListenerTest {

	@Mock
	private BuyPriceListener buyPriceListener;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPriceUpdate() {
		String security ="securityType";
		double price = 35d;
		Mockito.doNothing().when(buyPriceListener).priceUpdate( security,  price);
	}

	

}
