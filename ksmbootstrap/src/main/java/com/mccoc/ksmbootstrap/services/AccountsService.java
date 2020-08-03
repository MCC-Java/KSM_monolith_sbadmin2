/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.services;

import com.mccoc.ksmbootstrap.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
public class AccountsService {
    
    @Autowired
    AccountsRepository account;
    
    public String getpass(String username){
        return account.findById(username).get().getPassword();
    }
    
    public String getrole(String username){
        return account.findById(username).get().getRole();
    }
    
    public boolean checkusername(String username){
        return account.existsById(username);
    }
    
    
}
