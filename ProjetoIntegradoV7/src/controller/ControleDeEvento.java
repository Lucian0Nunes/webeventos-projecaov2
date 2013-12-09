package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.PieChartModel;

import entidades.Evento;
import entidades.Usuario;
import facade.EventoFacade;
import facade.UsuarioFacade;


@ManagedBean
public class ControleDeEvento {	
	
	private Evento evento;	
	private PieChartModel pieModel;
    private int contReserva;
    private int contConfirmado;
    private int contNaoConfirmados;
    private int contCadastrados;
    private int diasRestantes;
	
	public ControleDeEvento() {
		getEventoCompleto();
		carregaValores();
		diasRestantes = contandoDias();
	}
	
	
	public void getEventoCompleto(){
		if(evento == null){
			EventoFacade f = new EventoFacade();
			evento = f.busca(1l);
		}
		
	}
    public void prepararAdicionarEvento(ActionEvent event){
        evento = new Evento();
    }
	
	
	public String alterarEventoCadastrado(){
		EventoFacade f = new EventoFacade();
		if(evento.getNome() != null && evento.getData_inicio() != null && evento.getData_termino() != null){
			f.atualiza2(evento);
			
		}else{
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Campo usuário ou senha em branco.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "";
		}
		return null;
	}


	public Evento getEvento() {
		return evento;
	}
	
	public String getMainAdm(){
		return "/adm/mainadm";
	}
	
	public String getAlterarEvento(){
		return "/adm/adm1_1";
	}
	
	public String getEnviarEmailDeConfirmacao(){

		return "/adm/adm5";
	}
	
	public void confirma(ActionEvent event){
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage("E-mail de confirmação enviados a todos os usuários");
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(null, fm);
	}
	
	private void carregaValores(){
		 	
	        contReserva = 0;
	        contConfirmado = 0;
	        contNaoConfirmados = 0;
	        contCadastrados = 0;
	        UsuarioFacade f = new UsuarioFacade();
	        List<Usuario> listaUsuarios = new ArrayList<Usuario>(f.getLista());
	        
	        for (Usuario usuario : listaUsuarios) {
				if(usuario.isCadastroPreliminar()){
					contCadastrados += 1;
				}else if(usuario.isCadastroReserva()){
					contReserva += 1;
				}else if(usuario.isConfirmado()){
					contConfirmado += 1;
				}
				
			}
	        
	        
	}
	
	 public int contandoDias() {
		 	double test = computeDaysBetweenDates(new Date(),evento.getData_inicio());
		 	
		 	return (int) test;
		  }
	 
	 public double computeDaysBetweenDates(Date d1, Date d2) {
		 	
		    long diff;

		    diff = d2.getTime() - d1.getTime();
		    return ((double) diff) / (86400.0 * 1000.0);
		}
	
	 public int countDiffDay(Calendar c1, Calendar c2) {
		    int returnInt = 0;
		    while (!c1.after(c2)) {
		      c1.add(Calendar.DAY_OF_MONTH, 1);
		      returnInt++;
		    }

		    if (returnInt > 0) {
		      returnInt = returnInt - 1;
		    }

		    return (returnInt);
		  }
	
	
	private void createPieModel() {  
        pieModel = new PieChartModel();  
  
        int contVagasDisponiveis = evento.getQtd_vagas_restantes();
        int contReserva = 0;
        int contConfirmado = 0;
        int contCadastrados = 0;
        UsuarioFacade f = new UsuarioFacade();
        List<Usuario> listaUsuarios = new ArrayList<Usuario>(f.getLista());
        
        for (Usuario usuario : listaUsuarios) {
			if(usuario.isCadastroPreliminar()){
				contCadastrados += 1;
			}else if(usuario.isCadastroReserva()){
				contReserva += 1;
			}else if(usuario.isConfirmado()){
				contConfirmado += 1;
			}
			
		}
        
        pieModel.set(contVagasDisponiveis+ " Vagas Disponiveis", contVagasDisponiveis);
        pieModel.set(contCadastrados+ " Número de pessoas já Cadastrados", contCadastrados);  
        pieModel.set(contReserva+ " Número de pessoas no cadastro Reserva", contReserva);  
        pieModel.set(contConfirmado+ " Já Confirmados presença", contConfirmado);  
    } 
	
	public PieChartModel getPieModel() {
		createPieModel();
        return pieModel;  
    }
	



	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public int getContReserva() {
		return contReserva;
	}


	public void setContReserva(int contReserva) {
		this.contReserva = contReserva;
	}


	public int getContConfirmado() {
		return contConfirmado;
	}


	public void setContConfirmado(int contConfirmado) {
		this.contConfirmado = contConfirmado;
	}


	public int getContNaoConfirmados() {
		return contNaoConfirmados;
	}


	public void setContNaoConfirmados(int contNaoConfirmados) {
		this.contNaoConfirmados = contNaoConfirmados;
	}


	public int getContCadastrados() {
		return contCadastrados;
	}


	public void setContCadastrados(int contCadastrados) {
		this.contCadastrados = contCadastrados;
	}


	public int getDiasRestantes() {
		return diasRestantes;
	}


	public void setDiasRestantes(int diasRestantes) {
		this.diasRestantes = diasRestantes;
	}
	

}
