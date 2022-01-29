package cn.lessann.cloud.search.dao;

import cn.lessann.cloud.beans.search.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 10:03 下午
 */
public interface ArticleDao extends ElasticsearchRepository<Article, Integer> {

}
