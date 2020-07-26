/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.services;

import com.mccoc.ksmbootstrap.entities.Matkul;
import com.mccoc.ksmbootstrap.repositories.MatkulRepository;
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
public class MatkulService {

    @Autowired
    MatkulRepository matkul;

    public List<Matkul> getAll() {
        return matkul.findAll();
    }

    public List<Matkul> getbyNIM(String nim) {
        return matkul.findByNIM(nim);
    }

    public void deleteksm(String kodematkul, String nim) {
        matkul.deleteksm(kodematkul, nim);
    }

    public void savetoksm(String kodematkul, String nim) {
        matkul.savetoksm(nim, kodematkul);
    }

    public boolean checkkode(String kodematkul) {
        return matkul.existsById(kodematkul);
    }

    public boolean checkksm(String kodematkul, String nim) {
        if (matkul.count(kodematkul, nim) == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public void deletematkul(String kode){
        matkul.deleteById(kode);
        matkul.deleteksmbykode(kode);
    }
    
    public void savematkul(Matkul matkull){
       matkul.save(matkull);
    }

}
