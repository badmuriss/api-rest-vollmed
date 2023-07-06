package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.dto.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.dto.DadosCadastroPaciente;
import med.voll.api.domain.paciente.dto.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.dto.DadosListagemPaciente;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listarTodos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping(value="/inativos")
	public ResponseEntity<Page<DadosListagemPaciente>> listarInativos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		var page = repository.findAllByAtivoFalse(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<DadosDetalhamentoPaciente> detalharPorId(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = repository.getReferenceById(dados.id());
		if(paciente.getAtivo() == false) {
			throw new RuntimeException("Can't update inactive");
		}
		paciente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.desativarConta();
		return ResponseEntity.noContent().build();
	}
	
}
