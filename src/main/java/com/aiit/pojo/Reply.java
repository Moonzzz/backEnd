package com.aiit.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Moon
 */
@Data
@NoArgsConstructor
public class Reply {

    private Long replyId;
    private Long postId;
    private Long filmId;
    private String memberId;
    private String memberName;
    private String memberImage;
    private String content;
    private Integer likeNum;
    private String datePublished;

    public Reply(String content, Integer likeNum, String datePublished) {
        this.content = content;
        this.likeNum = likeNum;
        this.datePublished = datePublished;
    }

    public Reply(Long replyId, String memberName, String content, Integer likeNum, String datePublished) {
        this.replyId = replyId;
        this.memberName = memberName;
        this.content = content;
        this.likeNum = likeNum;
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", postId=" + postId +
                ", filmId=" + filmId +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberImage='" + memberImage + '\'' +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", datePublished='" + datePublished + '\'' +
                '}';
    }
}
