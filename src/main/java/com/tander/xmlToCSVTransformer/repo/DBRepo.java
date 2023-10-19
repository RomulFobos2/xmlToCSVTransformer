package com.tander.xmlToCSVTransformer.repo;

import com.tander.xmlToCSVTransformer.models.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DBRepo {
    private static final Logger logger = LoggerFactory.getLogger(DBRepo.class);

    DataSource dataSource;
    String sqlSelect;

    @Nullable
    public List<Article> getArticleListFromDB(){
        logger.info("Getting data from DB...");
        List<Article> articleList = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()){
                articleList.add(new Article(
                        resultSet.getString("ID_ART"),
                        resultSet.getString("NAME"),
                        resultSet.getString("CODE"),
                        resultSet.getString("USERNAME"),
                        resultSet.getString("GUID")
                ));
            }
        }
        catch (SQLException e){
            logger.error("Error while create connect to DB: ", e);
            return null;
        }
        logger.info("Data from DB received.");
        return articleList;
    }
}