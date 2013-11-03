package br.com.projecao.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.projecao.dao.UsuarioDao;
import br.com.projecao.entidades.Usuario;
import br.com.projecao.testes.SegundaFabrica;

public class Relatorio {
	

	public static void main(String[] args) throws JRException, SQLException {
		
		EntityManager manager = SegundaFabrica.getInstance();
		
		System.out.println("Gerando relatorio...");
		// lista com os nossos clientes
		List<Usuario> lista = new ArrayList<Usuario>();
		UsuarioDao dao = new UsuarioDao(manager);
		lista = dao.getLista();

	

		// compilacao do JRXML
		JasperReport report = JasperCompileManager
				.compileReport("relatorios/RelatorioClientes.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(lista));

		// exportacao do relatorio para outro formato, no caso PDF
		// JasperExportManager.exportReportToPdfFile(print,"WebContent/relatorio.xhtml");
		JasperExportManager.exportReportToHtmlFile(print,
				"WebContent/relatorio.xhtml");

		System.out.println("Relatário gerado.");
	}

}
