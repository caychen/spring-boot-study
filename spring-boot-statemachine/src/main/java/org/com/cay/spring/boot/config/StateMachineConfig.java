package org.com.cay.spring.boot.config;

import org.com.cay.spring.boot.vo.EventEnum;
import org.com.cay.spring.boot.vo.StateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * Created by Cay on 2018/5/18.
 */
@Configuration
@EnableStateMachine //该注解用来启用Spring StateMachine状态机功能
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StateMachineConfig.class);

	//方法用来初始化当前状态机拥有哪些状态，其中initial(States.UNPAID)定义了初始状态为UNPAID，states(EnumSet.allOf(States.class))则指定了使用上一步中定义的所有状态作为该状态机的状态定义。
	@Override
	public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> states) throws Exception {
//		super.configure(states);
		// 定义状态机中的状态
		states
				.withStates()
					.initial(StateEnum.UNPAID)// 初始状态
					.states(EnumSet.allOf(StateEnum.class));
	}

	//用来初始化当前状态机有哪些状态迁移动作，有来源状态source，目标状态target以及触发事件event。
	@Override
	public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions) throws Exception {
//		super.configure(transitions);
		transitions
				.withExternal()
					.source(StateEnum.UNPAID)
					.target(StateEnum.WAITING_FOR_RECEIVE)
					.event(EventEnum.PAY)// 指定触发事件

				.and()
				.withExternal()
					.source(StateEnum.WAITING_FOR_RECEIVE)
					.target(StateEnum.DONE)
					.event(EventEnum.RECEIVE);
	}


	/*
	//为当前的状态机指定了状态监听器
	@Override
	public void configure(StateMachineConfigurationConfigurer<StateEnum, EventEnum> config) throws Exception {
//		super.configure(config);
		config
				.withConfiguration()
				// 指定状态机的处理监听器
					.listener(new StateMachineListenerAdapter<StateEnum, EventEnum>() {

			@Override
			public void transition(Transition<StateEnum, EventEnum> transition) {
//				super.transition(transition);
				if (transition.getTarget().getId() == StateEnum.UNPAID) {
					LOGGER.info("订单待支付...");
					return;
				}
				if (transition.getTarget().getId() == StateEnum.WAITING_FOR_RECEIVE) {
					LOGGER.info("订单已支付，等待收货...");
					return;
				}
				if (transition.getTarget().getId() == StateEnum.DONE) {
					LOGGER.info("恭喜，订单完成...");
					return;
				}
			}
		});
	}
	*/
}
