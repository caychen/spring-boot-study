package org.com.cay.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by Cay on 2017/10/10.
 */
@Component
public class FutureAsyncTask {

	private static final Logger logger = LoggerFactory.getLogger(FutureAsyncTask.class);

	private Random random = new Random();

	@Async("taskExecutor")
	public Future<String> doTaskOne() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		logger.info("完成任务一，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务一完成...");
	}

	@Async("taskExecutor")
	public Future<String> doTaskTwo() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		logger.info("完成任务二，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务二完成...");
	}

	@Async("taskExecutor")
	public Future<String> doTaskThree() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();

		logger.info("完成任务三，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务三完成...");
	}
}
