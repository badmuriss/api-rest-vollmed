package med.voll.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.paciente.DadosRegistroPaciente;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@PostMapping
	public void cadastrar(@RequestBody DadosRegistroPaciente dados) {
		System.out.println(dados);
	}
	
}
