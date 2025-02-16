package com.marketplace.apimarketplace.Mapper;

import com.marketplace.apimarketplace.DTO.UsuarioDTO;
import com.marketplace.apimarketplace.Model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioModel toEntity(UsuarioDTO usuarioDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setName(usuarioDTO.getName());
        usuarioModel.setEmail(usuarioDTO.getEmail());
        usuarioModel.setMunicipality(usuarioDTO.getMunicipality());
        usuarioModel.setContact(usuarioDTO.getContact());
        usuarioModel.setPassword(usuarioDTO.getPassword());
        return usuarioModel;
    }

    public UsuarioDTO toDto(UsuarioModel usuarioModel) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setName(usuarioModel.getName());
        usuarioDTO.setEmail(usuarioModel.getEmail());
        usuarioDTO.setMunicipality(usuarioModel.getMunicipality());
        usuarioDTO.setContact(usuarioModel.getContact());
        usuarioDTO.setPassword(usuarioModel.getPassword());
        return usuarioDTO;
    }

}
