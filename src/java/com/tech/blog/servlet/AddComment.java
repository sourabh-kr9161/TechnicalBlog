package com.tech.blog.servlet;

import com.tech.blog.dao.CommentDao;
import com.tech.blog.entities.CommentDetails;
import java.io.*;
//import static java.lang.System.out;
import static java.lang.System.out;
import java.net.URLDecoder;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


public class AddComment extends HttpServlet {

	private Logger logger = Logger.getLogger("com.my.connection");

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		logger.info("<>Inside AddComment Class");

                out.println("Hello world");
		HttpSession session = req.getSession();
		URLDecoder.decode(req.getQueryString(),"UTF-8");
		
		int postId = Integer.parseInt(req.getParameter("postId"));
                out.println(postId);
		int postPublisherId = Integer.parseInt(req.getParameter("postPublisherId"));
		int commentParentId = Integer.parseInt(req.getParameter("commentParentId"));
		int commentPublisherId = (int)session.getAttribute("userId");
		String commentContent = req.getParameter("commentcontent").toString();

		CommentDetails model = new CommentDetails();
		model.setPostId(postId);
		model.setPostPublisherId(postPublisherId);
		model.setCommentParentId(commentParentId);
		model.setCommentPublisherId(commentPublisherId);
		model.setCommentContent(commentContent);


		int commentId = CommentDao.getCommentId(model) + 1;
		model.setCommentId(commentId);

		CommentDao.addComment(model);

		PrintWriter out = res.getWriter();
		out.print("Success");
		out.flush();
	}
}