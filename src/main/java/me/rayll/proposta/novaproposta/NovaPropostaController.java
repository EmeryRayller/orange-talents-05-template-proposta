package me.rayll.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/novaproposta")
public class NovaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<NovaPropostaDTO> criarNovaProposta(
            @RequestBody @Valid NovaPropostaDTO dto,
            UriComponentsBuilder uriComponentsBuilder){

        //Código para transformar um dto para model e salvar
        NovaProposta novaProposta = propostaRepository.save(dto.toModel());

        //Proposta criada tranformarda em DTO para poder ser manipulada.
        NovaPropostaDTO propostaSalvaDTO = novaProposta.toDTO();

        //Retorno de uma ReponseEntity com o header da localização do recurso e NovaPropostaCriada
        return ResponseEntity.
                    created(uriComponentsBuilder
                            .path("/novaproposta/{id}").buildAndExpand(propostaSalvaDTO.getId()).toUri())
                .body(novaProposta.toDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> propostaCriada(@PathVariable Long id){

        //Busca a proposta por id, caso não retorna exceção
        NovaProposta novaProposta = propostaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proposta não localizada.")
        );
        //mapeia entidade para dto
        NovaPropostaDTO propostaDTO = novaProposta.toDTO();

        return ResponseEntity.ok(propostaDTO);
    }
}
