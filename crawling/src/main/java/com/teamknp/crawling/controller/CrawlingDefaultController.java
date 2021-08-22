package com.teamknp.crawling.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlingDefaultController {

	@RequestMapping("/")
	public String defaultController() {
		return "Hello world";
	}
	
	@RequestMapping("/musicCrawling")
	public String musicCrawling() throws IOException {
		String returnVal = "";
		
		// 멜론 차트
		Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
		Elements eles = doc.select("form[id='frm']").select("tbody tr");
		
		for (int i = 0; i < eles.size(); ++i) {
			
			String sRank		= eles.get(i).select("span[class='rank']").text();
			String sArtist		= eles.get(i).select("span[class='checkEllipsis']").text();
			String sSongName	= eles.get(i).select("div[class='ellipsis rank01'] a").text();
			
			returnVal += "순위 : " + sRank + "<br/>";
			returnVal += "가수명 : " + sArtist + "<br/>";
			returnVal += "곡제목 : " + sSongName + "<br/><br/>";
			
		}
		
		return returnVal;
	}
}
