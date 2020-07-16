package com.flexon.bankRestfulApi.Controller;

import com.flexon.bankRestfulApi.DAO.BankAccountDAO;
import com.flexon.bankRestfulApi.Model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountDAO accountDAO = new BankAccountDAO();

    @GetMapping(path="/getBankAccount/{id}")
    public String searchAccountByID(@PathVariable String id) {
        BankAccount result = accountDAO.getAccountByID(id);
        if (result.getCustomerID() == null) {
            return "ID not found";
        }
        return result.toString();
    }

    @PostMapping(path="/addNewAccount")
    public ResponseEntity addNewAccount(@RequestBody BankAccount account) {
        try {
            boolean success = accountDAO.createAccount(account);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getLocalizedMessage() , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Created new account", HttpStatus.CREATED);
    }

    @DeleteMapping(path="deleteBankAccount/{id}")
    public ResponseEntity deleteBankAccount(@PathVariable String id) {
        if (accountDAO.deleteAccount(id))
            return new ResponseEntity<>("Successfully Deleted the Account", HttpStatus.OK);

        return new ResponseEntity<>("No this id, please retry", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path="updateBankAccount/{option}")
    public ResponseEntity updateBankAccount(@PathVariable String option, @RequestBody BankAccount account) {
        if (accountDAO.updateAccount(option, account))
            return new ResponseEntity<>("Update Successfully", HttpStatus.OK);

        return new ResponseEntity<>("Failed. Please recheck", HttpStatus.BAD_REQUEST);
    }
}
