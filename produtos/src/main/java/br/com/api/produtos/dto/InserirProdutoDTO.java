package br.com.api.produtos.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InserirProdutoDTO {
	@NotBlank(message = "O campo nome é obrigatório")
    private String nome;
	@NotBlank(message = "O campo marca é obrigatório")
    private String marca;
}
