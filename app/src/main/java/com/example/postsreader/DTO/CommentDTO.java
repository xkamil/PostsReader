package com.example.postsreader.DTO;

public class CommentDTO {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public CommentDTO() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentDTO that = (CommentDTO) o;

        if (postId != that.postId) return false;
        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!email.equals(that.email)) return false;
        return body.equals(that.body);

    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + id;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }
}
