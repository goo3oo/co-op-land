package com.coop.presentation.post.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PostPageResponse<T>(List<T> posts, int currentPage, int totalPages, boolean hasNext) {
    public static <T> PostPageResponse<T> from(Page<T> pageResponse) {
        return PostPageResponse.<T>builder()
                .posts(pageResponse.getContent())
                .currentPage(pageResponse.getNumber())
                .totalPages(pageResponse.getTotalPages())
                .hasNext(pageResponse.hasNext())
                .build();
    }
}