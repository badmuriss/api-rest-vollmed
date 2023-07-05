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
import med.voll.api.entities.Medico;
import med.voll.api.entities.records.medico.DadosAtualizacaoMedico;
import med.voll.api.entities.records.medico.DadosCadastroMedico;
import med.voll.api.entities.records.medico.DadosListagemMedico;
import med.voll.api.repositories.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		repository.save(new Medico(dados));
	}
	
	@GetMapping
	public Page<DadosListagemMedico> listarTodos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}

	@GetMapping(value="/inativos")
	public Page<DadosListagemMedico> listarInativos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		return repository.findAllByAtivoFalse(paginacao).map(DadosListagemMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.desativarConta();
	}
	
}
