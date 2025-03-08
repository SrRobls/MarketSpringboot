package com.marketplace.apimarketplace.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.apimarketplace.DTO.LoteDTO;
import com.marketplace.apimarketplace.Service.LoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;

    @GetMapping
    public ResponseEntity<List<LoteDTO>> getAllLotes() {
        return ResponseEntity.ok(loteService.getAllLotes());
    }


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LoteDTO> createLote(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("location") String location,
            @RequestParam("area") Double area,
            @RequestParam("usuarioId") Long usuarioId,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        System.out.println("Usuario ID recibido en el request: " + usuarioId);

        LoteDTO loteDto = new LoteDTO();
        loteDto.setTitle(title);
        loteDto.setDescription(description);
        loteDto.setPrice(price);
        loteDto.setLocation(location);
        loteDto.setArea(area);
        loteDto.setUsuarioId(usuarioId);

        LoteDTO loteSaved = loteService.createLote(loteDto, image, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(loteSaved);
    }


    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<LoteDTO> updateLote(
            @PathVariable Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "area", required = false) Double area,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        Optional<LoteDTO> updatedLote = loteService.updateLote(id, title, description, price, location, area, image);
        return updatedLote.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



    @GetMapping("/{id}")
    public ResponseEntity<LoteDTO> getLoteById(@PathVariable Long id) {
        return loteService.getLoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoteById(@PathVariable Long id) {
       if (loteService.deleteLote(id)) {
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.notFound().build();
       }
    };

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LoteDTO>> getLotesByUsuarioId(@PathVariable Long usuarioId) {
        List<LoteDTO> lotes = loteService.getLotesByUsuarioId(usuarioId);
        return ResponseEntity.ok(lotes);
    }

}
