/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.services;

import com.mccoc.ksmbootstrap.entities.Datamhs;
import com.mccoc.ksmbootstrap.repositories.DatamhsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JESSI
 */
@Service
public class DatamhsService {
    
    @Autowired
    DatamhsRepository mhs;
    
    public String checkpass(String nim){
        return mhs.findById(nim).get().getPassword();
    }
    
    public boolean checknim(String nim){
        return mhs.existsById(nim);
    }
    
    public String checkname(String nim){
        return mhs.findById(nim).get().getNama();
    }
    
    public Datamhs getbynim(String nim){
        return mhs.findById(nim).get();
    }
    
}
