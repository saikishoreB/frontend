package com.ecomm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecomm.DAO.CustomerDAO;
import com.ecomm.Model.Customer;
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDao;
	@RequestMapping(value="/all/registerform")
	public String getRegistrationForm(Model model){
		model.addAttribute("customer",new Customer());
		return "registerform";
	}
	@RequestMapping(value="/all/registercustomer")
	public String registerCustomer(@ModelAttribute(value="customer") Customer customer,Model model){

		//CHECK if Email is unique
		if(!customerDao.isEmailUnique(customer.getUser().getEmail())){
			model.addAttribute("error","Email id already exists.. please choose different email id");
			return "registerform";
		}
		
		//Call DAO to persist customer details
		customerDao.registerCustomer(customer);
		return "Login";
}

}
