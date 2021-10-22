package com.tech.blog.entities;

import java.sql.*;

public class CommentDetails {

	private int id;
	private int postId;
	private int postPublisherId;
	private int commentPublisherId;
	private int commentParentId;
	private int commentId;
	private int likeCount;
	private String commentPublisherName;
	private String commentContent;
	private String commentPublisherPicPath;
	private Timestamp commentTime;


	public void setPostId(int postId) {
	 	this.postId = postId;
	 }

	 public void setPostPublisherId(int postPublisherId) {
	 	this.postPublisherId = postPublisherId;
	 }

	 public void setCommentPublisherId(int commentPublisherId) {
	 	this.commentPublisherId = commentPublisherId;
	 }

	 public void setCommentParentId(int commentParentId) {
	 	this.commentParentId = commentParentId;
	 }

	 public void setCommentId(int commentId) {
	 	this.commentId = commentId;
	 }

	 public void setCommentContent(String commentContent) {
	 	this.commentContent = commentContent;
	 }


	 public int getPostId() {
	 	return postId;
	 }

	 public int getPostPublisherId() {
	 	return postPublisherId;
	 }

	 public int getCommentPublisherId() {
	 	return commentPublisherId;
	 }

	 public int getCommentParentId() {
	 	return commentParentId;
	 }

	 public int getCommentId() {
	 	return commentId;
	 }

	 public String getCommentContent() {
	 	return commentContent;
	 } 

    public void setId(int id) {
            this.id = id;
    }
//
    public void setCommentPublisherName(String commentPublisherName) {
        
        this.commentPublisherName= commentPublisherName;
    }
//
    public void setCommentPublisherPicPath(String commentPublisherPicPath) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   this.commentPublisherPicPath=commentPublisherPicPath;
    }
//
    public void setCommentTime(Timestamp commentTime ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      this.commentTime=commentTime;
    }
}