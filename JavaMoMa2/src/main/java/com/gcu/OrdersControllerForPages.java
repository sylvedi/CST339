package com.gcu;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.model.OrderModel;
import com.gcu.model.SearchOrdersModel;

@Controller
@RequestMapping("/orders")
public class OrdersControllerForPages {
	// uses dependency injection configuration found in the SpringConfig file 
		// to choose which busienssService will be utilized.
		@Autowired
		private OrdersBusinessServiceInterface ordersService;
		@GetMapping("/spa") 
		public String displaySPA(Model model)
		{
		// Display single page application
		model.addAttribute("title", "Orders");
		model.addAttribute("searchOrdersModel", new SearchOrdersModel());
		return "ordersSPA";
		}
		@GetMapping("/searchForm") 
		public String displaySearchForm(Model model)
		{
		// Display Login Form View
		model.addAttribute("title", "Search Orders");
		model.addAttribute("searchOrdersModel", new SearchOrdersModel());
		return "ordersSearchForm";
		}
		
		@GetMapping("/addNewForm") 
		public String displayAddNewForm(Model model)
		{
		// Display new order form
		model.addAttribute("title", "Add new order");
		model.addAttribute("orderModel", new OrderModel());
		return "ordersAddNewForm";
		}
		
		@PostMapping("/searchResults") 
		public String showAllOrders(@Validated SearchOrdersModel searchModel, BindingResult bindingResult, Model model) { 
		System.out.println("Performing search results for " + searchModel.getSearchTerm());
		// Check for validation errors
		        if (bindingResult.hasErrors()) 
		        {
		        model.addAttribute("title", "Search for Orders");
		            return "searchOrdersForm";
		        }
		List<OrderModel> orders = ordersService.searchOrders(searchModel.getSearchTerm());  
		 model.addAttribute("title", "Search for Orders");
		model.addAttribute("searchModel", searchModel);
		model.addAttribute("orders", orders);
		return "orders";
		}
		
		@GetMapping("/all") 
		public String showAllOrders( Model model) { 
		 
		List<OrderModel> orders = ordersService.getOrders();  
		 model.addAttribute("title", "Show all orders");
		model.addAttribute("searchModel", new SearchOrdersModel());
		model.addAttribute("orders", orders);
		return "orders";
		}
		
		@PostMapping("/addNew") 
		// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
		public String addOrder(@Validated OrderModel newOrder, BindingResult bindingResult, Model model) {
		// add the new order
		ordersService.addOne(newOrder);
		// get updated list of all the orders
		List<OrderModel> orders = ordersService.getOrders(); 
		// display all orders
		model.addAttribute("orders", orders); 
		model.addAttribute("searchModel", new SearchOrdersModel()); 
		return "orders";
		}
		 
		@GetMapping("/admin") 
		public String showOrdersForAdmin( Model model) { 
		 
		// display all orders with delete and edit buttons
		List<OrderModel> orders = ordersService.getOrders();  
		 model.addAttribute("title", "Edit or delete orders");
		model.addAttribute("searchModel", new SearchOrdersModel());
		model.addAttribute("orders", orders);
		// ordersAdmin page shows a table of orders including buttons for del and edit.
		return "ordersAdmin";
		}
		
		@PostMapping("/delete") 
		// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
		public String deleteOrder(@Valid OrderModel order, BindingResult bindingResult, Model model) {
		// add the new order
		ordersService.deleteOne(order.getId());
		// get updated list of all the orders
		List<OrderModel> orders = ordersService.getOrders(); 
		// display all orders
		model.addAttribute("orders", orders); 
		model.addAttribute("searchModel", new SearchOrdersModel()); 
		return "ordersAdmin";
		}
		
		@RequestMapping(value="/doDelete", method = RequestMethod.POST)
		public String deleteProduct (@RequestParam(value="_method", required=false) int id, Model model) {
			
			System.out.println(" This is the ID!!" + id);
			try {
				ordersService.deleteOne(id);
				model.addAttribute("orders", ordersService.getOrders());
				return "ordersAdmin";
			}catch (Exception e) {
				e.printStackTrace();
				return "orders";
			}
		}
		
		
		@PostMapping("/editForm") 
		public String displayEditForm(OrderModel orderModel, Model model)
		{
		// Display new order form
		model.addAttribute("title", "Edit order");
		model.addAttribute("orderModel", orderModel);
		return "ordersEditForm";
		}
		
		@PostMapping("/doUpdate") 
		// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
		public String updateOrder(@Valid OrderModel order, BindingResult bindingResult, Model model) {
		// add the new order
		ordersService.updateOne(order.getId(), order);
		// get updated list of all the orders
		List<OrderModel> orders = ordersService.getOrders(); 
		// display all orders
		model.addAttribute("orders", orders); 
		model.addAttribute("searchModel", new SearchOrdersModel()); 
		return "ordersEditForm";
		}
		
		@RequestMapping(value="/updateProduct", method = RequestMethod.POST)
		public String updateProduct (@RequestParam(value="_method", required=false) int id, Model model) {
			
			System.out.println(" This is the ID!!" + id);
			//placedHolderId = id;
			OrderModel order = new OrderModel();
			order.setId(id);
			
			model.addAttribute("title", "Update Order");
			model.addAttribute("orderModel", order);
			return "ordersEditForm";
			
		}
		


}
