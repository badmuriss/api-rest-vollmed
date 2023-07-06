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
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.dto.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.dto.DadosCadastroMedico;
import med.voll.api.domain.medico.dto.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.dto.DadosListagemMedico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.dto.DadosListagemPaciente;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		repository.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listarTodos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping(value="/inativos")
	public ResponseEntity<Page<DadosListagemMedico>> listarInativos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		var page = repository.findAllByAtivoFalse(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<DadosDetalhamentoMedico> detalharPorId(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		Medico medico = repository.getReferenceById(dados.id());
		if(medico.getAtivo() == false) {
			throw new RuntimeException("Can't update inactive");
		}
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.desativarConta();
		return ResponseEntity.noContent().build();
	}
	
}
