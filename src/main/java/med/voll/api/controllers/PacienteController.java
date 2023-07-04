package med.voll.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.entities.records.DadosCadastroPaciente;
import med.voll.api.repositories.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
		System.out.println(dados);
	}
	
}
