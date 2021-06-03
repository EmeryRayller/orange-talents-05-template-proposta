package me.rayll.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class NovaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping("/novaproposta")
    public ResponseEntity<?> criarNovaProposta(
            NovaPropostaDTO dto,
            UriComponentsBuilder uriComponentsBuilder){

        //Código para transformar um dto para model
        NovaProposta novaProposta = dto.toModel();
        //Proposta criada tranformarda em DTO para poder ser manipulada.
        NovaPropostaDTO propostaSalvaDTO = novaProposta.toDTO();
        //Retorno de uma ReponseEntity com o header da localização do recurso e NovaPropostaCriada
        return ResponseEntity.
                    created(uriComponentsBuilder
                            .path("/novaproposta/{id}").buildAndExpand(propostaSalvaDTO.getId()).toUri())
                .body(novaProposta.toDTO());
    }

    @GetMapping("/novaproposta/{id}")
    public ResponseEntity<?> propostaCriada(@PathVariable Long id){
        NovaProposta novaProposta = propostaRepository.findByDocumento().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proposta não localizada.")
        );

        NovaPropostaDTO propostaDTO = novaProposta.toDTO();

        return ResponseEntity.ok(propostaDTO);
    }
}
