package com.inmaytide.framework.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
	private static class Task implements Runnable {
		
		private CyclicBarrier barrier;
		
		public Task(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
            try {
            	System.out.println(Thread.currentThread().getName());
            	Thread.sleep(5000);
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	public  void execute() throws InterruptedException {
		Object that = this;
		final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				synchronized(that) {
					that.notifyAll();
				}
			}
		});
		Thread t1 = new Thread(new Task(cb), "Thread 1");
        Thread t2 = new Thread(new Task(cb), "Thread 2");
        Thread t3 = new Thread(new Task(cb), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
        synchronized(that) {
        	wait();
        }
        
	}
	
	public static void main(String...strings) throws InterruptedException {
		CyclicBarrierExample example = new CyclicBarrierExample();
		example.execute();
		System.out.println("end.......");
	}

}
