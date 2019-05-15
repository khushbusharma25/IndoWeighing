package com.ind.weighing.Indo_weighing.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
    public String welcome() {
        return "welcome";
    }
/*//	@GetMapping("/index")
//    public String index() {
//        return "index";
//    }
	
	@GetMapping("/login")
	public  String login() {
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@GetMapping("/error")
	public String error() {
		System.out.println("Error page");
		return "/login-error";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public String authenticate(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "index";
    }
	
	@GetMapping( "/404,/error")
	public String accesssDenied() {
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		
	  }
	  return "403";
	}*/
}
