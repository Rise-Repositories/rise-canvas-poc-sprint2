package school.sptech.risepocsprint2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoCriacaoDto {
    @Size(min = 9, max = 9)
    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String localidade;

    @NotBlank
    private String uf;
    private String ibge;
    private String gia;

    @NotBlank
    private String ddd;

    private String siafi;
}
