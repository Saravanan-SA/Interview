package com.acme.mytrader.strategy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TradingStrategyTest {
	
	@Mock
	private TradingStrategy tradingStrategy;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
    @Test
    public void testTriggerAutoBuy() throws InterruptedException {
    	List<SecurityDTO> requestList = new ArrayList<>();
    	Mockito.doNothing().when(tradingStrategy).triggerAutoBuy(requestList);
    }
}
