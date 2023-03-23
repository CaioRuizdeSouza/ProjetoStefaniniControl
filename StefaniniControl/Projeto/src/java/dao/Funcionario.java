package dao;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stefanini
 */
@Entity
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "funcionario.findAll", query = "SELECT u FROM Funcionario u"),
    @NamedQuery(name = "funcionario.findById", query = "SELECT u FROM Funcionario u WHERE u.id = :idfuncionario"),
    @NamedQuery(name = "funcionario.findByCodigoFuncionário", query = "SELECT u FROM Funcionario u WHERE u.codigofuncionario = :codigofuncionario"),
    @NamedQuery(name = "funcionario.findByName", query = "SELECT u FROM Funcionario u WHERE u.nome = :nome"),
    @NamedQuery(name = "funcionario.findByEmail", query = "SELECT u FROM Funcionario u WHERE u.email = :email"),
    @NamedQuery(name = "funcionario.findByCpf", query = "SELECT u FROM Funcionario u WHERE u.cpf = :cpf"),
    @NamedQuery(name = "funcionario.findByArea", query = "SELECT u FROM Funcionario u WHERE u.area = :area"),
    @NamedQuery(name = "funcionario.findByModeloEquipamento", query = "SELECT u FROM Funcionario u WHERE u.modeloequipamento = :modeloequipamento"),
    @NamedQuery(name = "funcionario.findByCargo", query = "SELECT u FROM Funcionario u WHERE u.cargo = :cargo"),
    @NamedQuery(name = "funcionario.findByCodigoEquipamento", query = "SELECT u FROM Funcionario u WHERE u.codigoequipamento = :codigoequipamento")})
    
public class Funcionario implements Serializable{
     private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfuncionario")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigofuncionario")
    private Integer codigofuncionario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "area")
    private String area;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modeloequipamento")
    private String modeloequipamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoequipamento")
    private String codigoequipamento;

    public Funcionario() {
    }

    public Funcionario(Long id) {
        this.id = id;
    }

    public Funcionario(Long id, Integer codigofuncionario, String nome, String email, String cpf, String area, String modeloequipamento, String cargo, String codigoequipamento) {
        this.id = id;
        this.codigofuncionario = codigofuncionario;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.area = area;
        this.modeloequipamento = modeloequipamento;
        this.cargo = cargo;
        this.codigoequipamento = codigoequipamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getCodigoFuncionario() {
        return codigofuncionario;
    }

    public void setCodigoFuncionario(Integer codigofuncionario) {
        this.codigofuncionario = codigofuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    public String getModeloEquipamento() {
        return modeloequipamento;
    }

    public void setModeloEquipamento(String modeloequipamento) {
        this.modeloequipamento = modeloequipamento;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getCodigoEquipamento() {
        return codigoequipamento;
    }

    public void setCodigoEquipamento(String codigoequipamento) {
        this.codigoequipamento = codigoequipamento;
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Funcionario[ id=" + id + " ]";
    }
    
}
