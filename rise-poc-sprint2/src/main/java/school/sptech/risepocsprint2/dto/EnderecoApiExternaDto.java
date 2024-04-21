package school.sptech.risepocsprint2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoApiExternaDto {

    private String cep;

    @JsonProperty(value = "logradouro")
    private String logradouro;

    @JsonProperty(value = "complemento")
    private String complemento;

    @JsonProperty(value = "bairro")
    private String bairro;

    @JsonProperty(value = "localidade")
    private String localidade;

    @JsonProperty(value = "uf")
    private String uf;

    @JsonProperty(value = "ibge")
    private String ibge;

    @JsonProperty(value = "gia")
    private String gia;

    @JsonProperty(value = "ddd")
    private String ddd;

    @JsonProperty(value = "siafi")
    private String siafi;


    @Override
    public String toString() {
        return "EnderecoApiExternaDto{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", estado='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }
}
