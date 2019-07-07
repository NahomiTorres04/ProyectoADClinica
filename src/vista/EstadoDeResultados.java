/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
@Entity
@Table(name = "estado_de_resultados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoDeResultados.findAll", query = "SELECT e FROM EstadoDeResultados e"),
    @NamedQuery(name = "EstadoDeResultados.findById", query = "SELECT e FROM EstadoDeResultados e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoDeResultados.findByServicios", query = "SELECT e FROM EstadoDeResultados e WHERE e.servicios = :servicios"),
    @NamedQuery(name = "EstadoDeResultados.findByCostos", query = "SELECT e FROM EstadoDeResultados e WHERE e.costos = :costos"),
    @NamedQuery(name = "EstadoDeResultados.findByUtilidadAntesIsr", query = "SELECT e FROM EstadoDeResultados e WHERE e.utilidadAntesIsr = :utilidadAntesIsr"),
    @NamedQuery(name = "EstadoDeResultados.findByIsr", query = "SELECT e FROM EstadoDeResultados e WHERE e.isr = :isr"),
    @NamedQuery(name = "EstadoDeResultados.findByUtilidadAntesReserva", query = "SELECT e FROM EstadoDeResultados e WHERE e.utilidadAntesReserva = :utilidadAntesReserva"),
    @NamedQuery(name = "EstadoDeResultados.findByReserva", query = "SELECT e FROM EstadoDeResultados e WHERE e.reserva = :reserva"),
    @NamedQuery(name = "EstadoDeResultados.findByUtilidadNeta", query = "SELECT e FROM EstadoDeResultados e WHERE e.utilidadNeta = :utilidadNeta"),
    @NamedQuery(name = "EstadoDeResultados.findByAnio", query = "SELECT e FROM EstadoDeResultados e WHERE e.anio = :anio")})
public class EstadoDeResultados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "servicios")
    private Double servicios;
    @Column(name = "costos")
    private Double costos;
    @Column(name = "utilidad_antes_isr")
    private Double utilidadAntesIsr;
    @Column(name = "isr")
    private Double isr;
    @Column(name = "utilidad_antes_reserva")
    private Double utilidadAntesReserva;
    @Column(name = "reserva")
    private Double reserva;
    @Column(name = "utilidad_neta")
    private Double utilidadNeta;
    @Column(name = "anio")
    private String anio;

    public EstadoDeResultados() {
    }

    public EstadoDeResultados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getServicios() {
        return servicios;
    }

    public void setServicios(Double servicios) {
        this.servicios = servicios;
    }

    public Double getCostos() {
        return costos;
    }

    public void setCostos(Double costos) {
        this.costos = costos;
    }

    public Double getUtilidadAntesIsr() {
        return utilidadAntesIsr;
    }

    public void setUtilidadAntesIsr(Double utilidadAntesIsr) {
        this.utilidadAntesIsr = utilidadAntesIsr;
    }

    public Double getIsr() {
        return isr;
    }

    public void setIsr(Double isr) {
        this.isr = isr;
    }

    public Double getUtilidadAntesReserva() {
        return utilidadAntesReserva;
    }

    public void setUtilidadAntesReserva(Double utilidadAntesReserva) {
        this.utilidadAntesReserva = utilidadAntesReserva;
    }

    public Double getReserva() {
        return reserva;
    }

    public void setReserva(Double reserva) {
        this.reserva = reserva;
    }

    public Double getUtilidadNeta() {
        return utilidadNeta;
    }

    public void setUtilidadNeta(Double utilidadNeta) {
        this.utilidadNeta = utilidadNeta;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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
        if (!(object instanceof EstadoDeResultados)) {
            return false;
        }
        EstadoDeResultados other = (EstadoDeResultados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vista.EstadoDeResultados[ id=" + id + " ]";
    }
    
}
