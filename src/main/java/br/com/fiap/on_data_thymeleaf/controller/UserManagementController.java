package br.com.fiap.on_data_thymeleaf.controller;

import br.com.fiap.on_data_thymeleaf.entity.Role;
import br.com.fiap.on_data_thymeleaf.entity.User;
import br.com.fiap.on_data_thymeleaf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.values());
        model.addAttribute("selectedRole", "USER"); // Default role
        model.addAttribute("isNewUser", true);
        return "admin/users/form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user, @RequestParam String role) {

        try {
            // Explicitly convert string to Role enum
            Role userRole = Role.valueOf(role);
            userService.createUser(user, role);
            return "redirect:/admin/users";
        } catch (IllegalArgumentException e) {
            // Handle the error, perhaps redirect back to the form with an error message
            return "redirect:/admin/users/create?error=invalidRole";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.values());
        model.addAttribute("selectedRole", user.getRole().name());
        model.addAttribute("isNewUser", false);
        return "admin/users/form";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user,
                             @RequestParam String role) {

        try {
            // Explicitly convert string to Role enum
            Role userRole = Role.valueOf(role);
            userService.updateUser(id, user, role);
            return "redirect:/admin/users";
        } catch (IllegalArgumentException e) {
            // Handle the error, perhaps redirect back to the form with an error message
            return "redirect:/admin/users/edit/" + id + "?error=invalidRole";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}


