package br.com.projecao.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.projecao.interfaces.InterfaceDeAcessoAoBanco;

/**
 * Classe que vai controlar os usuarios cadastrados no sistema. 
 *  
 * @author javadroid
 *
 */

@Entity
@Table(name="Usuario")
public class Usuario implements InterfaceDeAcessoAoBanco{


	private static final long serialVersionUID = 5009358024016271178L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome")
	private String nome;
	@Column(name="email", unique=true)
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_cadastro;
	@Column(name="cadastro_reserva")
	private boolean cadastroReserva;
	@Column(name="confirmado")
	private boolean confirmado;
	@Column(name="cadastroPreliminar")
	private boolean cadastroPreliminar;
	@Column(name="cpf")
	private String cpf;
	
	private int nivelAcesso;
	
	@Column(name="login")
	private String login;
	@Column(name="senha")
	private String senha;	
		
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
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

	public Calendar getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Calendar data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public boolean isCadastroReserva() {
		return cadastroReserva;
	}

	public void setCadastroReserva(boolean cadastroReserva) {
		this.cadastroReserva = cadastroReserva;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isCadastroPreliminar() {
		return cadastroPreliminar;
	}

	public void setCadastroPreliminar(boolean cadastroPreliminar) {
		this.cadastroPreliminar = cadastroPreliminar;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
