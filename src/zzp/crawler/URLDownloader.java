package zzp.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zzp.util.Net;

enum Tags {
	Audio
}

public class URLDownloader {




	private HashSet<String> list = new HashSet<String>();
	private HashSet<String> result = new HashSet<String>();
	
	URL mURL;

	public URLDownloader( String url, Tags tag) {
	
		try {
			mURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		//0 获取当前页的mp3url
		result.addAll(getMP3urls(url));
		
		// 1 获取html
		String webContent =null;
		webContent = zzp.util.Net.getWebContent(url);

		//2  获取连接标签,href
		String regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher ma = pa.matcher(webContent);
		while (ma.find()) {
			String temp = ma.group();
			list.add(getAttr_href(temp));
		}
		
		//3 获取href中的mp3链接
		for(String href : list) {
			if(!href.startsWith("http")) {
				href = mURL.getProtocol()+"://"+mURL.getAuthority()+href;
			}
			try {
				new URL(href);
			} catch (MalformedURLException e) {
				continue;
			}finally {
				
			}
			HashSet<String> s = getMP3urls(href);
			result.addAll(s);
			System.out.println(result.size()+",result："+result.toString());
			
			//if(result.size()>=100) break;
			
		}
		
		System.out.println("***********************************************");
		System.out.println(result.size()+",result："+result.toString());
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcurl = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf-8";
			
			Connection conn = DriverManager.getConnection(jdbcurl, "root", "lp050366");
			Statement stat = conn.createStatement();
			
			for(String xxxxx: result) {
				String sqlstat = "insert into mytable values(null,'"+xxxxx+"')";
				System.out.println(sqlstat);	
				stat.executeUpdate(sqlstat);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public HashSet<String> getResult() {
		return result;
	}


	private String getAttr_href(String str) {
		int start = str.indexOf("href=\"");
		int end ;
		if(start>0) {
			end = str.indexOf("\"", start+6);
		}else {
			 start = str.indexOf("href=\'");
			 end = str.indexOf("\'", start+6);
		}
		return str.substring(start+6, end);
	}
	
	private HashSet<String> getMP3urls(String href) {
		HashSet<String> hs = new HashSet<String>();
		String sub_webContent = Net.getWebContent(href);
		String[] strs = sub_webContent.split("\"");
		//System.out.println(Arrays.asList(strs));
		for(String str: strs) {
			if(str.startsWith("http")&&str.endsWith("mp3")) {
				//只是针对http://sc.chinaz.com/yinxiao/				
				String newstr = "http://gd"+str.substring(7);
				hs.add(newstr);
			}
		}
		return hs;
	}

	public static void main(String[] args) {
		new URLDownloader( "http://sc.chinaz.com/yinxiao/", Tags.Audio);
	}

}
