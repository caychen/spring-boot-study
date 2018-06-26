### 1、只需要通过使用@Async注解就能简单的将原来的同步函数变为异步函数
##### **注： @Async所修饰的函数不要定义为static类型，这样异步调用不会生效**  
<br/>

### 2、需要在Spring Boot的主程序中配置@EnableAsync使@Async注解生效
```java
@Component
public class AsyncTask {
	@Async//使用@Async注解来设置该任务为异步的
	public void doTaskOne() throws InterruptedException {
		System.out.println("完成任务一");
	}

	@Async
	public void doTaskTwo() throws InterruptedException {
		System.out.println("完成任务二");
	}

	@Async
	public void doTaskThree() throws InterruptedException {
		System.out.println("完成任务三");
	}
}
```

```
结果：
	如果不在测试方法里加入sleep，那么该测试方法可能会出现如下几种情况：
    1. 没有任何任务相关的输出
    2. 有部分任务相关的输出
    3. 乱序的任务相关的输出
原因：
    三个函数是异步执行的，主程序在异步调用后，主程序并不会理会三个函数是否已经执行完成了，所以当主程序执行完成后，就自动结束了，导致异步调用的结果输出不完整。
```

**使用异步回调：**<br/>
为了能让异步调用能够正常结束，需要使用 **Future<T>** 来返回异步调用的结果，通过改造异步函数（另外两个函数同doTaskOne）
```java
@Async
public Future<String> doTaskOne() throws Exception {
    System.out.println("完成任务一");
    return new AsyncResult<>("任务一完成");
}
```

在调用的地方通过返回值来控制代码的结束：
```java
Future<String> task1 = task.doTaskOne();
Future<String> task2 = task.doTaskTwo();
Future<String> task3 = task.doTaskThree();

while(true) {
    if(task1.isDone() && task2.isDone() && task3.isDone()) {
        // 三个任务都调用完成，退出循环等待
        break;
    }
    Thread.sleep(1000);
}
```

可以在@Async中指定线程池，参见FutureAsyncTask和PoolConfig