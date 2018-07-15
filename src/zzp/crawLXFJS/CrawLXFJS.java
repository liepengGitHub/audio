package zzp.crawLXFJS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawLXFJS {

	public static void main(String[] args) throws IOException  {
		
		File file = new File("C:\\Users\\zhan\\Desktop\\LXFJS.html");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		fw.write("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<title></title>\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://cdn.liaoxuefeng.com/cdn/static/themes/default/css/all.css?v=e6763a1\">"+
				"	<style type=\"text/css\">\r\n" + 
				"		body {margin-left: 10px;\r\n" + 
				"				margin-right:10px;\r\n" + 
				"\r\n" + 
				"				}\r\n" + 
				"	</style>"+
				"</head>\r\n" + 
				"<body>");
		
		String url = "https://www.liaoxuefeng.com/wiki/001434446689867b27157e896e74d51a89c25cc8b43bdb3000";
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element e1 = doc.getElementById("001434446689867b27157e896e74d51a89c25cc8b43bdb3000");
		Elements es1 = e1.getElementsByTag("a");
		
		for(Element e2:es1) {
			String link = e2.attr("abs:href");
			String text = e2.text();
			fw.write("<h1>");
			fw.write(text);
			fw.write("</h1>");
			//System.out.println(link+":"+text);
			Document docinner = null;
			try {
				docinner = Jsoup.connect(link).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			Elements es2 =docinner.getElementsByClass("x-wiki-content x-main-content");
			for(Element e3:es2) {
				fw.write(e3.toString());
			}
			System.out.println(text);
		}
		
		fw.write("</body>\r\n" + 
				"</html>");

		fw.close();
		

	}

}
