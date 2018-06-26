package org.com.cay.spring.boot;

import org.com.cay.spring.boot.vo.EventEnum;
import org.com.cay.spring.boot.vo.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class SpringBootStatemachineApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStatemachineApplication.class, args);
	}

	@Autowired
	private StateMachine<StateEnum, EventEnum> stateMachine;

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();

		stateMachine.sendEvent(EventEnum.PAY);

		stateMachine.sendEvent(EventEnum.RECEIVE);
	}
}
