package no.helleroy.messaging.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/principal", method = RequestMethod.GET)
    @ResponseBody
    public Principal principal(Principal principal) {
        return principal;
    }
}
