package br.com.projecao.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDeClientes {
	
	public static void main(String[] args) throws JRException, SQLException {

		System.out.println("Gerando relatorio...");
		// lista com os nossos clientes
		List<Cliente> lista = new ArrayList<Cliente>();

		Cliente c1 = new Cliente();
		c1.setNome("Alexandre Macedo");
		c1.setEmail("alexbmac@gmail.com");
		c1.setTelefone("9999-9999");

		Cliente c2 = new Cliente();
		c2.setNome("Rafael Cosentino");
		c2.setEmail("cosen@gmail.com");
		c2.setTelefone("8888-8888");

		Cliente c3 = new Cliente();
		c3.setNome("Daniel Machado");
		c3.setEmail("daniel@gmail.com");
		c3.setTelefone("7777-7777");

		lista.add(c1);
		lista.add(c2);
		lista.add(c3);

		// compilacao do JRXML
		JasperReport report = JasperCompileManager.compileReport("relatorios/RelatorioClientes.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,	new JRBeanCollectionDataSource(lista));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print,"relatorios/RelatorioClientes.pdf");

		System.out.println("Relatário gerado.");
	}
}
