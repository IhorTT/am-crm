package net.ukr.tigor.controller;

import net.ukr.tigor.domain.Enums.Role;
import net.ukr.tigor.domain.User;
import net.ukr.tigor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String UserList(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "userList";
    }

    @GetMapping("{user}/edit")
    public String get(@PathVariable User user, Model model) {
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("roles", Role.values());
        return "/edit/userEdit";
    }

    @GetMapping("{user}/delete")
    public String delete(@PathVariable User user, Model model) {
        userService.delete(user);
        return "redirect:/settings?tab=users";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form,
            @RequestParam(required = false, value = "") String btn) {
        if (btn.equals("btnOk")) {
            userService.creatUpdate(user, form);
        }

        return "redirect:/settings?tab=users";
    }

}
