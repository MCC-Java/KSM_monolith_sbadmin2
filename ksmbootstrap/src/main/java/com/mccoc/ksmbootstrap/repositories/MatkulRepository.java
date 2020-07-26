/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.repositories;

import com.mccoc.ksmbootstrap.entities.Matkul;
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
public interface MatkulRepository extends JpaRepository<Matkul, String> {

    @Modifying
    @Query(value = "SELECT * FROM matkul INNER JOIN kartustudi ON matkul.kode=kartustudi.kodematkul WHERE kartustudi.nim = ?1", nativeQuery = true)
    List<Matkul> findByNIM(String nim);
    
    @Modifying
    @Query(value = "DELETE FROM kartustudi WHERE kartustudi.kodematkul =:kode AND kartustudi.nim=:nim", nativeQuery = true)
    void deleteksm(@Param("kode") String kode, @Param("nim") String nim);
    
    @Modifying
    @Query(value = "DELETE FROM kartustudi WHERE kartustudi.kodematkul =:kode", nativeQuery = true)
    void deleteksmbykode(@Param("kode") String kode);
    
    @Modifying
    @Query(value = "INSERT INTO kartustudi VALUES (?1,?2)", nativeQuery=true)
    void savetoksm(String nim, String kode);
   
    @Query(value="SELECT COUNT(*) FROM kartustudi where kartustudi.kodematkul =?1 AND kartustudi.nim=?2", nativeQuery= true)
    int count(String kode, String nim);
}
