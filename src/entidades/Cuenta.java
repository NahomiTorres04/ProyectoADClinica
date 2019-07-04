/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findById", query = "SELECT c FROM Cuenta c WHERE c.id = :id"),
    @NamedQuery(name = "Cuenta.findByNombre", query = "SELECT c FROM Cuenta c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cuenta.findByActivo", query = "SELECT c FROM Cuenta c WHERE c.activo = :activo"),
    @NamedQuery(name = "Cuenta.findByCorriente", query = "SELECT c FROM Cuenta c WHERE c.corriente = :corriente")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Short activo;
    @Column(name = "corriente")
    private Short corriente;
    @OneToMany(mappedBy = "cuentaId")
    private Collection<Bien> bienCollection;

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    public Short getCorriente() {
        return corriente;
    }

    public void setCorriente(Short corriente) {
        this.corriente = corriente;
    }

    @XmlTransient
    public Collection<Bien> getBienCollection() {
        return bienCollection;
    }

    public void setBienCollection(Collection<Bien> bienCollection) {
        this.bienCollection = bienCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cuenta[ id=" + id + " ]";
    }
    
}
