package med.voll.api.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.entities.records.endereco.DadosEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	
	public Endereco(DadosEndereco endereco) {
		this.logradouro = endereco.logradouro();
		this.bairro = endereco.bairro();
		this.cep = endereco.cep();
		this.numero = endereco.numero();
		this.complemento = endereco.complemento();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
	}

	public void atualizarInformacoes(DadosEndereco dados) {
		
		String logradouro = dados.logradouro();
		String bairro = dados.bairro();
		String cep = dados.cep();
		String uf = dados.uf();
		String cidade = dados.cidade();
		String numero = dados.numero();
		String complemento = dados.complemento();
		
		if(logradouro != null && !logradouro.isBlank() && logradouro != this.logradouro) {
			this.logradouro = logradouro;
		}
		
		if (bairro != null && !bairro.isBlank() && bairro != this.bairro) {
            this.bairro = bairro;
        }
        
		if (cep != null && !cep.isBlank() && cep != this.cep) {
			
			@Pattern(regexp = "(^[0-9]{5})-?([0-9]{3}$)")
			String validCep = cep;
			
			this.cep = validCep;
        }
        
		if (uf != null && !uf.isBlank() && uf != this.uf) {
            this.uf = uf;
        }
        
		if (cidade != null && !cidade.isBlank() && cidade != this.cidade) {
            this.cidade = cidade;
        }
        
		if (numero != this.numero) {
            this.numero = numero;
        }
        
		if (complemento != this.complemento) {
            this.complemento = complemento;
        }
	}
	
	
}
