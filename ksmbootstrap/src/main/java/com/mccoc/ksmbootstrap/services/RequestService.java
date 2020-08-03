/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.services;

import com.mccoc.ksmbootstrap.entities.Request;
import com.mccoc.ksmbootstrap.repositories.CoursesRepository;
import com.mccoc.ksmbootstrap.repositories.RequestRepository;
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
public class RequestService {
    
    @Autowired
    RequestRepository request;
    
    
    @Autowired
    CoursesRepository Courses;
    
    public List<Request> getactive(){
        return (List<Request>) request.findactive();
    }
    
     public List<Request> getaccept(){
        return (List<Request>) request.findaccept();
    }
    
      public List<Request> getreject(){
        return (List<Request>) request.findreject();
    }
      
    public void accadmin(String kode, String ket){
        request.findById(Integer.parseInt(kode)).get().setKetadmin(ket);
        request.findById(Integer.parseInt(kode)).get().setKodestatus("3");
        Courses.deleteksm(request.findById(Integer.parseInt(kode)).get().getKode(), request.findById(Integer.parseInt(kode)).get().getNim());
    }
    
    public void deladmin(String kode, String ket){
        request.findById(Integer.parseInt(kode)).get().setKetadmin(ket);
        request.findById(Integer.parseInt(kode)).get().setKodestatus("2");
    }
    
    
    public void savereq(String nim, String kode, String ketstudent){
        request.savereq(nim, kode, 1, ketstudent);
    }
    
    public List<Request> findactivemhs(String nim){
        return (List<Request>) request.findactivemhs(nim);
    }
    
    public List<Request> findaccmhs(String nim){
        return (List<Request>) request.findaccmhs(nim);
    }
    
    public List<Request> findrejectmhs(String nim){
        return (List<Request>) request.findrejectmhs(nim);
    }
    
}
