package com.gennlife.crf.keyanbao;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * @Description: 科研宝解析html页面
 * @author: wangmiao
 * @Date: 2017年8月23日 下午12:27:20 
 */
public class FindElementsOfHtmlByJsoup {

	public static void findElements() throws Exception {
		
		File input = new File("E:\\科研宝\\748045.htm");
		//File input = new File("E:\\科研宝\\748330.htm");
		Document doc = Jsoup.parse(input, "UTF-8", "http://www.jb51.net/");

		Element content = doc.getElementById("crfpanel");
		Elements elements = content.getElementsByTag("span");
		
		for (Element e : elements) {
		  String value = e.text();
		  System.out.println(value);
		}
		
		
		System.out.println("-----------------");
		
		Elements elementsByClass = content.getElementsByClass("leftlabel td-label");
		
		for (Element eb : elementsByClass) {
		  String value2 = eb.text();
		  System.out.println(value2);
		}
		
		
	}
	
	
}
