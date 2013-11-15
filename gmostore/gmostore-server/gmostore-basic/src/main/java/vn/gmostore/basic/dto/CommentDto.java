package vn.gmostore.basic.dto;

import vn.gmostore.basic.model.Comment;

public class CommentDto implements Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String name;
    private Integer userId;
    private String content;
    private long createdDate;
    private Long updateDate;

    public CommentDto() {
    }

    public CommentDto(Integer id, String username, String name, Integer userId, String content, long createdDate, Long updateDate) {
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.userId = userId;
        this.content = content;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public CommentDto(Comment comment) {
        if (comment == null)
            return;
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.name = comment.getUser().getName();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.updateDate = comment.getUpdateDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
