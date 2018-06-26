package org.com.cay.spring.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.*;

/**
 * Created by Cay on 2018/5/18.
 */
@WithStateMachine
public class EventConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventConfig.class);

	@OnTransition(target = "UNPAID")
	public void create(){
		LOGGER.info("订单生成...");
	}

	@OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
	public void pay(){
		LOGGER.info("订单完成支付，待收货...");
	}

	@OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
	public void payStart(){
		LOGGER.info("订单完成支付，待收货: start");
	}

	@OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
	public void payEnd(){
		LOGGER.info("订单完成支付，待收货: end");
	}

	@OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
	public void end(){
		LOGGER.info("订单完成收货...");
	}

	@OnStateChanged(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
	public void changed(){
		LOGGER.info("订单从待支付变化为待收货....");
	}
}
