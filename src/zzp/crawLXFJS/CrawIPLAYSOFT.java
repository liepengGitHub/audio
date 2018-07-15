package zzp.crawLXFJS;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawIPLAYSOFT {

	public static void main(String[] args) throws IOException {
		Elements es = Jsoup.connect("https://www.iplaysoft.com/").get().getElementsByClass("entry-head");
		for(Element e:es) {
			Elements e1 = e.select(".entry-title a[href]");
			for(Element e2:e1) {
				System.out.print(e2.attr("abs:href")+"                      ");
			}
			System.out.println(e.select(".entry-cat").text());
		}
	}

}
