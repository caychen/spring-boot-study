package org.com.cay.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cay on 2017/9/28.
 */

//创建定时任务实现类
@Component
public class ScheduledTask {

	private static final DateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

	//task1实际执行间隔为：fixedDelay + 上一次执行的时间(本例中有1秒的延时)
	//@Scheduled(fixedDelay = 2000)//从上一次执行完毕后开始算，每隔2秒再执行一次，其中2秒不包括上一次执行的时间
	public void task1() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("当前时间：" + df.format(new Date()));
	}

	//task2实际执行间隔即为fixedRate的值
	//@Scheduled(fixedRate = 2000)//从上一次执行的时间点开始算，每隔2秒执行一次，其中2秒包括上一次执行的时间
	public void task2() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("当前时间：" + df.format(new Date()));
	}

	//@Scheduled(initialDelay = 5000, fixedRate = 2000)//延时5秒后开始执行，之后每次间隔2秒执行
	public void task3() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("当前时间：" + df.format(new Date()));
	}

	//从0秒开始，每隔5秒执行依次
	@Scheduled(cron = "0/5 * * * * ?")//cron表达式
	public void task4()/* throws InterruptedException */{
		//Thread.sleep(1000);
		System.out.println("当前时间：" + df.format(new Date()));
	}
}
