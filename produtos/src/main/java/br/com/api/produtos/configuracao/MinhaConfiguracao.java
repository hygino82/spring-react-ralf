package br.com.api.produtos.configuracao;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguracao {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
