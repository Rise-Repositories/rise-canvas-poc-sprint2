package school.sptech.risepocsprint2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import school.sptech.risepocsprint2.dto.EnderecoListagemDto;
import school.sptech.risepocsprint2.dto.EnderecoMapper;
import school.sptech.risepocsprint2.entity.Endereco;
import school.sptech.risepocsprint2.repository.EnderecoRepository;
import school.sptech.risepocsprint2.dto.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public ResponseEntity<ListaObj<EnderecoListagemDto>> listar() {
        ListaObj<Endereco> enderecos = new ListaObj (repository.findAll());
        ListaObj<EnderecoListagemDto> enderecosDto = new ListaObj<>(enderecos.size());

        for (int i = 0; i < enderecos.size(); i++) {
            EnderecoListagemDto dto = EnderecoMapper.toListagemDto(enderecos.get(i));
            enderecosDto.add(dto);
        }

        if (enderecosDto.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecosDto);
    }

    @GetMapping("/buscar-endereco")
    public ResponseEntity<EnderecoListagemDto> buscarEndereco(@RequestParam String cep) {

        RestClient client = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws/")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String raw = client.get()
                .uri(cep + "/json")
                .retrieve()
                .body(String.class);


       EnderecoApiExternaDto endereco = client.get()
                .uri(cep + "/json")
                .retrieve()
                .body(EnderecoApiExternaDto.class);

        if (endereco == null) {
            return ResponseEntity.noContent().build();
        }

        EnderecoListagemDto resposta = new EnderecoListagemDto();
        resposta.setCep(endereco.getCep());
        resposta.setLogradouro(endereco.getLogradouro());
        resposta.setComplemento(endereco.getComplemento());
        resposta.setBairro(endereco.getBairro());
        resposta.setLocalidade(endereco.getLocalidade());
        resposta.setUf(endereco.getUf());
        resposta.setIbge(endereco.getIbge());
        resposta.setGia(endereco.getGia());
        resposta.setDdd(endereco.getDdd());
        resposta.setSiafi(endereco.getSiafi());

        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<EnderecoListagemDto> criar(@RequestBody @Valid EnderecoCriacaoDto dto) {
        Optional<Endereco> existente = repository.findAllByCep(dto.getCep());

        if (existente.isPresent()) {
            return ResponseEntity.status(409).build();
        }

        Endereco entity = EnderecoMapper.toEntity(dto);
        repository.save(entity);
        EnderecoListagemDto resposta = EnderecoMapper.toListagemDto(entity);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/por-cep/{cep}")
    public ResponseEntity<EnderecoListagemDto> criarPorCep(@PathVariable String cep) {
        EnderecoCriacaoDto dto = EnderecoMapper.toEnderecoCriacaoDto(buscarEndereco(cep).getBody());
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return criar(dto);
    }

    @GetMapping("/buscar-endereco-por-cep-cadastrado")
    public ResponseEntity<EnderecoListagemDto> buscar(@RequestParam String cep) {
        ListaObj<Endereco> enderecos = new ListaObj (repository.findAll());
        if (enderecos.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        int[] ceps = new int[enderecos.size()];

        for (int i = 0; i < enderecos.size(); i++) {
            String cepFormat = enderecos.get(i).getCep().replaceAll("\\D+","");
            Integer cepInteiro = Integer.parseInt(cepFormat);
            ceps[i] = cepInteiro;
        }
        quickSort(ceps, 0, ceps.length-1);
        int busca = buscaBinaria(ceps, Integer.parseInt(cep));
        if (busca == -1) {
            return ResponseEntity.noContent().build();
        }
        int cepEncontrado = ceps[busca];
        for (int i = 0; i < enderecos.size(); i++) {
            String cepFormat = enderecos.get(i).getCep().replaceAll("\\D+","");
            Integer cepInteiro = Integer.parseInt(cepFormat);
            if (cepInteiro == cepEncontrado) {
                return ResponseEntity.ok(EnderecoMapper.toListagemDto(enderecos.get(i)));
            }
        }
        return null;
    }

    public int buscaBinaria(int[] v, int busca) {
        int indInicio = 0;
        int indFim = v.length - 1;
        int indMeio;

        while (indInicio <= indFim) {
            indMeio = (indInicio + indFim) / 2;

            if (v[indMeio] == busca) {
                return indMeio;
            } else if (v[indMeio] > busca) {
                indFim = indMeio - 1;
            } else {
                indInicio = indMeio + 1;
            }
        }
        return -1;
    }

    public static void quickSort(int[] v, int indInicio, int indFim) {
        int i, j, pivo;
        i = indInicio;
        j = indFim;
        pivo = v[(indFim + indInicio) / 2];
        while (i <= j) {
            while (i < indFim && v[i] < pivo) {
                i++;
            }
            while (j > indInicio && v[j] > pivo) {
                j--;
            }
            if (i <= j) {
                int aux = v[i];
                v[i] = v[j];
                v[j] = aux;
                i++;
                j--;
            }
        }

        if (indInicio < j) {
            quickSort(v, indInicio, j);
        }
        if (i < indFim) {
            quickSort(v, i, indFim);
        }
    }

    // Apenas para demonstração
    @PostMapping("/popular")
    public ResponseEntity<ListaObj<EnderecoListagemDto>> popular() {
        criarPorCep("09020-230");
        criarPorCep("09520-900");
        criarPorCep("09750-000");
        criarPorCep("01238-000");
        criarPorCep("02001-000");
        criarPorCep("02125-001");
        criarPorCep("05417-040");
        criarPorCep("03125-055");
        criarPorCep("05417-040");
        criarPorCep("01001-000");
        return listar();
    }


}
