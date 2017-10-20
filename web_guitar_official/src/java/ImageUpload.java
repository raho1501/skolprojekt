/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.Constants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author markus
 */
@WebServlet(urlPatterns = {"/webresources/ImageUpload"})
@MultipartConfig
public class ImageUpload extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	
	private final static Logger LOGGER =
            Logger.getLogger(ImageUpload.class.getCanonicalName());
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		Collection<Part> fileParts = request.getParts();
		Part filePart = fileParts.iterator().next();
		
		for(Part itt : fileParts)
		{
			filePart = itt;
		}
		
		String fileName = filePart.getSubmittedFileName();
		
		int indexOfExtension = fileName.indexOf(".");
		if(indexOfExtension <= 0)
		{
			throw new RuntimeException("Filename or file type is not supported.");
		}
		
		String extension = fileName.substring(indexOfExtension, fileName.length());
		
		if(!".png".equals(extension) &&
			!".jpg".equals(extension) &&
			!".jpeg".equals(extension) &&
			!".gif".equals(extension))
		{
			throw new RuntimeException("Filename or file type is not supported.");
		}
		
		String randFileName = UUID.randomUUID().toString();
		
		fileName = randFileName + extension;
		
		File newFile = new File(Constants.uploadPath, fileName);
		
		InputStream input = filePart.getInputStream();
		
		Files.copy(input, newFile.toPath());
		
		response.getWriter().print(fileName);
		response.getWriter().close();
	}
	
	
	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
					content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

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
