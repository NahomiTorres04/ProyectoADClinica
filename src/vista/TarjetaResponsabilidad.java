/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nahomi
 */
@Entity
@Table(name = "tarjeta_responsabilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaResponsabilidad.findAll", query = "SELECT t FROM TarjetaResponsabilidad t"),
    @NamedQuery(name = "TarjetaResponsabilidad.findById", query = "SELECT t FROM TarjetaResponsabilidad t WHERE t.id = :id"),
    @NamedQuery(name = "TarjetaResponsabilidad.findByFecha", query = "SELECT t FROM TarjetaResponsabilidad t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TarjetaResponsabilidad.findByFungible", query = "SELECT t FROM TarjetaResponsabilidad t WHERE t.fungible = :fungible")})
public class TarjetaResponsabilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fungible")
    private Short fungible;
    @OneToMany(mappedBy = "tarjetaResponsabilidadId")
    private Collection<Contenido> contenidoCollection;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;

    public TarjetaResponsabilidad() {
    }

    public TarjetaResponsabilidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getFungible() {
        return fungible;
    }

    public void setFungible(Short fungible) {
        this.fungible = fungible;
    }

    @XmlTransient
    public Collection<Contenido> getContenidoCollection() {
        return contenidoCollection;
    }

    public void setContenidoCollection(Collection<Contenido> contenidoCollection) {
        this.contenidoCollection = contenidoCollection;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
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
        if (!(object instanceof TarjetaResponsabilidad)) {
            return false;
        }
        TarjetaResponsabilidad other = (TarjetaResponsabilidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vista.TarjetaResponsabilidad[ id=" + id + " ]";
    }
    
}
