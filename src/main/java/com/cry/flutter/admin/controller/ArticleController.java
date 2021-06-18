package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.constants.ConstantDict;
import com.cry.flutter.admin.entity.Article;
import com.cry.flutter.admin.service.IArticleService;
import com.cry.flutter.admin.service.IFileService;
import com.cry.flutter.admin.vo.ArticleVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2021-03-11
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Resource(name = "articleServiceImpl")
    IArticleService articleService;

    @Resource(name = "fileServiceImpl")
    IFileService fileService;


    @PostMapping("save")
    public ResponseBodyApi save(@RequestBody Article article) {
        article.setStatus(ConstantDict.ARTICLE_STATUS_DRAFT);
        articleService.saveOrUpdate(article);
        return new ResponseBodyApi();
    }

    @PostMapping("audit")
    public ResponseBodyApi audit(@RequestBody Article article) {
        article.setStatus(ConstantDict.ARTICLE_STATUS_AUDIT);
        articleService.saveOrUpdate(article);
        return new ResponseBodyApi();
    }

    @PostMapping("public")
    public ResponseBodyApi publicAritcle(@RequestBody Article article) {
        article.setStatus(ConstantDict.ARTICLE_STATUS_PUBLIC);
        articleService.saveOrUpdate(article);
        return new ResponseBodyApi();
    }


    @PostMapping("pagePortal")
    public ResponseBodyApi<Page<Article>> pagePortal(@RequestBody RequestBodyApi<ArticleVO> requestBodyApi) {
        requestBodyApi.getParams().setStatus(ConstantDict.ARTICLE_STATUS_PUBLIC);
        return this.page(requestBodyApi);
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Article>> page(@RequestBody RequestBodyApi<ArticleVO> requestBodyApi) {
        ArticleVO articleVO = requestBodyApi.getParams();
        Page<Article> page = articleService.page(requestBodyApi.getPage(), new QueryWrapper<Article>().lambda()
                .eq(!StringUtils.isEmpty(articleVO.getStatus()), Article::getStatus, articleVO.getStatus())
                .like(!StringUtils.isEmpty(articleVO.getTitle()), Article::getTitle, articleVO.getTitle())
                .like(!StringUtils.isEmpty(articleVO.getTitleSub()), Article::getTitleSub, articleVO.getTitleSub())
                .like(!StringUtils.isEmpty(articleVO.getAuthor()), Article::getAuthor, articleVO.getAuthor())
                .ge(!StringUtils.isEmpty(articleVO.getPublishTimeStart()), Article::getPublishTime, articleVO.getPublishTimeStart())
                .le(!StringUtils.isEmpty(articleVO.getPublishTimeEnd()), Article::getPublishTime, articleVO.getPublishTimeEnd())
                .orderByDesc(Article::getCreateTime));
        return new ResponseBodyApi<>(page);
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody Article article) {
        articleService.saveOrUpdate(article);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        articleService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
