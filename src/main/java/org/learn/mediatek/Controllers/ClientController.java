package org.learn.mediatek.Controllers;

import jakarta.validation.Valid;
import org.learn.mediatek.Services.ClientService;
import org.learn.mediatek.dto.ClientRequestDto;
import org.learn.mediatek.dto.ClientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    @Qualifier("Impl1")
    private ClientService  clientService;

    @GetMapping("")
    public ResponseEntity<List<ClientResponseDto>> getClients(){
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequestDto clientRequestDto){
        clientService.save(clientRequestDto);
        return new ResponseEntity<>("Client Saved",HttpStatus.CREATED);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable("id") Integer id) {
       ClientResponseDto clientResponseDto= clientService.findById(id);
       return ResponseEntity.ok(clientResponseDto);
    }

    @GetMapping("/nom/{nom}")
    public List<ClientResponseDto> findByNom(@PathVariable("nom") String nom) {
        return clientService.findByNom(nom);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<ClientResponseDto> update(@Valid @RequestBody ClientRequestDto clientRequestDto,@PathVariable Integer id) {
       ClientResponseDto clientResponseDto=  clientService.update(clientRequestDto, id);
       return ResponseEntity.accepted().body(clientResponseDto);
    }
}
