<%@page import="com.tech.blog.dao.UserDao"%>
<%@page import="com.tech.blog.entities.User"%>
<%@page import="com.tech.blog.dao.LikeDao"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="mysqlconnection.connectionprovider"%>
<div class="row">
<%
User uuuu=(User)session.getAttribute("currentUser");
    //Thread.sleep(1000);
PostDao d=new PostDao(connectionprovider.getConnection());

int cid=Integer.parseInt(request.getParameter("cid"));
//Post pp=d.getPostByUserId(id);
//Post pp=d.getPostByPostId(id);
List<Post> postss=null;
if(cid==0){

 //postss=d.getAllPosts(); 
  postss=d.getAllPostByUserId(uuuu.getId());

}
else
{
      postss=d.getPostByUserIdCatId(uuuu.getId(),cid);
        // uuuu.getId(),cid
      //postss=d.getPostByCatId(cid);
//            UserDao dao=new UserDao(connectionprovider.getConnection());
//            User u=dao.getUserByEmailAndPassword(userEmail, userPassword);
}
if(postss.size()==0)
{
    out.println("<h3 class='display-3 text-center'> No posts are available right now </h3>");
    return;
}
for(Post p:postss)
{
    %>
    <div class="col-md-6 mt-2">
        <div class="card">
             <img class="card-img-top" src="blog_pics/<%= p.getpPic() %>" alt="Card image cap">
            <div class="card-body">
                <b><%= p.getpTitle() %></b>
                <p><%= p.getpContent() %></p>
               
            </div>
                <div class="card-footer primary-background text-center">
                  
                    
                   <%
                        LikeDao ld=new LikeDao(connectionprovider.getConnection());
                   %>
                    <a href="#!" onclick="doLike(<%= p.getPid()%>,<%= uuuu.getId() %>)" class="btn btn-outline-light btn-sm"><i class="fa fa-thumbs-o-up"></i> <span class="like-counter"><%= ld.countLikeOnPost(p.getPid())%></span></a> 
                   
                    <a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-commenting-o"></i> <span>20</span></a>
                    

                     
                    <a href="new.jsp?post_id=<%= p.getPid() %>"    class="btn btn-outline-light btn-sm">Read More</a>
               
              
                </div>
        </div>
    </div>
    
    <%
}



    



 %>




</div>
