package com.cry.flutter.admin.vo;

import com.cry.flutter.admin.entity.Article;
import lombok.Data;

@Data
public class ArticleVO extends Article {
    private String publishTimeStart;
    private String publishTimeEnd;
}
