package com.aiit.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Moon
 */
@Data
@NoArgsConstructor
public class Comment {

    private Long commentId;
    private Long filmId;
    private String memberId;
    private String memberName;
    private String memberImage;
    private String content;
    private Integer likeNum;
    private String datePublished;

    public Comment(String content, Integer likeNum, String datePublished) {
        this.content = content;
        this.likeNum = likeNum;
        this.datePublished = datePublished;
    }

    public Comment(Long commentId, String memberName, String content, Integer likeNum, String datePublished) {
        this.commentId = commentId;
        this.memberName = memberName;
        this.content = content;
        this.likeNum = likeNum;
        this.datePublished = datePublished;
    }
}
