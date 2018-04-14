package com.univ.consumer;

public class Consumer implements Runnable {

	private Product stu;// 这里的类型用Runnable还是Student?

	// 显然是用Student，一是意思更明确，二是不然没法调用stu.getName()和setName()

	public Consumer(Product stu) {
		this.stu = stu;
	}

	public void run() {

		// 注意，消费者只管消费，不负责生成
		while (true) {
			synchronized (stu) {
				if(!stu.isFlag()){//没有产品在，则等待
					try {
						stu.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{//有产品， 则消费
					if (stu.getName() != null) {
						System.out.println(stu.getName()+ "   "+stu.getAge());
						stu.setFlag(false);
						stu.notify();//消费完了，要通知生产者生产
					}
				}
				
			}
		}

	}

}
