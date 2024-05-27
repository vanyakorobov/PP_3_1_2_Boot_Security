package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = (UserServiceImpl) userService;
        this.roleService = roleService;
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String showEditUserForm(@RequestParam("id") long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, @RequestParam long id) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String allUsers(ModelMap model, @ModelAttribute("user") User user) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "web";
    }
}
