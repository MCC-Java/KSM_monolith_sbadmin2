/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JESSI
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findByKoderequest", query = "SELECT r FROM Request r WHERE r.koderequest = :koderequest")
    , @NamedQuery(name = "Request.findByKode", query = "SELECT r FROM Request r WHERE r.kode = :kode")
    , @NamedQuery(name = "Request.findByKetstudent", query = "SELECT r FROM Request r WHERE r.ketstudent = :ketstudent")
    , @NamedQuery(name = "Request.findByKetadmin", query = "SELECT r FROM Request r WHERE r.ketadmin = :ketadmin")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "koderequest")
    private Integer koderequest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "kode")
    private String kode;
    @Size(max = 500)
    @Column(name = "ketstudent")
    private String ketstudent;
    @Size(max = 500)
    @Column(name = "ketadmin")
    private String ketadmin;
    @Column(name = "kodestatus")
    private String kodestatus;
    @Column(name = "nim")
    private String nim;

    public Request() {
    }

    public Request(Integer koderequest) {
        this.koderequest = koderequest;
    }

    public Request(Integer koderequest, String kode) {
        this.koderequest = koderequest;
        this.kode = kode;
    }

    public Request(Integer kodereq, String nim, String kode, String kodestatus, String ketstudent) {
        this.koderequest= kodereq;
        this.nim =nim;
        this.kode = kode;
        this.kodestatus= kodestatus;
        this.ketstudent=ketstudent;
    }

    public Integer getKoderequest() {
        return koderequest;
    }

    public void setKoderequest(Integer koderequest) {
        this.koderequest = koderequest;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKetstudent() {
        return ketstudent;
    }

    public void setKetstudent(String ketstudent) {
        this.ketstudent = ketstudent;
    }

    public String getKetadmin() {
        return ketadmin;
    }

    public void setKetadmin(String ketadmin) {
        this.ketadmin = ketadmin;
    }

    public String getKodestatus() {
        return kodestatus;
    }

    public void setKodestatus(String kodestatus) {
        this.kodestatus = kodestatus;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (koderequest != null ? koderequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.koderequest == null && other.koderequest != null) || (this.koderequest != null && !this.koderequest.equals(other.koderequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + koderequest + "";
    }

}
