package entidades;

import interfaces.InterfaceDeAcessoAoBanco;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Log")
public class Logs implements InterfaceDeAcessoAoBanco {

	private static final long serialVersionUID = 2928936100361493216L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="log")
	private String log;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_operacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public Calendar getData_operacao() {
		return data_operacao;
	}
	public void setData_operacao(Calendar data_operacao) {
		this.data_operacao = data_operacao;
	}
	
	

}
