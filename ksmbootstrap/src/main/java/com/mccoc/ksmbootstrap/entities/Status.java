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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")
    , @NamedQuery(name = "Status.findByKodeStatus", query = "SELECT s FROM Status s WHERE s.kodeStatus = :kodeStatus")
    , @NamedQuery(name = "Status.findByStatus", query = "SELECT s FROM Status s WHERE s.status = :status")})
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "kode_status")
    private Integer kodeStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kodestatus", fetch = FetchType.LAZY)
    private Collection<Request> requestCollection;

    public Status() {
    }

    public Status(Integer kodeStatus) {
        this.kodeStatus = kodeStatus;
    }

    public Status(Integer kodeStatus, String status) {
        this.kodeStatus = kodeStatus;
        this.status = status;
    }

    public Integer getKodeStatus() {
        return kodeStatus;
    }

    public void setKodeStatus(Integer kodeStatus) {
        this.kodeStatus = kodeStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeStatus != null ? kodeStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.kodeStatus == null && other.kodeStatus != null) || (this.kodeStatus != null && !this.kodeStatus.equals(other.kodeStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mccoc.ksmbootstrap.entities.Status[ kodeStatus=" + kodeStatus + " ]";
    }
    
}
