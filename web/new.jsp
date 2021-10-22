


<%@page import="com.tech.blog.entities.Post"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="com.tech.blog.dao.LikeDao"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.tech.blog.dao.UserDao"%>
<%@page import="mysqlconnection.connectionprovider"%>
<%@page import="com.tech.blog.entities.Message"%>
<%@page import="com.tech.blog.entities.User"%>
<%@page errorPage="error_page.jsp"%>

<%

User user=(User)session.getAttribute("currentUser");
if(user==null)
{
    response.sendRedirect("login_page.jsp");
}

%>



<% 
int postId=Integer.parseInt(request.getParameter("post_id"));
PostDao d=new PostDao(connectionprovider.getConnection());
Post p=d.getPostByPostId(postId);
p.setPid(postId);


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
         <style>
             
             .row-user2{
          margin-left:  170px;
          margin-top: -20px;
   }
             body{
       background:url(img/background.png);
       background-size: cover;
       background-attachment: fixed;
   }  
                
   

        </style>
       <!--doted for css-->

       <style>
                *{margin: 0;padding:0px}

               .header{
                  //  width: 100%;
                   // background-color: primary-background ;
                   // height: 40px;
                }

               .showLeft{
                //  background-color: #0d77b6 !important;
                   // border:1px solid #0d77b6 !important;
                   // text-shadow: none !important;
                   /// color:#fff !important;
                    padding:5px;
                }

                .icons li {
                    background: none repeat scroll 0 0 #fff;
                    height: 7px;
                    width: 7px;
                    line-height: 0;
                    list-style: none outside none;
                    margin-right: 15px;
                    margin-top: 3px;
                    vertical-align: top;
                    border-radius:50%;
                    pointer-events: none;
                }
                 .btn-left {
                    left: 0.4em;
                }

                .btn-right {
                    right: 0.4em;
                }

                .btn-left, .btn-right {
                   position: absolute;
                    top: 0.24em;
                }

                .dropbtn {
                   // background-color: #4CAF50;
                    position: absolute;
                    color: white;
                    font-size: 16px;
                    border: none;
                    cursor: pointer;
                }

                .dropbtn:hover, .dropbtn:focus {
                  // background-color: #3e8e41;
                }

                .dropdown {
                    position: absolute;
                    display: inline-block;
                    right: 0.4em;
                }
                  .dropdown-content {
                    display: none;
                   position: relative;
                    margin-top: 10px;
                   // background-color: #f9f9f9;
                   background-color: white;
                    min-width: 160px;
                    overflow: auto;
                    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                   // z-index: 1;
                }

                .dropdown-content a {
                    color: black;
                    padding: 12px 16px;
                    text-decoration: none;
                    display: block;
                }

                .dropdown a:hover {background-color: #f1f1f1}

                .show {display:block;}

            </style>
        
   
        
    </head>
    <body>
        <!--------------navbar start--------->
    
    <nav class="navbar navbar-expand-lg navbar-dark primary-background">
    <a class="navbar-brand" href="index.jsp"><span class="fa fa-tint"></span> Tech Blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
          <a class="nav-link" href="Profile.jsp"><span class="fa fa-home"></span>Home <span class="sr-only">(current)</span></a>
      </li>
     
      <li class="nav-item dropdown5">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span class="fa fa-check-square-o"></span> Categories
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">programming language</a>
          <a class="dropdown-item" href="#">project Implementation</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Data structure</a>
        </div>
      </li>
       <li class="nav-item">
           <a class="nav-link" href="#"><span class="fa fa-phone"></span> contact</a>
      </li>
       <li class="nav-item">
           <a class="nav-link" href="#" data-toggle="modal" data-target="#add-post-modal"><span class="fa fa-asterisk"></span> Do Post</a>
      </li>
         
     
    </ul>
      
      <ul class="navbar-nav mr-right">
           <li class="nav-item">
           <a class="nav-link" href="editpost_page.jsp" ><span class="fa fa-user-circle"></span> <%= user.getName() %></a>
        </li>
           <li class="nav-item">
           <a class="nav-link" href="LogoutServlet"><span class="fa fa-user-plus"></span> Logout</a>
        </li>
          
      </ul>
  </div>
</nav>
    
    <!-----------end of navbar----------->
       
    
                            <%
                        Message m=(Message)session.getAttribute("msg");
                        if(m!=null)
                        {
                            %>
                            
                            <div class="alert <%= m.getCssClass() %>" role="alert">
                               <%= m.getContent() %>
                            </div>
                            <%
                            session.removeAttribute("msg");
                        }
                            %>
    
    <!--main content of body-->
    
    <div class="container  ">
        <div class="row my-4">
            <div class="col-md-8 offset-md-2">
                <div class="card " id="profile-details2">
               <!--  <div class="card-header ">
                        <h4 class="post-title"><%= p.getpTitle() %></h4>
                        
                 </div>-->
                   <div class="card-header primary-background text-white ">
                         <div class=" row my-3 ">
                            <div class=" col-md-8">
                                  <h4 class="post-title"><%= p.getpTitle() %></h4>
                            </div>
                            <div class="col-md-4 ">
                               
                                <p class="row-user2">
                                    <!--<i   class='fa fa-ellipsis-v' onclick="showDropdown()" ></i>-->
                                    
                         <div class="header">
                      <!--<i class='fa fa-ellipsis-v'></i>
                <!-- three dot menu -->
                <div class="dropdown">
                    <!-- three dots -->
                    <ul class="dropbtn icons btn-right showLeft" onclick="showDropdown()">
                         <li></li>
                        <li></li>
                        <li></li>
                        
                    </ul>
                   
                    <!-- menu -->
                    <div id="myDropdown" class="dropdown-content">
                        <a  href="#!" id="edit-profile-button" data-toggle="modal" data-target="#exampleModal">Edit</a>
                        <a href="deletpostServlet?post_id=<%= p.getPid() %>">Delete</a>
                        <a href="#contact">Copy link</a>
                    </div>
                </div>
                                 
                          </div>
                                </p>
                   
                            </div>
                        </div>
                          
                </div>
                  <!--  <div class="header">
                      <!--<i class='fa fa-ellipsis-v'></i>
                <!-- three dot menu 
                <div class="dropdown">
                    <!-- three dots 
                    <ul class="dropbtn icons btn-right showLeft" onclick="showDropdown()">
                        <!-- <li></li>
                        <li></li>
                        <li></li>
                         <i class='fa fa-ellipsis-v'></i>
                    </ul>
                   
                    <!-- menu 
                    <div id="myDropdown" class="dropdown-content">
                        <a href="#home">Home</a>
                        <a href="#about">About</a>
                        <a href="#contact">Contact</a>
                    </div>
                </div>

                          </div> -->
                      
                
                    <div class="card-body">
                        
                        <img  class="card-img-top my-2"src="blog_pics/<%= p.getpPic() %>"alt="Card image cap">
                        <div class="row my-3 row-user">
                            <div class="col-md-8">
                                <% UserDao ud=new UserDao(connectionprovider.getConnection()); %>
                                
                                <p class="post-user-info"><a href="#!"><%= ud.getUserByUserId(p.getUserId()).getName() %> </a>has posted</p> 
                            </div>
                            <div class="col-md-4">
                                <p class="post-date"><%= DateFormat.getDateTimeInstance().format(p.getpDate()) %></p>
                                
                            </div>
                        </div>
                        <p class="post-content"><%= p.getpContent() %></p>
                        <br>
                        <br>
                        <div class="post-code">
                        <pre><%= p.getpCode() %> </pre>
                        </div>
                    </div>
                    <div class="card-footer primary-background">
                        
                        <%
                        LikeDao ld=new LikeDao(connectionprovider.getConnection());
                        %>
                        <a href="#!" onclick="doLike(<%= p.getPid()%>,<%= user.getId() %>)" class="btn btn-outline-light btn-sm"><i class="fa fa-thumbs-o-up"></i> <span class="like-counter"><%= ld.countLikeOnPost(p.getPid())%></span></a>
                        <a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-commenting-o"></i> <span>20</span></a>
                       
                        
                    </div>
                        <div class="card-footer">
                            <div class="fb-comments" data-href="http://localhost:8084/TechBlog1/show_blog_page.jsp?post_id=<%= p.getPid()%>" data-width="" data-numposts="5"></div> 
                        </div>
                </div>
            </div>
            
        </div>  
    </div>
    
    
    
    
    <!--end of main content of body-->
    
    
        <!---------------------profile model------------------->
    
 

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header primary-background text-white text-center "  >
        <h5 class="modal-title" id="exampleModalLabel" >Tech Blog</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        
      <div class="modal-body">
          <div class="container text-center">
    <!-- <img src="pics/<%= user.getProfile()%>" class="img-fluid" style="border-radius:50%;max-width:150px;">
         <br>
        <h5 class="modal-title mt-3" id="exampleModalLabel"><%= user.getName() %></h5>
       
        <!-------------details-----------
        
        <div id="profile-details">
  <table class="table">
  
  <tbody>
    <tr>
      <th scope="row">ID:</th>
      <td><%= user.getId() %></td>
   
    </tr>
    <tr>
      <th scope="row">EMAIL:</th>
      <td><%= user.getEmail()%></td>
     
    </tr>
    <tr>
      <th scope="row">GENDER:</th>
      <td><%= user.getGender() %></td>
    
    </tr>
     <tr>
      <th scope="row">ABOUT:</th>
      <td><%= user.getAbout() %></td>
    
    </tr>
  </tbody>
</table>
      </div>-->
      <!------------profile edit--------------->
     <div id="profile-edit" style="display: none;">
          <h3 class="mt-2">Please Edit Carefully..</h3>
          <form action="editforpostServlet" method="POST" enctype="multipart/form-data" >
           
              <table class="table">
                 
                  <tr>
                      <td>Title: </td>
                          
                      <td> <input type="text" class="form-control" name="user_ptitle" value="<%=  p.getpTitle() %>">   </td>
                  </tr>
                  
             
                 
                  <tr>
                      <td>Content: </td>
                          
                      <td> <textarea type="text" class="form-control" name="user_pcontent"> <%= p.getpContent() %></textarea>   </td>
                  </tr>
                  <tr>
                      <td>Code: </td>
                          
                      <td> 
                          <textarea type="text" class="form-control" name="user_pcode"> <%= p.getpCode() %> </textarea>   
                         
                      </td>
                  </tr>
                          <tr><td>
                                  <input type="hidden" name="pid" value="<%= p.getPid() %>">
                                      
                                  
                      </td></tr>
              <!--    <tr>
                      <td> Pic: </td>
                          
                      <td> <input type="file" class="form-control" name="image" >  </td>
                  </tr>-->
                  
              </table>
     
                  <div class="container">
                      <button type="submit" class="btn btn-outline-primary">save</button>
                  </div>
          </form>
      </div>  
        
         </div>
        
      </div>
      
      <div class="modal-footer">
<!--        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        
        <button id="edit-profile-button" type="button" class="btn btn-primary">EDIT</button>-->
      </div>
    </div>
  </div>
</div>
    
    

    <!---------------------end of profile modal---------------------->
        
    
    
    
    
    
     
    
    
            <!---------------javascript---------------------->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script src="js/myjs.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
              
          <script>
            $(document).ready(function(){
                // let editStatus=false;
                $('#edit-profile-button').click(function(){
                   
                 // if(editStatus == false)
                  {
//                        $("#profile-details").hide();
                          $("#profile-edit").show();
//                       editStatus=true;
//                       $(this).text("Back");
                  }
                 // else
                  {
//                       $("#profile-details").show();
//        
//                         $("#profile-edit").hide();
//                         editStatus=false;
//                          $(this).text("Edit");
                  }
                 
       
        
               }); 
            });
            </script>
            
            <script>
                function changeLanguage(language) {
                    var element = document.getElementById("url");
                    element.value = language;
                    element.innerHTML = language;
                }

                function showDropdown() {
                    document.getElementById("myDropdown").classList.toggle("show");
                }

                // Close the dropdown if the user clicks outside of it
                window.onclick = function(event) {
                    if (!event.target.matches('.dropbtn')) {
                        var dropdowns = document.getElementsByClassName("dropdown-content");
                        var i;
                        for (i = 0; i < dropdowns.length; i++) {
                            var openDropdown = dropdowns[i];
                            if (openDropdown.classList.contains('show')) {
                                openDropdown.classList.remove('show');
                            }
                        }
                    }
                }
            </script>
            
           
    </body>
</html>
