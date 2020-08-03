/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.services;

import com.mccoc.ksmbootstrap.entities.Courses;
import com.mccoc.ksmbootstrap.entities.Students;
import com.mccoc.ksmbootstrap.repositories.CoursesRepository;
import java.util.Collection;
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
public class CoursesService {

    @Autowired
    CoursesRepository Courses;

    public List<Courses> getAll() {
        return Courses.findAll();
    }

     public List<Courses> showtostudent() {
        return Courses.showtostudent();
    }
    public List<Courses> getbyNIM(String nim) {
        return Courses.findByNIM(nim);
    }

    public void deleteksm(String kodeCourses, String nim) {
        Courses.deleteksm(kodeCourses, nim);
    }

    public void savetoksm(String nim, String kodeCourses) {
        Courses.savetoksm(nim, kodeCourses);
    }


    public boolean checkksm(String kodeCourses, String nim) {
        if (Courses.count(kodeCourses, nim) == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public void deleteCourses(String kode){
        Courses.deleteById(kode);
        Courses.deleteksmbykode(kode);
    }
    
    public void saveCourses(Courses Coursesl){
       Courses.save(Coursesl);
    }
    
    public int sksnow(String nim){
        return Courses.sksnow(nim);
    }
         

}
