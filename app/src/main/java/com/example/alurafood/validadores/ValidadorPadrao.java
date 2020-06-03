package com.example.alurafood.validadores;

import com.google.android.material.textfield.TextInputLayout;

abstract class ValidadorPadrao {

	private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
	private final TextInputLayout inputLayout;

	public ValidadorPadrao(TextInputLayout inputLayout) {
		this.inputLayout = inputLayout;
	}

	protected boolean validaCampoObrigatorio() {
		String texto = inputLayout.getEditText().getText().toString();
		if(texto.isEmpty()) {
			this.inputLayout.setError(CAMPO_OBRIGATORIO);
			return false;
		}
		removeErro();
		return true;
	}

	protected void removeErro() {
		this.inputLayout.setError(null);
		this.inputLayout.setErrorEnabled(false);
	}

	public abstract boolean valida();

}
