/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.repositories;

import com.mccoc.ksmbootstrap.entities.Courses;
import com.mccoc.ksmbootstrap.entities.Students;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JESSI
 */
@Repository
public interface CoursesRepository extends JpaRepository<Courses , String> {

    @Modifying
    @Query(value = "SELECT * FROM Courses  INNER JOIN studycards ON Courses .kode=studycards.kodematkul  WHERE studycards.nim = ?1", nativeQuery = true)
    List<Courses> findByNIM(String nim);
    
    @Query(value = "SELECT (*) FROM Courses WHERE kuota>", nativeQuery = true)
    public List<Courses> showtostudent();
    
    @Modifying
    @Query(value = "DELETE FROM studycards WHERE studycards.kodematkul =:kode AND studycards.nim=:nim", nativeQuery = true)
    void deleteksm(@Param("kode") String kode, @Param("nim") String nim);
    
    @Modifying
    @Query(value = "DELETE FROM studycards WHERE studycards.kodematkul  =:kode", nativeQuery = true)
    void deleteksmbykode(@Param("kode") String kode);
    
    @Modifying
    @Query(value = "INSERT INTO studycards VALUES (?1,?2)", nativeQuery=true)
    void savetoksm(String nim, String kode);
   
    @Query(value="SELECT COUNT(*) FROM studycards where studycards.kodematkul  =?1 AND studycards.nim=?2", nativeQuery= true)
    int count(String kode, String nim);
    
    @Query(value = "SELECT SUM(courses.sks) FROM studycards INNER JOIN courses ON (studycards.kodematkul=courses.kode) WHERE studycards.nim=?1", nativeQuery=true)
    int sksnow(String nim);
    
}
