/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neck
 */
@Entity
@Table(name = "token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
    , @NamedQuery(name = "Token.findByIdtoken", query = "SELECT t FROM Token t WHERE t.idtoken = :idtoken")
    , @NamedQuery(name = "Token.findByFechaExp", query = "SELECT t FROM Token t WHERE t.fechaExp = :fechaExp")
    , @NamedQuery(name = "Token.findByToken", query = "SELECT t FROM Token t WHERE t.token = :token")})
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtoken")
    private Integer idtoken;
    @Column(name = "fechaExp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExp;
    @Size(max = 20)
    @Column(name = "token")
    private String token;
    @JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuarioCodigo;

    public Token() {
    }

    public Token(Integer idtoken) {
        this.idtoken = idtoken;
    }

    public Integer getIdtoken() {
        return idtoken;
    }

    public void setIdtoken(Integer idtoken) {
        this.idtoken = idtoken;
    }

    public Date getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(Date fechaExp) {
        this.fechaExp = fechaExp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public void setUsuarioCodigo(Usuario usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtoken != null ? idtoken.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.idtoken == null && other.idtoken != null) || (this.idtoken != null && !this.idtoken.equals(other.idtoken))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.ufps.alexandria.entities.Token[ idtoken=" + idtoken + " ]";
    }

}
