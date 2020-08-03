/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JESSI
 */
@Entity
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c")
    , @NamedQuery(name = "Courses.findByKode", query = "SELECT c FROM Courses c WHERE c.kode = :kode")
    , @NamedQuery(name = "Courses.findByRuang", query = "SELECT c FROM Courses c WHERE c.ruang = :ruang")
    , @NamedQuery(name = "Courses.findByHari", query = "SELECT c FROM Courses c WHERE c.hari = :hari")
    , @NamedQuery(name = "Courses.findByJam", query = "SELECT c FROM Courses c WHERE c.jam = :jam")
    , @NamedQuery(name = "Courses.findByNama", query = "SELECT c FROM Courses c WHERE c.nama = :nama")
    , @NamedQuery(name = "Courses.findBySks", query = "SELECT c FROM Courses c WHERE c.sks = :sks")
    , @NamedQuery(name = "Courses.findByDosen", query = "SELECT c FROM Courses c WHERE c.dosen = :dosen")
    , @NamedQuery(name = "Courses.findByKuota", query = "SELECT c FROM Courses c WHERE c.kuota = :kuota")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "kode")
    private String kode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ruang")
    private String ruang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hari")
    private String hari;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "jam")
    private String jam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sks")
    private int sks;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dosen")
    private String dosen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kuota")
    private int kuota;
    @JoinTable(name = "studycards", joinColumns = {
        @JoinColumn(name = "kodematkul", referencedColumnName = "kode")}, inverseJoinColumns = {
        @JoinColumn(name = "nim", referencedColumnName = "nim")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Students> studentsCollection;

    public Courses() {
    }

    public Courses(String kode) {
        this.kode = kode;
    }

    public Courses(String kode, String ruang, String hari, String jam, String nama, int sks, String dosen, int kuota) {
        this.kode = kode;
        this.ruang = ruang;
        this.hari = hari;
        this.jam = jam;
        this.nama = nama;
        this.sks = sks;
        this.dosen = dosen;
        this.kuota = kuota;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    @XmlTransient
    public Collection<Students> getStudentsCollection() {
        return studentsCollection;
    }

    public void setStudentsCollection(Collection<Students> studentsCollection) {
        this.studentsCollection = studentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kode != null ? kode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.kode == null && other.kode != null) || (this.kode != null && !this.kode.equals(other.kode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mccoc.ksmbootstrap.entities.Courses[ kode=" + kode + " ]";
    }
    
}
