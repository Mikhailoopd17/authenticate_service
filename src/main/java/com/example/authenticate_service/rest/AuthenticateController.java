//package com.example.authenticate_service.rest;
//
//import com.example.authenticate_service.pojo.Authenticate;
//import com.example.authenticate_service.pojo.User;
//import com.example.authenticate_service.service.AuthtenticateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping(value = "${web.prefix}/auth", produces = "application/json; charset=UTF-8")
//public class AuthenticateController {
//    @Autowired
//    private AuthtenticateService authtenticateService;
//
//    @GetMapping("/{id}")
//    public Authenticate getById(@PathVariable Long id) {
//        return authtenticateService.getOneById(id);
//    }
//
//    @PostMapping
//    public HttpServletResponse getAuthenticate(HttpServletRequest request,
//                                               HttpServletResponse response,
//                                               @RequestBody User user) {
//        return authtenticateService.getAuthenticate(request, response, user);
//    }
//}
