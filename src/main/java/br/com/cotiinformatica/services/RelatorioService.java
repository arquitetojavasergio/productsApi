package br.com.cotiinformatica.services;

import java.time.Instant;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.repositories.HistoricoProdutosRepository;

@Service
public class RelatorioService {

	@Autowired HistoricoProdutosRepository historicoProdutosRepository;

	@Value("${mail.host}")
	String mailHost;
	
	@Value("${mail.port}")
	String mailPort;
	
	@Value("${mail.from}")
	String mailFrom;
	
	@Value("${mail.to}")
	String mailTo;

	public void enviarRelatorio() {

		var produtos = historicoProdutosRepository.findAll();

		var texto = "Relatório de histórico de produtos:\n\n";
		for (var item : produtos) {
			texto += "Id: " + item.getId() + "\n";
			texto += "Data e hora: " + item.getDataHoraOperacao() + "\n";
			texto += "Operação: " + item.getTipoOperacao() + "\n";
			texto += "Produto: " + item.getNomeProduto() + "\n";
			texto += "Preço unitário: " + item.getPrecoProduto() + "\n";
			texto += "Quantidade: " + item.getQuantidadeProduto() + "\n";
			texto += "\n";
		}

		var properties = new Properties();
		properties.put("mail.smtp.host", mailHost);
		properties.put("mail.smtp.port", mailPort);

		var session = Session.getInstance(properties);

		try {

			var mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(mailFrom));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			mimeMessage.setSubject("Relatório de histórico de produtos gerado em: " + Instant.now());
			mimeMessage.setText(texto);

			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
