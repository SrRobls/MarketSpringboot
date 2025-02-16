package com.marketplace.apimarketplace.Mapper;
import com.marketplace.apimarketplace.DTO.LoteDTO;
import com.marketplace.apimarketplace.Model.LoteModel;
import com.marketplace.apimarketplace.Model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class LoteMapper {
    public LoteModel toEntity(LoteDTO dto, UsuarioModel usuario) {
        LoteModel lote = new LoteModel();
        lote.setId(dto.getId());
        lote.setTitle(dto.getTitle());
        lote.setDescription(dto.getDescription());
        lote.setPrice(dto.getPrice());
        lote.setLocation(dto.getLocation());
        lote.setImage(dto.getImage());
        lote.setUsuario(usuario);
        return lote;
    }

    public LoteDTO toDTO(LoteModel lote) {
        LoteDTO dto = new LoteDTO();
        dto.setId(lote.getId());
        dto.setTitle(lote.getTitle());
        dto.setDescription(lote.getDescription());
        dto.setPrice(lote.getPrice());
        dto.setLocation(lote.getLocation());
        dto.setImage(lote.getImage());
        dto.setUsuarioId(lote.getUsuario().getId());
        return dto;
    }
}
