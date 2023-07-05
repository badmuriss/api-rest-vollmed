package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.entities.Paciente;
import med.voll.api.entities.records.medico.DadosAtualizacaoPaciente;
import med.voll.api.entities.records.paciente.DadosCadastroPaciente;
import med.voll.api.entities.records.paciente.DadosListagemPaciente;
import med.voll.api.repositories.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
		repository.save(new Paciente(dados));
	}
	
	@GetMapping
	public Page<DadosListagemPaciente> listarTodos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
	}

	@GetMapping(value="/inativos")
	public Page<DadosListagemPaciente> listarInativos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		return repository.findAllByAtivoFalse(paginacao).map(DadosListagemPaciente::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		Paciente paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.desativarConta();
	}
	
}
