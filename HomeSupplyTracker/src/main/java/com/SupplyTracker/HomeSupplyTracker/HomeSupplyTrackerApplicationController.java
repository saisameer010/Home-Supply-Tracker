package com.SupplyTracker.HomeSupplyTracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.SupplyTracker.Database.DbAccessDAOImpl;
import com.SupplyTracker.Pojo.*;
import com.SupplyTracker.Purchase.Mail;

// This Controller Class is used to redirect all the requests
@Controller
public class HomeSupplyTrackerApplicationController {
	// this is to access databse elements and functions
	private DbAccessDAOImpl dba;

	// this is to toggle between show and hide table
	boolean show = true;

	// currentProducts is used to store elements given in by the user
	// databaseProducts is used to store the data present in the database
	private HashMap<String, Product> currentProducts = new HashMap<>();
	private HashMap<String, Product> databaseProducts = new HashMap<>();
	private HashSet<String> TypeSet = new HashSet<String>();
	private HashSet<String> ItemSet = new HashSet<String>();
	private boolean err = false;
	// this is created to help with the display of final List Because
	// currentProducts is cleared at the end
	private HashMap<String, Product> temp = new HashMap<>();

	// This keeps track of the Total Amount
	private float total = 0;

	public HomeSupplyTrackerApplicationController() {

	}

	// here we have the root mapping
	@RequestMapping(value = "/")
	public String LoginPage(Model model) {
		// We are retrieving the DbAccessDAOImpl Data Connection Details from
		// application context to help set JdbcTemplate

		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

			dba = (DbAccessDAOImpl) ctx.getBean("Dba");

		} catch (Exception e) {
			System.out.println("\n Exceptin db access");
		}

		// Here we map all the database values to databaseProducts
		List<Product> li = dba.Select();
		try {
			for (Product p : li) {
				String name = p.getName();
				System.out.println(name);
				TypeSet.add(p.getType());
				ItemSet.add(p.getName());
				Product produc = new Product(name, p.getType(), p.getQuantity(), p.getPrice(), p.getDesc());
				databaseProducts.put(name, produc);
			}
			model.addAttribute("Log", err);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println(TypeSet);
		model.addAttribute("TypeSet", TypeSet);
		model.addAttribute("ItemSet", ItemSet);
		model.addAttribute("show", show);
		return "login";// "services";//
	}

	// Here We redirect when Dashboard comes in
	// We take in Model parameter to give data to the jsp
	// ModelAttribute to get product data from the jsp
	// Show is taken as input when hide Database or show Database buttons are
	// clicked
	// Copy toggles to copy databaseProducts to currentProducts
	// Username And Password are also validated here
	@RequestMapping(value = "/Dashboard")
	public String dashboard(Model model, @ModelAttribute("product") Product p,
			@RequestParam(required = false) String Show, @RequestParam(required = false) String Copy,
			@RequestParam(required = false) String username, @RequestParam(required = false) String password) {

		// if login details are wrong Redirect to login page untill correct details are
		// given as input
		// validate()
		if (username != null && password != null) {
			if (!(username.contentEquals("admin") && password.equals("1234"))) {
				err = true;
				return "redirect:";
			}
		}
		err = false;
		// this is given to the jsp file to display in case of early termination
		model.addAttribute("Map2", databaseProducts);

		// This is used to copy the contents of databaseProducts to Current Products
		if (Copy != null && Copy.equals("true")) {
			currentProducts.putAll(databaseProducts);
			model.addAttribute("Map", currentProducts);

			return "DashBoard";
		}

		// adding this here will update the dashboard if it comes back from another page
		model.addAttribute("Map", currentProducts);

		// this is used to toggle between the Show database and hide database buttons in
		// jsp
		if (Show != null && Show.equals("true")) {
			show = true;
			model.addAttribute("Show", show);
			return "DashBoard";
		} else if (Show != null && Show.equals("false")) {
			show = false;
			model.addAttribute("Show", show);
			p.setName(null);
		}

		// if this is the first time dashboard is being accessed then we add the product
		// attribute to the Jsp file
		// System.out.println(p+p.getName());
		if ((p.getName() == null || p == null) && currentProducts.isEmpty()) {
			Product prod = new Product();
			model.addAttribute("product", prod);
			return "DashBoard";
		}

		// if the list already has the said product we update the quantity
		// rest of the cases are to prevent going in this block with invalid Product
		// variables

		else if (p != null && p.getName() != null && currentProducts.containsKey(p.getName().strip())
				&& !p.getName().equals("0")) {
			// update quantity
			p.setQuantity(currentProducts.get(p.getName().strip()).getQuantity() + p.getQuantity());
			// set price
			float pr = dba.SelectPrice(p.getName().strip());
			p.setPrice(pr);
			if (p.getQuantity() <= 0)
				currentProducts.remove(p.getName().strip());
			else
				currentProducts.replace(p.getName().strip(), p);
			model.addAttribute("Map", currentProducts);
		}
		// This runs if this is the first time the object is being accessed in the
		// database
		else if (p != null && p.getName() != null && !currentProducts.containsKey(p.getName().strip())
				&& p.getName() != null && !p.getName().equals("0")) {
			// we get the missing values i.e price and Description.. if they exist else we
			// add 0 price and none description
			float pr = dba.SelectPrice(p.getName().strip());
			p.setDesc(dba.SelectDescription(p.getName().strip()));
			p.setPrice(pr);

			ItemSet.add(p.getName());
			TypeSet.add(p.getDesc());
			// add the values to the Product
			if (p.getQuantity() <= 0)
				currentProducts.remove(p.getName().strip());
			else
				currentProducts.put(p.getName().strip(), p);
			// calculate total
			total += p.getQuantity() * p.getPrice();
			model.addAttribute("Map", currentProducts);
		}
		// this block is written here for the dispaly to happen before redirecting
		if (Copy != null && Copy.equals("true"))
			Copy = "false";
		model.addAttribute("TypeSet", TypeSet);
		model.addAttribute("ItemSet", ItemSet);
		return "DashBoard";
	}

	// Here We redirect when Dashboard comes in
	// We have Model parameter to give data to the jsp
	// ModelAttribute to get product data from the jsp
	@RequestMapping(value = "/Update")
	public String update(Model model, @ModelAttribute("product") Product p) {
		// we append databaseProducts for it to print at the beginning
		model.addAttribute("Map2", databaseProducts);

		// if running for the first time we add modelAttribute Product
		if (p.getName() == null) {
			Product prod = new Product();
			model.addAttribute("product", prod);
			return "Updated";
		}

		// If it already Contains A product we Update its Details
		else if (databaseProducts.containsKey(p.getName())) {
			System.out.println("P Updt rpt");
			dba.Update(p);
			databaseProducts.replace(p.getName(), p);
			updatePriceInCart();
		}

		// if being added for first time we insert into the Databse
		else {
			dba.Insert(p);
			ItemSet.add(p.getName());
			TypeSet.add(p.getDesc());
			databaseProducts.put(p.getName(), p);
			updatePriceInCart();
		}
		model.addAttribute("TypeSet", TypeSet);
		model.addAttribute("ItemSet", ItemSet);
		return "Updated";
	}

	// Here we Send the mail to the Vendor notifying the items needed and displaying
	// the total;
	@RequestMapping("/Purchase")
	public String purchase(Model model) {
		// temp variable to display the products
		System.out.println(currentProducts);

		temp.putAll(currentProducts);
		System.out.println(temp + " " + total);
		model.addAttribute("Map3", temp);
		updateTotalPrice();
		model.addAttribute("Total", total);
		// this is used to send mail to the vendor
		Mail m = new Mail();
		m.SendMail(currentProducts);
		// clearing this session
		currentProducts.clear();
		return "purchase";
	}

	public void updatePriceInCart() {
		for (Map.Entry mapElement : databaseProducts.entrySet()) {
			if (currentProducts.containsKey((String) mapElement.getKey())) {
				Product p = (Product) mapElement.getValue();
				currentProducts.replace((String) mapElement.getKey(), p);
			}
		}
	}

	public void updateTotalPrice() {
		total = 0;
		for (Map.Entry mapElement : currentProducts.entrySet()) {
			Product p = (Product) mapElement.getValue();
			total += p.getPrice() * p.getQuantity();
		}
	}

}
