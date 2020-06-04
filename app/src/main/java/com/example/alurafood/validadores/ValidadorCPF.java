package com.example.alurafood.validadores;

import android.util.Log;

import com.example.alurafood.formatadores.FormatadorCPF;
import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

class ValidadorCPF {

	private static final String DEVE_CONTER_ONZE_DIGITOS = "CPF deve conter 11 dígitos";
	private static final String CPF_INVALIDO = "CPF Inválido";
	private static final String VALIDADOR_CPF = "ValidadorCPF";
	private static final String EXCECAO = "exceção: ";
	private final TextInputLayout inputLayout;
	private final CPFValidator validador;
	private final FormatadorCPF formatador;

	public ValidadorCPF(TextInputLayout inputLayout) {
		this.inputLayout = inputLayout;
		this.validador = new CPFValidator();
		this.formatador = new FormatadorCPF();
	}

	protected void removeErro() {
		//noinspection ConstantConditions
		String cpfFormatado = inputLayout.getEditText().getText().toString();
		String cpf = formatador.desformata(cpfFormatado);
		inputLayout.getEditText().setText(cpf);
	}

	@SuppressWarnings("ConstantConditions")
	public boolean valida() {
		removeErro();
		String cpf = inputLayout.getEditText().getText().toString();
		if(naoContemOnzeDigitos(cpf)) {
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

		formata(cpf);

		return true;
	}

	private boolean naoContemOnzeDigitos(String cpf) {
		return cpf.length() != 11;
	}

	private void formata(String cpf) {
		String cpfFormatado = formatador.formata(cpf);
		// noinspection ConstantConditions
		inputLayout.getEditText().setText(cpfFormatado);
	}
}
