/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;
import javax.mail.Session;
import javax.mail.Transport;

/**
 *
 * @author markus
 */
@Named(value = "shopInputBean")
@RequestScoped
public class ShopInputBean {
	
	private String title;
	private String info;
	private String price;
	private Part imageFile;
	
	private String shopId;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	
	@Inject
	private ShopManagedBean shopManagedBean;
	@Inject
	private CustomerManagedBean customerManagedBean;

	/**
	 * Creates a new instance of ShopInputBean
	 */
	public ShopInputBean() {
	}
	
	public void removeShop(Shop shop)
	{
		if(shop.getCustomerIdFk() != null)
		{
			Customer customer = customerManagedBean.getCustomer(shop.getCustomerIdFk());
			customerManagedBean.removeCustomer(customer);
		}
		shopManagedBean.removeShop(shop);
	}
	
	public void removeCustomer()
	{
		if(shopId == null)
		{
			return;
		}
		Shop shop = shopManagedBean.getShop(Integer.parseInt(shopId));
		if(shop.getCustomerIdFk() == null)
		{
			return;
		}
		
		Customer customer = customerManagedBean.getCustomer(shop.getCustomerIdFk());
		customerManagedBean.removeCustomer(customer);
		
		shop.setCustomerIdFk(null);
		shopManagedBean.updateShop(shop);
	}

	/**
	 * @param shopManagedBean the shopManagedBean to set
	 */
	public void setShopManagedBean(ShopManagedBean shopManagedBean) {
		this.shopManagedBean = shopManagedBean;
	}

	/**
	 * @param customerManagedBean the customerManagedBean to set
	 */
	public void setCustomerManagedBean(CustomerManagedBean customerManagedBean) {
		this.customerManagedBean = customerManagedBean;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the imageFile
	 */
	public Part getImageFile() {
		return imageFile;
	}

	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}

	/**
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String saveImage() throws MessagingException
	{
		String filename = imageFile.getSubmittedFileName();
		
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

		try(InputStream input = imageFile.getInputStream())
		{
			Files.copy(input, savedFile.toPath());
		}
		catch(IOException e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
		
		return filename;
	}
	
	public String submitShop()
	{
		if(title.isEmpty()) return "shop";
		if(info.isEmpty()) return "shop";
		if(price.isEmpty()) return "shop";
		
		Shop shop = new Shop();
		
		shop.setTitle(title);
		shop.setInfo(info);
		shop.setPrice(Integer.parseInt(price));
		
		String filename = null;
		
		if(imageFile != null)
		{
			try
			{
				filename = saveImage();
			}
			catch(MessagingException e)
			{
				Logger.getLogger(getClass().getName()).
					log(Level.SEVERE, "exception caught", e);
				throw new RuntimeException(e);
			}
		}
		
		shop.setImageUrl(filename);
		
		shopManagedBean.addShop(shop);
		
		return "redirect_shop";
	}
	
	public String submitCustomer()
	{
		if(firstName.isEmpty()) return "redirect_shop";
		if(lastName.isEmpty()) return "redirect_shop";
		if(phoneNumber.isEmpty()) return "redirect_shop";
		if(email.isEmpty()) return "redirect_shop";
		if(shopId.isEmpty()) return "redirect_shop";
		
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		
		Shop shop = shopManagedBean.getShop(Integer.parseInt(shopId));
		
		Customer customer = new Customer();
		
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNr(phoneNumber);
		
		customerManagedBean.addCustomer(customer);
		
		shop.setCustomerIdFk(customer.getCustomerId());
		
		shopManagedBean.updateShop(shop);
		
		try
		{
			ShopInputBean.Send(
				Constants.mailAcount,
				Constants.mailPassword,
				Constants.toMail,
				email,
				"Reservation av gitarr " + shop.getTitle(),
				"En gitarr är reserverad av " + customer.getFirstName() +
					" " + customer.getLastName() + ". " +
					"<a href='http://" + ip.getHostAddress() +
					":8080/web_guitar_official/removeReservation.xhtml?id=" + 
					shop.getShopId() + "'" +
					">Klicka här för ta bort din reservation.</a>");
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		return "redirect_shop";
	}
	
	public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		/*
		If set to false, the QUIT command is sent and the connection is immediately closed. If set 
		to true (the default), causes the transport to wait for the response to the QUIT command.

		ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
			http://forum.java.sun.com/thread.jspa?threadID=5205249
			smtpsend.java - demo program from javamail
		 */
		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		// -- Create a new message --
		final MimeMessage msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress(username + "@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setText(message, "utf-8", "html");
		msg.setSentDate(new Date());

		Transport t = (Transport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}
}
