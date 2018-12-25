package com.aiit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;


/**
 * @author Moon
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    @NonNull
    private Long memberId;
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private Timestamp datePublished;
    private Timestamp dateLastModified;
}
