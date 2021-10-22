

package com.tech.blog.servlet;

import com.tech.blog.dao.PostDao;
import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import mysqlconnection.Helper;
import mysqlconnection.connectionprovider;

/**
 *
 * @author DELL
 */
@MultipartConfig
public class editforpostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editforpostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
           
              
            
            //fetch all post data
            String posttitle=request.getParameter("user_ptitle");
            String postcontent=request.getParameter("user_pcontent");
            String postcode=request.getParameter("user_pcode");
 //           Part part =request.getPart("image");
//            String imageName=part.getSubmittedFileName();
            // out.println("01 profile updated to db");
            
            //get user from the session
         
          
          int postId=Integer.parseInt(request.getParameter("pid"));
          // out.println(postId);
           PostDao d=new PostDao(connectionprovider.getConnection());
           Post posts=d.getPostByPostId(postId);
           //out.println("02 profile updated to db");
//            
             posts.setpTitle(posttitle);
             posts.setpContent(postcontent);
             posts.setpCode(postcode);
//             String oldFile=post.getpPic();
//             post.setpPic(imageName);
            
             
             
           //update database....
             PostDao postdao=new PostDao(connectionprovider.getConnection());
             boolean ans=  postdao.updatePost( posts);
             //out.println("03 profile updated to db");  
             if(ans)
        {
            // out.println("04 profile updated to db");  
            
         //  String path = request.getRealPath("/") + "/pics" + File.separator + posts.getpPic();
//            
//            
//            //delete code
            // String pathOldFile = request.getRealPath("/") + "pics" + File.separator + oldFile;
           
             //if(!oldFile.equals("default.png"))
            // {
//               Helper.deleteFile(pathOldFile);   
            // }
//             
//          
//              if( Helper.saveFile(part.getInputStream(), path))
//              {
//                out.println("profile updated to db"); 
                Message msg=new Message("profile updated to db","success","alert-success");
//               
//                s.setAttribute("msg", msg);
//              }
//              else
//              {
//                  
//                  out.println("not updated your pic");
//                  Message msg=new Message("something went wrong","error","alert-danger");
//               
//               //  s.setAttribute("msg", msg);
//            }
          
            
        }
        else
        {
             out.println("not updated");
             Message msg=new Message("something went wrong","error","alert-danger");
               
            // s.setAttribute("msg", msg);
        }
//        
            response.sendRedirect("Profile.jsp");
             
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(editforpostServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
           // Logger.getLogger(editforpostServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
