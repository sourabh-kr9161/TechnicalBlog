/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tech.blog.servlet;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
public class EditServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
                        
            //fetch all data
            
            
            String userEmail=request.getParameter("user_email");
            String userName=request.getParameter("user_name");
            String userPassword=request.getParameter("user_password");
            String userGender=request.getParameter("user_gender");
            String userAbout=request.getParameter("user_about");
            Part part = request.getPart("image");
            String imageName=part.getSubmittedFileName();
            out.println(imageName);
            
            //get user from the session
        HttpSession s=request.getSession();
        User user=(User)s.getAttribute("currentUser");
        user.setEmail(userEmail);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setGender(userGender);
        user.setAbout(userAbout);
        String oldFile=user.getProfile();
        user.setProfile(imageName);

        
        //update database....
        UserDao userDao = new UserDao(connectionprovider.getConnection());
        boolean ans = userDao.updateUser(user);
        
        if(ans)
        {
             //out.println("profile updated to db");  
            
             String path = request.getRealPath("/")+"/pics"+File.separator+user.getProfile();
            
             //out.println(path);
            //delete code
             String pathOldFile = request.getRealPath("/") + "/pics" + File.separator + oldFile;
             if(!oldFile.equals("default.png"))
             {
                Helper.deleteFile(pathOldFile);   
            }
             
          
              if (Helper.saveFile(part.getInputStream() , path))
              {
                 out.println("profile updated to db"); 
                 Message msg = new Message("profile updated to db", "success", "alert-success");
               
                 s.setAttribute("msg", msg);
              }
              else
              {
                  
                  //out.println("not updated your pic");
                   Message msg=new Message("something went wrong", "error", "alert-danger");
               
                   s.setAttribute("msg", msg);
              }
          
            
        }
        else
        {
             out.println("not updated");
             Message msg=new Message("something went wrong", "error", "alert-danger");
               
             s.setAttribute("msg", msg);
        }
        
        response.sendRedirect("Profile.jsp");
            
            
        //.........................edit posts...................................
        
        
//           String pTitle=request.getParameter("pTitlee");
//            String pContent=request.getParameter("pContentt");
//            String pCode=request.getParameter("pCodee");
//           
//            Part parts =request.getPart("picc");
//            String imageNames=parts.getSubmittedFileName();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
            
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
//        processRequest(request, response);
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
        processRequest(request, response);
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
