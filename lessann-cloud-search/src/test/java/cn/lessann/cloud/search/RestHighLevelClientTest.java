package cn.lessann.cloud.search;

import cn.lessann.cloud.beans.search.Article;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 9:00 下午
 */
public class RestHighLevelClientTest {

    Gson gson = new Gson();
    RestHighLevelClient client;

    @Before
    public void create() {
        // 初始化HighLevel客户端
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );
    }

    @After
    public void close() throws Exception {
        client.close();
    }

    @Test
    public void insertDoc() throws IOException {
// 准备文档数据：
        String content = "Java最早是由SUN公司（已被Oracle收购）的詹姆斯·高斯林（高司令，人称Java之父）在上个世纪90年代初开发的一种编程语言，最初被命名为Oak，目标是针对小型家电设备的嵌入式应用，结果市场没啥反响。谁料到互联网的崛起，让Oak重新焕发了生机，于是SUN公司改造了Oak，在1995年以Java的名称正式发布，原因是Oak已经被人注册了，因此SUN注册了Java这个商标。随着互联网的高速发展，Java逐渐成为最重要的网络编程语言";
        Article article = new Article(1, "Java基础教学", "Small11", content, new Date());
        // 转为Json格式：
        String source = gson.toJson(article);
        // 创建一个新增索引的请求
        IndexRequest request = new IndexRequest("lessann_cloud_article")
                //添加数据，并指定是JSON格式
                .source(source, XContentType.JSON)
                .id(article.getId() + "");

        // 发起请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("response = " + response);
    }

    @Test
    public void selectDoc() throws Exception {
        // 创建get请求，并指定id
        GetRequest request = new GetRequest("lessann_cloud_article", "1");
        // 是否进行结果source过滤
        // request.fetchSourceContext(new FetchSourceContext(true, null, null));

        // 查询，得到响应
        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        // 解析响应，应该是json
        String source = response.getSourceAsString();
        // 转换json数据
        Article item = gson.fromJson(source, Article.class);
        System.out.println(item);
    }

    @Test
    public void testBulkIndex() throws IOException {
        // 准备文档数据：
        List<Article> list = new ArrayList<>();
        String java = "Java最早是由SUN公司（已被Oracle收购）的詹姆斯·高斯林（高司令，人称Java之父）在上个世纪90年代初开发的一种编程语言，最初被命名为Oak，目标是针对小型家电设备的嵌入式应用，结果市场没啥反响。谁料到互联网的崛起，让Oak重新焕发了生机，于是SUN公司改造了Oak，在1995年以Java的名称正式发布，原因是Oak已经被人注册了，因此SUN注册了Java这个商标。随着互联网的高速发展，Java逐渐成为最重要的网络编程语言。";
        String python = "Python 由 Guido van Rossum 于 1989 年底发明，第一个公开发行版发行于 1991 年。像 Perl 语言一样, Python 源代码同样遵循 GPL(GNU General Public License) 协议。";
        String go = "Go 是一个开源的编程语言，它能让构造简单、可靠且高效的软件变得容易。Go是从2007年末由Robert Griesemer, Rob Pike, Ken Thompson主持开发，后来还加入了Ian Lance Taylor, Russ Cox等人，并最终于2009年11月开源，在2012年早些时候发布了Go 1稳定版本。现在Go的开发已经是完全开放的，并且拥有一个活跃的社区。";
        String c = "C++ 是一种高级语言，它是由 Bjarne Stroustrup 于 1979 年在贝尔实验室开始设计开发的。C++ 进一步扩充和完善了 C 语言，是一种面向对象的程序设计语言。C++ 可运行于多种平台上，如 Windows、MAC 操作系统以及 UNIX 的各种版本。";

        list.add(new Article(1, "Java基础", "small11", java, new Date()));
        list.add(new Article(2, "Python", "yjz", python, new Date()));
        list.add(new Article(3, "Go", "fish", go, new Date()));
        list.add(new Article(4, "C++", "Small11", c, new Date()));

        // 创建批量新增请求
        BulkRequest request = new BulkRequest();
        for (Article item : list) {
            // 构建单个IndexRequest，并add到BulkRequest中去
            request.add(new IndexRequest("lessann_cloud_article")
                    .source(gson.toJson(item), XContentType.JSON));
        }
        // 发起请求
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);
    }

    @Test
    public void testMatchAll() throws IOException {
        // 创建搜索对象
        SearchRequest request = new SearchRequest("lessann_cloud_article");
        // 查询构建工具
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 添加查询条件，通过QueryBuilders获取各种查询
        sourceBuilder.query(QueryBuilders.matchAllQuery());

        request.source(sourceBuilder);

        // 搜索
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 解析
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // 取出source数据
            String json = hit.getSourceAsString();
            // 反序列化
            Article item = gson.fromJson(json, Article.class);
            System.out.println("item = " + item);
        }
    }
}
