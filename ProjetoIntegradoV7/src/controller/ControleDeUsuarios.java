package controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import entidades.Usuario;
import facade.UsuarioFacade;

@ManagedBean(name = "controleUsuarios")
public class ControleDeUsuarios {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> lista;

	public String adiciona() {
		UsuarioFacade dao = new UsuarioFacade();
		boolean ok = dao.adiciona(this.usuario);
		FacesContext fc = FacesContext.getCurrentInstance();
		if(ok){
			this.usuario = new Usuario();
			FacesMessage fm = new FacesMessage("Cadastro efetuado com sucesso, verifique sua caixa de e-mail.");
			fm.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(null, fm);
			return "/main";
		}else{
			FacesMessage fm = new FacesMessage("Erro de Usuário/Senha, valide seus dados.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/paginas/pagina6";
		}
	}
	
    public void prepararAdicionarParticipante(ActionEvent event){
        usuario = new Usuario();
    }

	public void preparaAlteracao() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		UsuarioFacade dao = new UsuarioFacade();
		this.usuario = dao.busca(id);
	}

	public void remove() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long id = Long.parseLong(params.get("id"));
		UsuarioFacade dao = new UsuarioFacade();
		dao.remove(id);
		this.lista = null;

	}

	public List<Usuario> getUsuarios() {
		if (this.lista == null) {
			UsuarioFacade dao = new UsuarioFacade();
			this.lista = dao.getLista();
		}
		return this.lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
