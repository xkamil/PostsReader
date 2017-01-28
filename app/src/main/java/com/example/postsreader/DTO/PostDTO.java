package com.example.postsreader.DTO;

public class PostDTO {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostDTO() {
    }

    public PostDTO(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostDTO postDTO = (PostDTO) o;

        if (userId != postDTO.userId) return false;
        if (id != postDTO.id) return false;
        if (!title.equals(postDTO.title)) return false;
        return body.equals(postDTO.body);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + id;
        result = 31 * result + title.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }
}
