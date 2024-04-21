package school.sptech.risepocsprint2.dto;

import school.sptech.risepocsprint2.entity.Endereco;

public class EnderecoMapper {

    public static EnderecoListagemDto toListagemDto(Endereco entity) {
        var dto = new EnderecoListagemDto();
        dto.setCep(entity.getCep());
        dto.setLogradouro(entity.getLogradouro());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setLocalidade(entity.getLocalidade());
        dto.setUf(entity.getUf());
        dto.setIbge(entity.getIbge());
        dto.setGia(entity.getGia());
        dto.setDdd(entity.getDdd());
        dto.setSiafi(entity.getSiafi());
        return dto;
    }

    public static EnderecoCriacaoDto toEnderecoCriacaoDto(EnderecoListagemDto listagemDto) {
        var dto = new EnderecoCriacaoDto();
        dto.setCep(listagemDto.getCep());
        dto.setLogradouro(listagemDto.getLogradouro());
        dto.setComplemento(listagemDto.getComplemento());
        dto.setBairro(listagemDto.getBairro());
        dto.setLocalidade(listagemDto.getLocalidade());
        dto.setUf(listagemDto.getUf());
        dto.setIbge(listagemDto.getIbge());
        dto.setGia(listagemDto.getGia());
        dto.setDdd(listagemDto.getDdd());
        dto.setSiafi(listagemDto.getSiafi());
        return dto;
    }

    public static Endereco toEntity(EnderecoListagemDto dto) {
        var entity = new Endereco();
        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setLocalidade(dto.getLocalidade());
        entity.setUf(dto.getUf());
        entity.setIbge(dto.getIbge());
        entity.setGia(dto.getGia());
        entity.setDdd(dto.getDdd());
        entity.setSiafi(dto.getSiafi());
        return entity;
    }
    public static Endereco toEntity(EnderecoCriacaoDto dto) {
        var entity = new Endereco();
        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setLocalidade(dto.getLocalidade());
        entity.setUf(dto.getUf());
        entity.setIbge(dto.getIbge());
        entity.setGia(dto.getGia());
        entity.setDdd(dto.getDdd());
        entity.setSiafi(dto.getSiafi());
        return entity;
    }
}
