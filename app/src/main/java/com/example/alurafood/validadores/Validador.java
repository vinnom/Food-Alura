package com.example.alurafood.validadores;

import com.google.android.material.textfield.TextInputLayout;

import static com.example.alurafood.R.id.formulario_cadastro_cpf;

public class Validador extends ValidadorPadrao {

	private final TextInputLayout inputLayout;
	private int inputLayoutId;

	public Validador(TextInputLayout inputLayout) {
		super(inputLayout);
		this.inputLayout = inputLayout;
		this.inputLayoutId = inputLayout.getId();
	}

	@Override
	public boolean valida() {
		String texto = inputLayout.getEditText().getText().toString();
		if(texto.isEmpty()) return super.validaCampoObrigatorio();

		switch(inputLayoutId) {
			case formulario_cadastro_cpf:
				return new ValidadorCPF(inputLayout).valida();
			default:
				return true;
		}
	}

	public void limpaCampo() {
		super.removeErro();

		switch(inputLayoutId) {
			case formulario_cadastro_cpf:
				new ValidadorCPF(inputLayout).removeErro();
				break;
			default:
				break;
		}

	}
}
