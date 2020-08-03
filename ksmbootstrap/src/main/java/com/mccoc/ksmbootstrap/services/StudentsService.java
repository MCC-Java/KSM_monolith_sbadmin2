/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.services;

import com.mccoc.ksmbootstrap.entities.Students ;
import com.mccoc.ksmbootstrap.repositories.StudentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JESSI
 */
@Service
@Transactional
public class StudentsService {
    
    @Autowired
    StudentsRepository mhs;
   
    public String checkname(String nim){
        return mhs.findById(nim).get().getNama();
    }
    
    public Students getbynim(String nim){
        return mhs.findById(nim).get();
    }
    
    public List<Students> lihatpeserta(String kode){
        return mhs.lihatpeserta(kode);
    }
    
      
    
}
