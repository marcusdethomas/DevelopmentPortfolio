package com.mvc.many.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.bookclub.models.Book;
import com.mvc.many.models.Entry;
import com.mvc.many.models.Tag;
import com.mvc.many.models.User;
import com.mvc.many.models.UserLogin;
import com.mvc.many.services.EntryService;
import com.mvc.many.services.TagService;
import com.mvc.many.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	EntryService entryService;
	@Autowired
	TagService tagService;
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/register";
	}
	
	@GetMapping("/index")
	public String mainPage() {
		return "index.jsp";
	}
	// user registration
	
	@GetMapping("/register")
	public String index(Model model) {
		 model.addAttribute("newUser", new User());
		 model.addAttribute("newLogin", new UserLogin());
		 return "index.jsp";
		}
	@PostMapping("/register")
	public String authentication(@Valid @ModelAttribute("newUser") 
			User newUser, BindingResult result, Model model, HttpSession session) {
		User user = userService.register(newUser, result);
	    if(result.hasErrors()) {
	        // Be sure to send in the empty LoginUser before re-rendering the page.
	        model.addAttribute("newLogin", new UserLogin());
	        return "index.jsp";
	    }
	    
	    session.setAttribute("userId", newUser);
		return "redirect:/dashboard";
		}
	
	// user home page
	
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("entries") Entry entry,@ModelAttribute("tags") Tag tag,
			Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		model.addAttribute("entries", entryService.all());
		model.addAttribute("tags", tagService.all());
		return "dashboard.jsp";
	}
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") UserLogin newLogin, BindingResult result, Model model, HttpSession session) {
	     
		User user = userService.login(newLogin, result);
	 
	    if(result.hasErrors() || user==null) {
	        model.addAttribute("newUser", new User());
	        return "index.jsp";
	    }
	    
	    session.setAttribute("userId", user);
	    return "redirect:/dashboard";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	 session.setAttribute("userId", null);
	     return "redirect:/";
	}
	
	
	// view entries
	@GetMapping("/entries/{id}")
	public String books(@PathVariable("id")Long id, Model model, @ModelAttribute("user") User user,
			@ModelAttribute("entries") Entry entry,@ModelAttribute("tags") Tag tag, HttpSession session){
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		model.addAttribute("entries", entryService.findBy(id));
		model.addAttribute("tags", tagService.all());
		System.out.println(tag);
		return "show.jsp";
	}
	
	// add entry
	@GetMapping("/create")
	public String newBook(@ModelAttribute("entries") Entry entry, Model model, @ModelAttribute("tags") Tag tag, 
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		model.addAttribute("tags", tagService.all());
		return "create.jsp";
	}
		
	// create entry
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("entries") Entry entry, @ModelAttribute("tags") Tag tag,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("entries", entryService.all());
			System.out.println("Error present!");
			return "create.jsp";
		}
		model.addAttribute("tags", tagService.all());
		model.addAttribute("entries", entryService.all());
		this.entryService.save(entry);
		return "redirect:/dashboard";
	}
	
	// edit entry
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id")Long id, Model model, HttpSession session) {
		Entry entry = entryService.findBy(id);
		model.addAttribute("tags", tagService.all());
    	model.addAttribute("entries", entry);
		return "update.jsp";
	}
	@PostMapping("/edit/{id}")
	public String editEntry(@PathVariable("id")Long id,@Valid @ModelAttribute("entries") Entry entry, 
			@ModelAttribute("tags") Tag tag, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("entries", entryService.all());
			System.out.println("Error present!");
			return "update.jsp";
		}
		model.addAttribute("entries", entryService.all());
		this.entryService.save(entry);
		return "redirect:/dashboard";
	}
	
	// delete
	// delete book
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable("id")Long id, @ModelAttribute("entries") Entry entry, Model model, HttpSession session) {
			model.addAttribute("entries", entryService.all());
			this.entryService.delete(entry);
			return "redirect:/dashboard";
		}
	// image handling
	 
}
