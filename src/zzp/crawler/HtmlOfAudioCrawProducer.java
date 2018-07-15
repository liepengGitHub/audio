package zzp.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class HtmlOfAudioCrawProducer implements Runnable{
	
	//private volatile boolean isRunning = true;
	private BlockingQueue<HtmlOfAudioCrawData> queue ;
	//private AtomicInteger count = new AtomicInteger();
	private String orginalURL;
	
	public HtmlOfAudioCrawProducer(BlockingQueue<HtmlOfAudioCrawData> queue,String orginalURL) {
		this.queue = queue;
		this.orginalURL = orginalURL;
	}

	@Override
	public void run() {
		HtmlOfAudioCrawData data = null;
		System.out.println("start producting id:" + Thread.currentThread().getId());
		try {
			
				pruduce();
				ArrayList<String> links = zzp.util.Net.getLink(orginalURL);
				for(String link : links) {
					data = new HtmlOfAudioCrawData(link);
					queue.add(data);
					System.out.println(data + " 加入队列");
					Thread.sleep(1);
				}
	            if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
	                System.err.println(" 加入队列失败");
	            }
			
		}catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private void pruduce() {
    	//1 拿到原材料：第零层url
    	
    	//2 分析原始url，提取其中的url
    	
    	//3 加入queue，如果只是分析零层，不需要循环
	}

/*	public void stop() {
        isRunning = false;
    }*/
	
}
