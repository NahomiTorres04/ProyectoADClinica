/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
@Entity
@Table(name = "bien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bien.findAll", query = "SELECT b FROM Bien b"),
    @NamedQuery(name = "Bien.findById", query = "SELECT b FROM Bien b WHERE b.id = :id"),
    @NamedQuery(name = "Bien.findByCodigo", query = "SELECT b FROM Bien b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "Bien.findByDescripcion", query = "SELECT b FROM Bien b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "Bien.findByCantidad", query = "SELECT b FROM Bien b WHERE b.cantidad = :cantidad"),
    @NamedQuery(name = "Bien.findByPrecioUnitario", query = "SELECT b FROM Bien b WHERE b.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "Bien.findByPrecioTotal", query = "SELECT b FROM Bien b WHERE b.precioTotal = :precioTotal"),
    @NamedQuery(name = "Bien.findByEstado", query = "SELECT b FROM Bien b WHERE b.estado = :estado"),
    @NamedQuery(name = "Bien.findByDonado", query = "SELECT b FROM Bien b WHERE b.donado = :donado"),
    @NamedQuery(name = "Bien.findByFungible", query = "SELECT b FROM Bien b WHERE b.fungible = :fungible"),
    @NamedQuery(name = "Bien.findByFecha", query = "SELECT b FROM Bien b WHERE b.fecha = :fecha")})
public class Bien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "precio_total")
    private Double precioTotal;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "donado")
    private Short donado;
    @Column(name = "fungible")
    private Short fungible;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    @ManyToOne
    private Cuenta cuentaId;
    @JoinColumn(name = "contenido_id", referencedColumnName = "id")
    @ManyToOne
    private Contenido contenidoId;
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Departamento departamentoId;

    public Bien() {
    }

    public Bien(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Short getDonado() {
        return donado;
    }

    public void setDonado(Short donado) {
        this.donado = donado;
    }

    public Short getFungible() {
        return fungible;
    }

    public void setFungible(Short fungible) {
        this.fungible = fungible;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Cuenta cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Contenido getContenidoId() {
        return contenidoId;
    }

    public void setContenidoId(Contenido contenidoId) {
        this.contenidoId = contenidoId;
    }

    public Departamento getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Departamento departamentoId) {
        this.departamentoId = departamentoId;
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
        if (!(object instanceof Bien)) {
            return false;
        }
        Bien other = (Bien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bien[ id=" + id + " ]";
    }
    
}
