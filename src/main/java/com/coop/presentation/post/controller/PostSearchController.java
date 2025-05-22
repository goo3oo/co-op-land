package com.coop.presentation.post.controller;

import com.coop.domain.post.service.PostSearchService;
import com.coop.global.common.ApiResponse;
import com.coop.presentation.post.dto.response.PostDocPageResponse;
import com.coop.presentation.post.dto.response.PostPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class PostSearchController {

    private final PostSearchService postSearchService;

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<PostDocPageResponse>> readPostsBySearching(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String updatedAt
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());
        PostDocPageResponse responseDto =
                postSearchService.getPostDocsBySearching(pageable, keyword, author, category, updatedAt);

        return ApiResponse.success(responseDto);
    }

    @GetMapping("/like")
    public ResponseEntity<ApiResponse<PostPageResponse>> readPostsByLike(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String keyword
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());
        PostPageResponse responseDto =
                postSearchService.getPostsByLike(pageable, keyword);

        return ApiResponse.success(responseDto);
    }

    @GetMapping("/full-text-natural")
    public ResponseEntity<ApiResponse<PostPageResponse>> readPostsByFullTextNatural(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String keyword
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updated_at").descending());
        PostPageResponse responseDto =
                postSearchService.getPostsByFullTextNaturalLanguage(pageable, keyword);

        return ApiResponse.success(responseDto);
    }

    @GetMapping("/full-text-boolean")
    public ResponseEntity<ApiResponse<PostPageResponse>> readPostsByFullTextBoolean(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String keyword
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updated_at").descending());
        PostPageResponse responseDto =
                postSearchService.getPostsByFullTextBoolean(pageable, keyword);

        return ApiResponse.success(responseDto);
    }
}
