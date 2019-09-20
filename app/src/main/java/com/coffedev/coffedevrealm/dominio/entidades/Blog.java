package com.coffedev.coffedevrealm.dominio.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Blog extends RealmObject {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private int v;
    @SerializedName("categoryblog")
    @Expose
    private String categoryblog;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private String id;
    @SerializedName("image")
    @Expose
    private Imagen imageBlog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getCategoryblog() {
        return categoryblog;
    }

    public void setCategoryblog(String categoryblog) {
        this.categoryblog = categoryblog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Imagen getImageBlog() {
        return imageBlog;
    }

    public void setImageBlog(Imagen imageBlog) {
        this.imageBlog = imageBlog;
    }

}