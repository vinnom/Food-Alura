package com.example.alurafood.validadores;

import android.util.Log;

import com.example.alurafood.formatadores.FormatadorCPF;
import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidadorCPF extends ValidadorPadrao {

	private static final String DEVE_CONTER_ONZE_DIGITOS = "CPF deve conter 11 dígitos";
	private static final String CPF_INVALIDO = "CPF Inválido";
	private static final String VALIDADOR_CPF = "ValidadorCPF";
	private static final String EXCECAO = "exceção: ";
	private TextInputLayout inputLayout;
	private CPFValidator validador;
	private FormatadorCPF formatador;

	public ValidadorCPF(TextInputLayout inputLayout) {
		super(inputLayout);
		this.inputLayout = inputLayout;
		this.validador = new CPFValidator();
		this.formatador = new FormatadorCPF();
	}

	@Override
	protected void removeErro() {
		String cpfFormatado = inputLayout.getEditText().getText().toString();
		String cpf = formatador.desformata(cpfFormatado);
		inputLayout.getEditText().setText(cpf);
	}

	@Override
	public boolean valida() {
		String cpf = inputLayout.getEditText().getText().toString();
		if(cpf.length() != 11) {
			inputLayout.setError(DEVE_CONTER_ONZE_DIGITOS);
			return false;
		} else {
			try {
				validador.assertValid(cpf);
			} catch(InvalidStateException e) {
				Log.e(VALIDADOR_CPF, EXCECAO + e.getInvalidMessages());
				inputLayout.setError(CPF_INVALIDO);
				return false;
			}
		}

		String cpfFormatado = formatador.formata(cpf);
		inputLayout.getEditText().setText(cpfFormatado);

		return true;
	}
}
