package org.com.cay.springboot.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * 异步任务
 * Created by Cay on 2017/10/10.
 */
@Component
public class AsyncTask {

	private Random random = new Random();

	@Async//使用@Async注解来设置该任务为异步的
	public void doTaskOne() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
	}

	@Async
	public void doTaskTwo() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
	}

	@Async
	public void doTaskThree() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
	}
}
