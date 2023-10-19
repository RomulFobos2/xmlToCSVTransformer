package com.tander.xmlToCSVTransformer;

import com.tander.xmlToCSVTransformer.models.Article;
import com.tander.xmlToCSVTransformer.models.Articles;
import com.tander.xmlToCSVTransformer.repo.DBRepo;
import com.tander.xmlToCSVTransformer.service.XMLCreator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class XmlToCsvTransformerApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		DBRepo dbRepo = context.getBean(DBRepo.class);
		XMLCreator xmlCreator = context.getBean(XMLCreator.class);

		List<Article> articleList = dbRepo.getArticleListFromDB();
		if(articleList != null){
			Articles articles = new Articles(articleList);
			if(xmlCreator.createXML(articles)){
				xmlCreator.transformXML();
				xmlCreator.transformCSV();
			}
		}
	}

}
