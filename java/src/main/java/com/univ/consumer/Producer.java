package com.univ.consumer;

public class Producer implements Runnable{

	private Product stu;
	private int i=0;
	
	public Producer(Product stu){
		this.stu = stu;
	}

	public void run() {

		while (true) {
			synchronized (stu) {
				if(stu.isFlag()){//有产品，则等待
					try {
						stu.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{//没有产品，则生产产品
					if(i%2 == 0){
						stu.setName("lml");
						stu.setAge(24);
					}else{
						stu.setName("aa");
						stu.setAge(10);
					}i++;
					stu.setFlag(true);
					stu.notify();//生产完了要通知消费者消费
				}
				
			}
		}

	}

}
