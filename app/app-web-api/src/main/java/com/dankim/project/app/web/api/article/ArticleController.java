package com.dankim.project.app.web.api.article;

import com.dankim.project.app.web.api.article.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleApiService articleApiService;

    @PostMapping
    public ResponseEntity<CreateArticleResponse> create(@RequestBody CreateArticleRequest request) {
        var articleId = articleApiService.create(request.userId(), request.content(), request.title());
        return ResponseEntity.ok(new CreateArticleResponse("created", articleId));
    }

    @GetMapping
    public ResponseEntity<GetArticleResponse> read(@RequestParam Long id) {
        var article = articleApiService.read(id);
        return ResponseEntity.ok(new GetArticleResponse(article));
    }

    @PutMapping
    public ResponseEntity<UpdateArticleResponse> update(@RequestBody UpdateArticleRequest request) {
        var updated = articleApiService.update(
                request.articleId(),
                request.userId(),
                request.content(),
                request.title()
        );

        return ResponseEntity.ok(new UpdateArticleResponse(updated));
    }

}
