package com.aiit.pojo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Moon
 */
@Data
@RequiredArgsConstructor
public class PostDTO {
    @NonNull
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private Integer likeNum;
    private Timestamp datePublished;
    private Timestamp dateLastModified;
}
