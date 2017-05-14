package com.application.testdoctalk.modal.networkResponse;

/**
 * Created by rkrde on 14-05-2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IssuesResponse {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("repository_url")
    @Expose
    public String repositoryUrl;
    @SerializedName("labels_url")
    @Expose
    public String labelsUrl;
    @SerializedName("comments_url")
    @Expose
    public String commentsUrl;
    @SerializedName("events_url")
    @Expose
    public String eventsUrl;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("number")
    @Expose
    public Integer number;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("labels")
    @Expose
    public List<Label> labels = null;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("locked")
    @Expose
    public Boolean locked;
    @SerializedName("assignee")
    @Expose
    public Object assignee;
    @SerializedName("assignees")
    @Expose
    public List<Object> assignees = null;
    @SerializedName("milestone")
    @Expose
    public Object milestone;
    @SerializedName("comments")
    @Expose
    public Integer comments;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("closed_at")
    @Expose
    public Object closedAt;
    @SerializedName("body")
    @Expose
    public String body;

}
