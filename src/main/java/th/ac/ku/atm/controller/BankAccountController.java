package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Transaction;
import th.ac.ku.atm.service.BankAccountService;


@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        accountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable int id,
                          @ModelAttribute Transaction transaction,
                          Model model) {
        accountService.deposit(id,transaction);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable int id,
                          @ModelAttribute Transaction transaction,
                          Model model) {

        accountService.withdraw(id,transaction);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id) {
        accountService.deleteBankAccount(id);
        return "redirect:/bankaccount";
    }


}

