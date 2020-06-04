package com.example.alurafood.validadores;

import com.example.alurafood.formatadores.FormatadorTelefone;
import com.google.android.material.textfield.TextInputLayout;

class ValidadorTelefone {

	private static final String DEVE_CONTER_DEZ_OU_ONZE_DIGITOS = "Deve conter 10 ou 11 digítos (incluido DDD)";
	private final TextInputLayout inputLayout;
	private final FormatadorTelefone formatador;

	public ValidadorTelefone(TextInputLayout inputLayout) {
		this.inputLayout = inputLayout;
		this.formatador = new FormatadorTelefone();
	}

	protected void removeErro() {
		//noinspection ConstantConditions
		String telefoneFormatado = inputLayout.getEditText().getText().toString();
		String telefone = formatador.desformata(telefoneFormatado);
		inputLayout.getEditText().setText(telefone);
	}

	public boolean valida() {
		removeErro();
		//noinspection ConstantConditions
		String telefone = inputLayout.getEditText().getText().toString();
		final int digitos = telefone.length();
		if(naoEhTelefoneComDDD(digitos)) {
			inputLayout.setError(DEVE_CONTER_DEZ_OU_ONZE_DIGITOS);
			return false;
		}

		formata(telefone);
		return true;
	}

	private boolean naoEhTelefoneComDDD(int digitos) {
		return digitos < 10 || digitos > 11;
	}

	private void formata(String telefone) {
		String telefoneFormatado = formatador.formata(telefone);
		//noinspection ConstantConditions
		inputLayout.getEditText().setText(telefoneFormatado);
	}
}
