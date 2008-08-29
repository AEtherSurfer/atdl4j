/*
 * $Id: NumberFormatVerifyListener.java 2749 2006-12-05 14:02:38Z IDEAIS\tuler $
 *
 * Copyright 2006 Investtools Tecnologia em Informatica LTDA.
 * Todos os direitos reservados.
 * http://www.investtools.com.br
 */

package br.com.investtools.fix.atdl.ui.swt.widget;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * Classe que permiti a inibi��o de edi��o de um valor inv�lido em um campo
 * Text. A valida��o do texto entrado pelo usu�rio � feita por um NumberFormat.
 * Caso o NumberFormat consiga formatar o texto a edi��o � permitida, caso contr�rio
 * a edi��o � negada.
 * 
 * Uma inst�ncia dessa classe deve ser adicionada aos verifyListener's de um campo Text.
 * 
 * @author tuler
 *
 */
public class NumberFormatVerifyListener implements VerifyListener {

	protected NumberFormat formatter;
	
	protected boolean allowEmpty;

	/**
	 * 
	 * @param formatter formatador usado para validar o texto digitado pelo usu�rio.
	 * @param allowEmpty flag que indica se um campo vazio � valido.
	 */
	public NumberFormatVerifyListener(NumberFormat formatter, boolean allowEmpty) {
		this.formatter = formatter;
		this.allowEmpty = allowEmpty;
	}
	
	public void verifyText(VerifyEvent e) {
		if (e.widget instanceof Text) {
			try {
				String value = getFutureText(e);
				if (value.length() == 0 && allowEmpty) {
					return;
				}
				
				ParsePosition parsePosition = new ParsePosition(0);
				formatter.parse(value, parsePosition);
				if (parsePosition.getIndex() < value.length()) {
					// sobrou texto, tem uma parte que nao � parseavel
					throw new ParseException("Valor inv�lido", parsePosition.getIndex());
				}
			} catch (ParseException e1) {
				e.doit = false;
			}
			
			
		}
	}
	
	public String getFutureText(VerifyEvent e) {
			if (e.widget instanceof Text) {
				Text text = (Text) e.widget;
				String old = text.getText();
				String start = old.substring(0, e.start);
				String end = old.substring(e.end);
				String value = start + e.text + end;
				return value;
			}
			return "";
	}

}
