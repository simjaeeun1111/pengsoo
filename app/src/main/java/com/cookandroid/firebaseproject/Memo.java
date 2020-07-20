package com.cookandroid.firebaseproject;

public class Memo {
    private String title;
    private String content;

    public Memo(String title, String content){
        this.title = title;
        this.content = content;
    }
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Memo{");
        sb.append("content='").append(content).append('\'');
        sb.append("title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
