package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import suporte.JavaMailApp;
import facade.UsuarioFacade;

@ManagedBean
public class Navegador {
	
	private String nome;
	private String senha;
	private String sugestao;
	private int sessaovalida;
	
	
	@SuppressWarnings("serial")
	private List<Distribuidor> items = new ArrayList<Distribuidor>(){
		{
			add(new Distribuidor("Inicio",  "/paginas/pagina1"));
			add(new Distribuidor("Sobre",   "/paginas/pagina2"));
			add(new Distribuidor("Serviço", "/paginas/pagina3"));
			add(new Distribuidor("Localização", "/paginas/pagina4"));
			add(new Distribuidor("Suporte", "/paginas/pagina5"));

		};
	};
	
	
	@SuppressWarnings("serial")
	private List<Distribuidor> itemsAdm = new ArrayList<Distribuidor>(){
		{
			add(new Distribuidor("Resumo",  "/adm/adm1"));
			add(new Distribuidor("Agenda",   "/adm/adm2"));
			add(new Distribuidor("Usuarios", "/adm/adm3"));
			add(new Distribuidor("Relatorios", "/adm/adm4"));
			add(new Distribuidor("Suporte", "/adm/adm5"));

		};
	};

	public List<Distribuidor> getItems() {
		return items;
	}
	
	
	public String getCadastro(){
		return "/paginas/pagina6";
	}
	
	public String getLogin(){
		return "/paginas/pagina7";
	}
	
	public void enviaSugestao(ActionEvent event){
		JavaMailApp mail = new JavaMailApp();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String usuario = (String) session.getAttribute("usuario");
		
		mail.mandaSugestao(sugestao, usuario);
		sugestao = new String();
	}
	
	public void limparCamposSugestao(ActionEvent event){
		sugestao = new String();
	}
	
	
	
	public String autentica() {

		FacesContext fc = FacesContext.getCurrentInstance();
		String[] retorno;
		if (nome.equals("") || senha.equals("")) {
			FacesMessage fm = new FacesMessage("Campo usuário ou senha em branco.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/paginas/pagina7";
		} else {
			UsuarioFacade f = new UsuarioFacade();
			retorno = f.validaLogin(nome, senha);
		}
		if (retorno[1].equals("1")) {

			if (retorno.length > 0) {
				ExternalContext ec = fc.getExternalContext();
				HttpSession session = (HttpSession) ec.getSession(false);
				session.setAttribute("usuario", retorno[0]);
				return "/main";

			} else {
				FacesMessage fm = new FacesMessage("Credenciais inválidas.");
				fm.setSeverity(FacesMessage.SEVERITY_ERROR);
				fc.addMessage(null, fm);
				setSessaovalida(0);
				return "/paginas/pagina7";
			}
		}else{
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", retorno[0]);
			return "/adm/mainadm";
			
		}
	}
	
	
	public String getValidaLogin(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		String retorno;
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		retorno = (String) session.getAttribute("usuario");
		
		
		if(retorno == null){
			return null;
		}else{
			return "valido";
		}		
	}
	
	
	public String registraSaida(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		
		return "/main";
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getSugestao() {
		return sugestao;
	}


	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}


	public int getSessaovalida() {
		return sessaovalida;
	}


	public void setSessaovalida(int sessaovalida) {
		this.sessaovalida = sessaovalida;
	}


	public List<Distribuidor> getItemsAdm() {
		return itemsAdm;
	}

}
