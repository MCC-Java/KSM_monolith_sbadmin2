/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "students")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s")
    , @NamedQuery(name = "Students.findByNim", query = "SELECT s FROM Students s WHERE s.nim = :nim")
    , @NamedQuery(name = "Students.findByNama", query = "SELECT s FROM Students s WHERE s.nama = :nama")
    , @NamedQuery(name = "Students.findByFakultas", query = "SELECT s FROM Students s WHERE s.fakultas = :fakultas")
    , @NamedQuery(name = "Students.findByProgdi", query = "SELECT s FROM Students s WHERE s.progdi = :progdi")
    , @NamedQuery(name = "Students.findByBebansks", query = "SELECT s FROM Students s WHERE s.bebansks = :bebansks")})
public class Students implements Serializable {

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
    @Column(name = "fakultas")
    private String fakultas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "progdi")
    private String progdi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bebansks")
    private String bebansks;
    @ManyToMany(mappedBy = "studentsCollection", fetch = FetchType.LAZY)
    private Collection<Courses> coursesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nim", fetch = FetchType.LAZY)
    private Collection<Request> requestCollection;
    @JoinColumn(name = "nim", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Accounts accounts;

    public Students() {
    }

    public Students(String nim) {
        this.nim = nim;
    }

    public Students(String nim, String nama, String fakultas, String progdi, String bebansks) {
        this.nim = nim;
        this.nama = nama;
        this.fakultas = fakultas;
        this.progdi = progdi;
        this.bebansks = bebansks;
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

    public String getBebansks() {
        return bebansks;
    }

    public void setBebansks(String bebansks) {
        this.bebansks = bebansks;
    }

    @XmlTransient
    public Collection<Courses> getCoursesCollection() {
        return coursesCollection;
    }

    public void setCoursesCollection(Collection<Courses> coursesCollection) {
        this.coursesCollection = coursesCollection;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
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
        if (!(object instanceof Students)) {
            return false;
        }
        Students other = (Students) object;
        if ((this.nim == null && other.nim != null) || (this.nim != null && !this.nim.equals(other.nim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mccoc.ksmbootstrap.entities.Students[ nim=" + nim + " ]";
    }
    
}
