package org.com.cay.springboot.task;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 同步任务
 * Created by Cay on 2017/10/10.
 */
@Component
public class SyncTask {

	private Random random = new Random();

	public void doTaskOne() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
	}

	public void doTaskTwo() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
	}

	public void doTaskThree() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
	}
}
