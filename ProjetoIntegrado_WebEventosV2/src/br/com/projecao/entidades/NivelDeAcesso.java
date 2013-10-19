package br.com.projecao.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.projecao.interfaces.InterfaceDeAcessoAoBanco;

/**
 * Classe para controlar os niveis de acesso do sistema, 
 * inicialmente o escopo está fechado entre usuarios,administrador,responsaveis por Oficina
 * 
 * @author javadroid
 *
 */
@Entity
@Table(name="nivel_acesso")
public class NivelDeAcesso implements InterfaceDeAcessoAoBanco{
	
	private static final long serialVersionUID = -7246278067243376101L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nivel_acesso")
	private String nivel;
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	

}
