package cn.lessann.cloud.beans.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 8:57 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "lessann_cloud_article")
public class Article {
    @Id
    private int id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword)
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private Date createTime;
}
