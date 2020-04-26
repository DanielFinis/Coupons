package com.danny.coupons;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danny.coupons.logic.CouponsController;

@Component
public class MyTimerTask extends TimerTask {
	
	@Autowired
	private CouponsController couponsController;

	@Override
	public void run() {
		long now = Calendar.getInstance().getTimeInMillis();
		Date todayDate = new Date(now);
		try {
			couponsController.removeNotValidCouponsByEndDate(todayDate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
