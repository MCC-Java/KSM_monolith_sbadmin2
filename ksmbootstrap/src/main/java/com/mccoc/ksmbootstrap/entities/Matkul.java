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
@Table(name = "matkul")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matkul.findAll", query = "SELECT m FROM Matkul m")
    , @NamedQuery(name = "Matkul.findByKode", query = "SELECT m FROM Matkul m WHERE m.kode = :kode")
    , @NamedQuery(name = "Matkul.findByRuang", query = "SELECT m FROM Matkul m WHERE m.ruang = :ruang")
    , @NamedQuery(name = "Matkul.findByHari", query = "SELECT m FROM Matkul m WHERE m.hari = :hari")
    , @NamedQuery(name = "Matkul.findByJam", query = "SELECT m FROM Matkul m WHERE m.jam = :jam")
    , @NamedQuery(name = "Matkul.findByNama", query = "SELECT m FROM Matkul m WHERE m.nama = :nama")
    , @NamedQuery(name = "Matkul.findBySks", query = "SELECT m FROM Matkul m WHERE m.sks = :sks")
    , @NamedQuery(name = "Matkul.findByDosen", query = "SELECT m FROM Matkul m WHERE m.dosen = :dosen")})
public class Matkul implements Serializable {

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
    @JoinTable(name = "kartustudi", joinColumns = {
        @JoinColumn(name = "kodematkul", referencedColumnName = "kode")}, inverseJoinColumns = {
        @JoinColumn(name = "nim", referencedColumnName = "nim")})
    @ManyToMany
    private Collection<Datamhs> datamhsCollection;

    public Matkul() {
    }

    public Matkul(String kode) {
        this.kode = kode;
    }

    public Matkul(String kode, String ruang, String hari, String jam, String nama, int sks, String dosen) {
        this.kode = kode;
        this.ruang = ruang;
        this.hari = hari;
        this.jam = jam;
        this.nama = nama;
        this.sks = sks;
        this.dosen = dosen;
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

    @XmlTransient
    public Collection<Datamhs> getDatamhsCollection() {
        return datamhsCollection;
    }

    public void setDatamhsCollection(Collection<Datamhs> datamhsCollection) {
        this.datamhsCollection = datamhsCollection;
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
        if (!(object instanceof Matkul)) {
            return false;
        }
        Matkul other = (Matkul) object;
        if ((this.kode == null && other.kode != null) || (this.kode != null && !this.kode.equals(other.kode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mccoc.ksmbootstrap.entities.Matkul[ kode=" + kode + " ]";
    }
    
}
