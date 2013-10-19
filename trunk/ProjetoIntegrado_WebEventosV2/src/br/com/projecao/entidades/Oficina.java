package br.com.projecao.entidades;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.projecao.interfaces.InterfaceDeAcessoAoBanco;


/**
 * Classe que retem as informações sobre todas as oficinas, caso existam no Evento
 * 
 * @author javadroid
 *
 */

@Entity
@Table(name="Oficinas")
public class Oficina implements InterfaceDeAcessoAoBanco{
	
	private static final long serialVersionUID = 1739528996033073012L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horario = new GregorianCalendar();

	@Column(name="descricao")
	private String descricao; // descricao da oficina
	
	@OneToMany
	@JoinColumn(name="id_usuario")
	private List<Usuario> responsavel; // responsavel pelo evento
	
	
	public Calendar getHorario() {
		return horario;
	}
	public void setHorario(Calendar horario) {
		this.horario = horario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(List<Usuario> responsavel) {
		this.responsavel = responsavel;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
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
		Oficina other = (Oficina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
