package com.example.alurafood.ui.atividades;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alurafood.R;
import com.example.alurafood.validadores.Validador;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.alurafood.R.id.formulario_cadastro_cpf;
import static com.example.alurafood.R.id.formulario_cadastro_email;
import static com.example.alurafood.R.id.formulario_cadastro_nome_completo;
import static com.example.alurafood.R.id.formulario_cadastro_senha;
import static com.example.alurafood.R.id.formulario_cadastro_telefone;

public class FormularioCadastroActivity extends AppCompatActivity {

	private static final String APPBAR = "Cadastro";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_cadastro);
		setTitle(APPBAR);
	}

	@Override
	protected void onResume() {
		super.onResume();

		configuraCampo(formulario_cadastro_nome_completo);
		configuraCampo(formulario_cadastro_cpf);
		configuraCampo(formulario_cadastro_telefone);
		configuraCampo(formulario_cadastro_email);
		configuraCampo(formulario_cadastro_senha);

	}

	private void configuraCampo(int inputLayoutId) {
		final TextInputLayout inputLayout = findViewById(inputLayoutId);
		final EditText campo = inputLayout.getEditText();
		campo.setOnFocusChangeListener((v, hasFocus) -> quandoMudaDeFoco(inputLayout, hasFocus));
	}

	private void quandoMudaDeFoco(TextInputLayout inputLayout, boolean hasFocus) {
		Validador validador = new Validador(inputLayout);
		if(hasFocus) {
			validador.limpaCampo();
		} else {
			validador.valida();
		}
	}
}