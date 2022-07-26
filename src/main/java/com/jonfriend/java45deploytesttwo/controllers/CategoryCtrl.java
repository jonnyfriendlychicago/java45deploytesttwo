package com.jonfriend.java45deploytesttwo.controllers;

//import java.util.List;

//import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonfriend.java45deploytesttwo.models.CategoryMdl;
import com.jonfriend.java45deploytesttwo.models.ProductMdl;
import com.jonfriend.java45deploytesttwo.services.CategorySrv;
import com.jonfriend.java45deploytesttwo.services.ProductSrv;
//import com.jonfriend.java45deploytesttwo.services.UserSrv;

@Controller
public class CategoryCtrl {

	@Autowired
	private ProductSrv productSrv;
	
	@Autowired
	private CategorySrv categorySrv;
	
//	@Autowired
//	private UserSrv userSrv;
	
	// display create-new category page
	@GetMapping("/store/category/new")
	public String newCategory(
			@ModelAttribute("category") CategoryMdl categoryMdl
			) {
		return "store/category/create.jsp";}
	
	// process the create-new category
	@PostMapping("/store/category/new")
	public String addNewCategory(
			@Valid @ModelAttribute("category") CategoryMdl categoryMdl
			, BindingResult result
			, Model model) {
		
		if(result.hasErrors()) {
			return "store/category/create.jsp";
		} else {
			categorySrv.addCategory(categoryMdl);
			return "redirect:/store";
		}	
	}
	
	// view/manage one category
	@GetMapping("/store/category/{id}")
	public String showCategory(
			@PathVariable("id") Long id
			, Model model
			) {
		CategoryMdl category = categorySrv.findById(id);
		
		model.addAttribute("category", categorySrv.findById(id));
		model.addAttribute("assignedProducts", productSrv.getAssignedCategories(category));
		model.addAttribute("unassignedProducts", productSrv.getUnassignedCategories(category));
		
		return "/store/category/record.jsp";
	}
	
	// process edits to that one category
	@PostMapping("/store/category/{id}")
	public String editCategory(
			@PathVariable("id") Long id
			, @RequestParam(value="productId") Long productId
			, Model model
			) {
		
		CategoryMdl category = categorySrv.findById(id);
		ProductMdl product = productSrv.findById(productId);
		
		category.getProductMdl().add(product);
		categorySrv.updateCategory(category);
		model.addAttribute("assignedProducts", productSrv.getAssignedCategories(category));
		model.addAttribute("unassignedProducts", productSrv.getUnassignedCategories(category));
		return "redirect:/store/category/" + id;
	}

// end ctrl
}
