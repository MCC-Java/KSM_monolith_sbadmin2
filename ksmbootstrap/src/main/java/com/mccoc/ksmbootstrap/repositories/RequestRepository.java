/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.repositories;

import com.mccoc.ksmbootstrap.entities.Request;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JESSI
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "SELECT koderequest,nim,kode,kodestatus,ketstudent,ketadmin FROM request WHERE kodestatus=1",
            nativeQuery = true
    )
    Collection<Request> findactive();

    @Query(value = "SELECT koderequest,nim,kode,kodestatus,ketstudent,ketadmin FROM request WHERE kodestatus=3",
            nativeQuery = true
    )
    Collection<Request> findaccept();

    @Query(value = "SELECT koderequest,nim,kode,kodestatus,ketstudent,ketadmin FROM request WHERE kodestatus=2",
            nativeQuery = true
    )
    Collection<Request> findreject();

    @Modifying
    @Query(value = "INSERT INTO request(nim,kode,kodestatus,ketstudent) VALUES (?1,?2,?3,?4);", nativeQuery = true)
    void savereq(String nim, String kode, int kodestatus, String ketstudent);

    @Modifying
    @Query(value = "SELECT koderequest,nim,kode,kodestatus,ketstudent,ketadmin FROM request WHERE kodestatus=2 AND nim=?1 ", nativeQuery = true)
    Collection<Request> findrejectmhs(String nim);

    @Modifying
    @Query(value = "SELECT koderequest,nim,kode,kodestatus,ketstudent,ketadmin FROM request WHERE kodestatus=3 AND nim=?1 ", nativeQuery = true)
    Collection<Request> findaccmhs(String nim);
    
    @Modifying
    @Query(value = "SELECT koderequest,nim,kode,kodestatus,ketstudent,ketadmin FROM request WHERE nim=?1 AND kodestatus=1", nativeQuery = true)
    Collection<Request> findactivemhs(String nim);


}
