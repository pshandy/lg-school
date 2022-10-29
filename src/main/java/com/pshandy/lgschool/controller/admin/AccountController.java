package com.pshandy.lgschool.controller.admin;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/")
    public String getAccounts(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("accounts", accountService.getAccounts());
        return "admin/account/display_all";
    }

    @PostMapping(path = "/")
    public String createAccount(RedirectAttributes redirectAttributes,
                                          @ModelAttribute Account account) {
        try {
            accountService.createAccount(account);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось создать запись. Аккаунт с таким логином уже существует.");
        }
        return "redirect:/admin/account/";
    }

    @GetMapping(path = "/{login}")
    public String getAccount(Model model,
                                 @PathVariable("login") String login) {
        Account account = accountService.getAccount(login);
        model.addAttribute("account", account);
        return "admin/account/edit";
    }

    @PatchMapping(path = "/{login}")
    public String updateAccount(@PathVariable("login") String login,
                                    @ModelAttribute Account account,
                                    Model model) {
        accountService.updateAccount(login, account);
        return "redirect:/admin/account/";
    }

    @DeleteMapping(path = "/{login}")
    public String deleteAccount(RedirectAttributes redirectAttributes,
                                @PathVariable("login") String login) {
        try {
            accountService.deleteAccount(login);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось удалить запись. Обнаружены внешние ссылки.");
        }
        return "redirect:/admin/account/";
    }

}
