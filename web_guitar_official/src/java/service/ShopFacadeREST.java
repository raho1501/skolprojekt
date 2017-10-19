/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Constants;
import beans.Shop;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.jboss.resteasy.plugins.providers.multipart.InputPart;
//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;




/**
 *
 * @author markus
 */
@Stateless
@Path("beans.shop")
public class ShopFacadeREST extends AbstractFacade<Shop> {

	@PersistenceContext(unitName = "web_guitar_officialPU")
	private EntityManager em;

	public ShopFacadeREST() {
		super(Shop.class);
	}

	@POST
        @Override
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void create(Shop entity) {
		super.create(entity);
	}

	@PUT
        @Path("{id}")
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void edit(@PathParam("id") Integer id, Shop entity) {
		super.edit(entity);
	}

	@DELETE
        @Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		super.remove(super.find(id));
	}

	@GET
        @Path("{id}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Shop find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
        @Override
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Shop> findAll() {
		return super.findAll();
	}

	@GET
        @Path("{from}/{to}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Shop> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(new int[]{from, to});
	}

	@GET
        @Path("count")
        @Produces(MediaType.TEXT_PLAIN)
	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
           //FUNGERAR INTE.
        @POST
        @Path("/upload")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        public Response uploadFile() {
               
            File imageFile = null;
            String filename="";// = param;
		
            int indexOfExtension = filename.indexOf(".");
            if(indexOfExtension <= 0)
            {
                    throw new RuntimeException("Filename or file type is not supported.");
            }

            String extension = filename.substring(indexOfExtension, filename.length());

            if(!".png".equals(extension) &&
                    !".jpg".equals(extension) &&
                    !".jpeg".equals(extension) &&
                    !".gif".equals(extension))
            {
                    throw new RuntimeException("Filename or file type is not supported.");
            }

            String randFileName = UUID.randomUUID().toString();

            filename = randFileName + extension;
            // /home/markus/NetBeansProjects/skolprojekt/web_guitar_official/web/resources/uploaded_img
            // /var/web_guitar_official/images
            File savedFile = new File(Constants.uploadPath, filename);

            try(InputStream input = new FileInputStream(imageFile))
            {
                    Files.copy(input, savedFile.toPath());
            }
            catch(IOException e)
            {
                    Logger.getLogger(getClass().getName()).
                            log(Level.SEVERE, "exception caught", e);
                    throw new RuntimeException(e);
            }
		

        return Response.ok("Cool Tools!").build();

        }

	
}
