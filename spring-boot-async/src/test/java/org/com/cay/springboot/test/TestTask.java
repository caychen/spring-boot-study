package org.com.cay.springboot.test;

import org.com.cay.springboot.task.AsyncTask;
import org.com.cay.springboot.task.FutureAsyncTask;
import org.com.cay.springboot.task.SyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.Future;

/**
 * Created by Cay on 2017/10/10.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTask {

	@Autowired
	private SyncTask syncTask;

	@Autowired
	private AsyncTask asyncTask;

	@Autowired
	private FutureAsyncTask futureAsyncTask;

	@Test
	public void testSyncTask() throws Exception{
		syncTask.doTaskOne();
		syncTask.doTaskTwo();
		syncTask.doTaskThree();
	}

	//如果不在测试方法里加入sleep，那么该测试方法可能会出现如下几种情况：
	//1、没有任何任务相关的输出
	//2、有部分任务相关的输出
	//3、乱序的任务相关的输出
	//原因：三个函数是异步执行的，主程序在异步调用后，主程序并不会理会三个函数是否已经执行完成了，所以当主程序执行完成后，就自动结束了，导致异步调用的结果输出不完整。
	@Test
	public void testAsyncTask() throws Exception{
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();

		//为了能看到结果，将主程序延迟10秒
		Thread.sleep(10000);
	}

	@Test
	public void testFutureAsyncTask() throws Exception{
		long start = System.currentTimeMillis();

		Future<String> taskOne = futureAsyncTask.doTaskOne();
		Future<String> taskTwo = futureAsyncTask.doTaskTwo();
		Future<String> taskThree = futureAsyncTask.doTaskThree();

		while(true){
			if(taskOne.isDone() && taskTwo.isDone() && taskThree.isDone()){
				break;
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}
