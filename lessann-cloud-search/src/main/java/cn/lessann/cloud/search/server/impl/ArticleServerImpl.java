package cn.lessann.cloud.search.server.impl;

import cn.lessann.cloud.beans.search.Article;
import cn.lessann.cloud.search.dao.ArticleDao;
import cn.lessann.cloud.search.server.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 10:06 下午
 */
@Service
public class ArticleServerImpl implements ArticleServer {

    @Autowired
    ArticleDao articleDao;

    @Override
    public boolean articleAdd(Article article) {
        Article save = articleDao.save(article);
        return true;
    }
}
