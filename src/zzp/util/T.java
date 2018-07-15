package zzp.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class T {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*Document doc = Jsoup.connect("http://example.com")
				  .data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();*/
		Document doc =Jsoup.connect("http://sc.chinaz.com/yinxiao/").get();
		Elements es = doc.getElementsByTag("p");
		for(Element e:es) {
			String str = e.attr("thumb");
			System.out.println(str+":"+e.text());
		}
		

	}

}
