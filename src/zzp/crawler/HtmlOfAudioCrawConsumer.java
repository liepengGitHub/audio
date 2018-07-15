package zzp.crawler;

import java.util.concurrent.BlockingQueue;

public class HtmlOfAudioCrawConsumer implements Runnable{
	
	private volatile boolean isRunning = true;
	private BlockingQueue<HtmlOfAudioCrawData> queue;
	
	public HtmlOfAudioCrawConsumer(BlockingQueue<HtmlOfAudioCrawData> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
        System.out.println("start Consumer id :"+Thread.currentThread().getId());
        try {
        		while(true) {
        			HtmlOfAudioCrawData data = queue.take();
            		if(data!=null) {
            			////
            			System.out.println(queue.size()+"-----HtmlOfAudioCrawConsumer"+Thread.currentThread().getId()+"在拿出数据:"+data+"来处理");
            		}
            		
        		}
        }catch(InterruptedException e) {
        	e.printStackTrace();
        	Thread.currentThread().interrupt();
        }
	}
	
	
}
