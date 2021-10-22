
package com.tech.blog.dao;

//import com.tech.blog.entities.Like;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


public class LikeDao {
    Connection con;
//
    public LikeDao(Connection con) {
        this.con = con;
    }
//    
  public boolean insertLike(int pid,int uid)
  {
      boolean f=false;
      try{
          String q="insert into likedd(pid,uid)values(?,?)";
          PreparedStatement p=this.con.prepareStatement(q);
          //values set....
          p.setInt(1, pid);
          p.setInt(2, uid);
          p.executeUpdate();
          f=true;
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      return f;
  }
  
  public int countLikeOnPost(int pid)
  {
      int count=0;
      String q="select count(*) from likedd where pid=?";
      try
      {
          
      
      PreparedStatement p=this.con.prepareStatement(q);
      p.setInt(1,pid);
     ResultSet set= p.executeQuery();
     if(set.next())
     {
         count=set.getInt("count(*)");
     }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      return count;
  }
  
  
 /* public boolean isLikedByUser(int pid,int uid)
  {
      boolean f=false;
      try{
          PreparedStatement p=this.con.prepareStatement("select * from liked where pid=? and uid=?");
          p.setInt(1, pid);
          p.setInt(2, uid);
          ResultSet set=p.executeQuery();
          if(set.next())
          {
              f=true;
          }
          
          
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      
      
      return f;
      
  }
  
  
  public boolean deleteLike(int pid,int uid)
  {
      boolean f=false;
      try
      {
          PreparedStatement p=this.con.prepareStatement("delete from liked where pid=? and uid=? "); 
          p.setInt(1, pid);
          p.setInt(2, uid);
          p.executeUpdate();
          f=true;
          
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return f;
  }*/
  
  
  ////////////////////////////////////////////////new//////////////////////////////////////////////////////
    
 /*   private static Logger logger = Logger.getLogger("com.my.connection");

	private static Connection con;
  
	public static Like liker(int pid,int uid) {

		Like likeModel = new Like();
		
		try {
			//
                    
                   // con = DBConnection.getConnection();
			logger.info("<>Inside LikeDao class liker Method()");
			
			boolean alreadyLiked = isLiked(pid,uid);


			if(alreadyLiked){
				removeLike(pid,uid);
				likeModel.setIsLiked(false);
			} else {
				addLike(pid,uid);
				likeModel.setIsLiked(true);
			}
                                    //select count(*) from liked where pid=?
			String qry = "select count(*) as totallike from liked where pid = ? ";
                     
                            
                       // PreparedStatement p=this.con.prepareStatement(qry);
			PreparedStatement preStmt = con.prepareStatement(qry);
			preStmt.setInt(1, pid);

			ResultSet rs = preStmt.executeQuery();

			if(rs.next()) {
				likeModel.setTotalLike(rs.getInt("totallike"));
			}
                
                         
			return likeModel;

		} catch (Exception ex) {
			logger.severe("<>"+ex.getMessage());
		} finally {
			if(con!= null){
				try {
					con.close();
				} catch(Exception ex){}
			}
		}
		return likeModel;
	}


	private static boolean isLiked( int pid,int uid) throws SQLException {

		logger.info("<>Inside isLiked Method()");

		String selQry = "select pid,uid from liked where pid=? and uid=?";
		PreparedStatement preStmt = con.prepareStatement(selQry);
		preStmt.setInt(1,pid);
		preStmt.setInt(2,uid);

		ResultSet rs = preStmt.executeQuery();

		if(rs.next()) {
			return true;
		}

		return false;
		
	}


	public static void addLike( int pid,int uid) {

		try {

			logger.info("<>Inside addLike Method()");
			String insQry = " insert  into liked (  pid , uid ) values ( ? , ? ) ";
			PreparedStatement preStmt = con.prepareStatement(insQry);
			preStmt.setInt(1,pid);
			preStmt.setInt(2,uid);

			preStmt.executeUpdate();
		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}
	}


	private static void removeLike( int pid,int uid) throws SQLException {

		try {

			logger.info("<>Inside removeLike Method()");
			String delQry = " delete from liked where pid = ? and uid = ?  ";
			PreparedStatement preStmt = con.prepareStatement(delQry);
			preStmt.setInt(1,pid);
			preStmt.setInt(2,uid);

			preStmt.executeUpdate();
		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}
	}
*/
}
