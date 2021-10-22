/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tech.blog.dao;

import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */

public class PostDao {
      Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }
    public ArrayList<Category> getAllCategories()
    {
      ArrayList<Category> list=new ArrayList<>();
     try{
        String q="Select * from Categories";
        Statement st=this.con.createStatement();
        ResultSet set=st.executeQuery(q);
        while(set.next())
        {
           int cid=set.getInt("cid");
           String name=set.getString("name");
           String description=set.getString("description");
           Category c=new Category(cid,name,description);
           list.add(c);
        }
        
     }catch(Exception e)
     {
         e.printStackTrace();
     }
      
      return list;
    }
    
        public boolean savePost(Post p){
        boolean f=false;
        try{
            
            String q="insert into posts (pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setString(1, p.getpTitle());
            pstmt.setString(2, p.getpContent());
            pstmt.setString(3, p.getpCode());
            pstmt.setString(4, p.getpPic());
            pstmt.setInt(5, p.getCatId());
            pstmt.setInt(6, p.getUserId());
            
            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
        
         //get All the posts
  public List<Post>  getAllPosts()
  {
      List<Post> list=new ArrayList<>();
      //fetch all the post
      
      try
      {
        PreparedStatement p=con.prepareStatement("select * from posts order by pid desc");
        ResultSet set=p.executeQuery();
        while(set.next())
        {
            int pid=set.getInt("pid");
            String pTitle=set.getString("pTitle");
            String pContent=set.getString("pContent");
            String pCode=set.getString("pCode");
            String pPic=set.getString("pPic");
            Timestamp date=set.getTimestamp("pDate");
            int catId=set.getInt("catId");
            int userId=set.getInt("userId");
            
            Post post=new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
            list.add(post);
        }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      
        return list;
      
      
      
  }
  
  public List<Post> getPostByCatId(int catId)
  {
      List<Post> list=new ArrayList<>();
      //fetch all post by id
      try
      {
        PreparedStatement p=con.prepareStatement("select * from posts where catId=?");
        p.setInt(1,catId);
        ResultSet set=p.executeQuery();
        while(set.next())
        {
            int pid=set.getInt("pid");
            String pTitle=set.getString("pTitle");
            String pContent=set.getString("pContent");
            String pCode=set.getString("pCode");
            String pPic=set.getString("pPic");
            Timestamp date=set.getTimestamp("pDate");
            //int catId=set.getInt("catId");
            int userId=set.getInt("userId");
            
            Post post=new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
            list.add(post);
        }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      
      return list;
  }
   public Post getPostByPostId(int postId) throws SQLException{
      Post post=null;
      String q="select * from posts where pid=?";
      
      try
      {
         PreparedStatement p=this.con.prepareStatement(q);
         p.setInt(1, postId); 
         ResultSet set=p.executeQuery();
         if(set.next())
         {
            int pid=set.getInt("pid");
            String pTitle=set.getString("pTitle");
            String pContent=set.getString("pContent");
            String pCode=set.getString("pCode");
            String pPic=set.getString("pPic");
            Timestamp date=set.getTimestamp("pDate");
            int cid=set.getInt("catId");
            int userId=set.getInt("userId");
            
             post=new Post(pid, pTitle, pContent, pCode, pPic, date,cid, userId);
             //post =new Post();
         }
      }
      catch(Exception e){
          e.printStackTrace();
      }
      
      
      
      return post;
      
      
  }
   
   
     public List<Post> getAllPostByUserId(int userId)
  {
      List<Post> list=new ArrayList<>();
      //fetch all post by userId
      try
      {
        PreparedStatement p=con.prepareStatement("select * from posts where userId=?");
        p.setInt(1,userId);
        ResultSet set=p.executeQuery();
        while(set.next())
        {
            int pid=set.getInt("pid");
            String pTitle=set.getString("pTitle");
            String pContent=set.getString("pContent");
            String pCode=set.getString("pCode");
            String pPic=set.getString("pPic");
            Timestamp date=set.getTimestamp("pDate");
            int catId=set.getInt("catId");
         //   int userId=set.getInt("userId");
            
            Post post=new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
            list.add(post);
        }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      
      return list;
  }
     
     
     
    public boolean updatePost(Post posts)
    {
        boolean f=false;
       try{
           String query="update posts set pTitle=? , pContent=? , pCode=? , pPic=? , catId=? , userId=? where pid=? ";
           PreparedStatement p=con.prepareStatement(query);
           p.setString(1, posts.getpTitle());
           p.setString(2, posts.getpContent());
           p.setString(3, posts.getpCode());
           p.setString(4, posts.getpPic());
           p.setInt(5, posts.getCatId());
           p.setInt(6, posts.getUserId());
           p.setInt(7, posts.getPid());
           
           
           p.executeUpdate();
           f=true;
       } 
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return f;
    }

   public boolean deletePost(int pid) throws SQLException{
      boolean rowdeleted = false;
      String q="delete from posts where pid=?";
      
     try
      {
         PreparedStatement p=this.con.prepareStatement(q);
         p.setInt(1,pid); 
         rowdeleted = p.executeUpdate() > 0;
         rowdeleted=true;
      }
      catch(Exception e){
          e.printStackTrace();
      }
     return rowdeleted;
      
      
  }
    
   ////////////////////////////////////////////////////////////
   
     
     public List<Post> getPostByUserIdCatId(int userId,int catId)
  {
      List<Post> list=new ArrayList<>();
      //fetch all post by userId
      try
      {
        PreparedStatement p=con.prepareStatement("select * from posts where userId=? and catId=?");
        p.setInt(1,userId);
        p.setInt(2,catId);
        ResultSet set=p.executeQuery();
        while(set.next())
        {
            int pid=set.getInt("pid");
            String pTitle=set.getString("pTitle");
            String pContent=set.getString("pContent");
            String pCode=set.getString("pCode");
            String pPic=set.getString("pPic");
            Timestamp date=set.getTimestamp("pDate");
            //int catId=set.getInt("catId");
         //  int userId=set.getInt("userId");
            
            Post post=new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
            list.add(post);
        }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      
      return list;
  }
     
}
