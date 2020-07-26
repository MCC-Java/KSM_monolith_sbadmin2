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
@Table(name = "datamhs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datamhs.findAll", query = "SELECT d FROM Datamhs d")
    , @NamedQuery(name = "Datamhs.findByNim", query = "SELECT d FROM Datamhs d WHERE d.nim = :nim")
    , @NamedQuery(name = "Datamhs.findByNama", query = "SELECT d FROM Datamhs d WHERE d.nama = :nama")
    , @NamedQuery(name = "Datamhs.findByPassword", query = "SELECT d FROM Datamhs d WHERE d.password = :password")
    , @NamedQuery(name = "Datamhs.findByFakultas", query = "SELECT d FROM Datamhs d WHERE d.fakultas = :fakultas")
    , @NamedQuery(name = "Datamhs.findByProgdi", query = "SELECT d FROM Datamhs d WHERE d.progdi = :progdi")})
public class Datamhs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nim")
    private String nim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fakultas")
    private String fakultas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "progdi")
    private String progdi;
    @ManyToMany(mappedBy = "datamhsCollection")
    private Collection<Matkul> matkulCollection;

    public Datamhs() {
    }

    public Datamhs(String nim) {
        this.nim = nim;
    }

    public Datamhs(String nim, String nama, String password, String fakultas, String progdi) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
        this.fakultas = fakultas;
        this.progdi = progdi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getProgdi() {
        return progdi;
    }

    public void setProgdi(String progdi) {
        this.progdi = progdi;
    }

    @XmlTransient
    public Collection<Matkul> getMatkulCollection() {
        return matkulCollection;
    }

    public void setMatkulCollection(Collection<Matkul> matkulCollection) {
        this.matkulCollection = matkulCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nim != null ? nim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datamhs)) {
            return false;
        }
        Datamhs other = (Datamhs) object;
        if ((this.nim == null && other.nim != null) || (this.nim != null && !this.nim.equals(other.nim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mccoc.ksmbootstrap.entities.Datamhs[ nim=" + nim + " ]";
    }
    
}
