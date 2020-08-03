/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.repositories;

import com.mccoc.ksmbootstrap.entities.Students;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JESSI
 */
@Repository
public interface StudentsRepository extends JpaRepository< Students ,String> {
    @Modifying
    @Query(value = "SELECT students.nim, students.nama, students.fakultas, students.progdi,students.bebansks FROM students INNER JOIN studycards ON students.nim=studycards.nim  WHERE studycards.kodematkul = :kode", nativeQuery=true)
    List<Students> lihatpeserta(@Param("kode") String kode);
    
    
    
}
