package zzp.crawler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 根据配置文件收集资源到数据库
 * 
 * @author zhan
 *
 */
public class AudioCrawler {

	// html页面内容
	private ArrayList<String> htmlAddresses;

	private ArrayList<String> mp3Addresses;

	private StringBuilder webContent;

	private Properties pps;

	public AudioCrawler() {

		//init();

		// 获取项目根目录
		// 读取linksrc

		downloadAddress();
		/*readLinksrcFromProperties();

		for (String url : htmlAddresses) {
			zzp.util.Net.getWebContent(url);
		}*/

		//getMP3urls();

		//download();

		// play();

	}

	private void init() {
		htmlAddresses = new ArrayList<String>();
		mp3Addresses = new ArrayList<String>();
		webContent = new StringBuilder();
		pps = new Properties();
		try {
			pps.load(new FileReader("/linksrc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void downloadAddress() {
		// TODO Auto-generated method stub
		BlockingQueue<HtmlOfAudioCrawData> queue = new LinkedBlockingDeque<HtmlOfAudioCrawData>(1000);
		HtmlOfAudioCrawProducer producer2 = new HtmlOfAudioCrawProducer(queue, "http://www.ipc.me");
		HtmlOfAudioCrawProducer producer3 = new HtmlOfAudioCrawProducer(queue, "https://www.iplaysoft.com");
		HtmlOfAudioCrawConsumer consumer = new HtmlOfAudioCrawConsumer(queue);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(producer2);
		service.execute(producer3);
		service.execute(consumer);;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.shutdown();
	}
	
	public static void main(String[] args) {
		new AudioCrawler();
	}

	private void getMP3urls() {
		String[] strs = webContent.toString().split("\"");
		// System.out.println(Arrays.asList(strs));
		for (String str : strs) {
			if (str.startsWith("http") && str.endsWith("mp3")) {
				String newstr = "http://gd" + str.substring(7);
				mp3Addresses.add(newstr);
			}
		}
	}

	private void readLinksrcFromProperties() {

		Iterator<Object> it = pps.values().iterator();
		while (it.hasNext()) {
			htmlAddresses.add((String) it.next());
		}
	}

	private void play() {
	}

	private void download() {
	}

	private void writeInDatabase() {
	}

}
