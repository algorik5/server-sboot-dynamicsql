package util;

import java.util.concurrent.CountDownLatch;

public class CountDown {

	CountDownLatch latch;
	public void startCount(int total)
	{
		latch = new CountDownLatch(total);
	}
	public void countDown()
	{
		latch.countDown();
	}
	public void waitEnd() throws InterruptedException
	{
		latch.await();
	}
	
	public static void main(String[] args) throws Exception
	{
		int total = 10;
		CountDownLatch latch = new CountDownLatch(total);
		
		Log.log("==== start ");
		new Thread(()->{
			for(int i=0;i<100;i++)
			{
				latch.countDown();
				Log.log("\t\t === countDown # "+ i);
			}
		}).start();
		
		Log.log("\t=== await ");
		latch.await();
		Log.log("==== end ");
	}
}
