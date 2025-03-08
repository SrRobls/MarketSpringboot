    package com.marketplace.apimarketplace.Service;

    import com.marketplace.apimarketplace.DTO.LoteDTO;
    import com.marketplace.apimarketplace.Mapper.LoteMapper;
    import com.marketplace.apimarketplace.Model.LoteModel;
    import com.marketplace.apimarketplace.Model.UsuarioModel;
    import com.marketplace.apimarketplace.Repository.LoteRepository;
    import com.marketplace.apimarketplace.Repository.UsuarioRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class LoteService {

        private final LoteRepository loteRepository;
        private final UsuarioRepository usuarioRepository;
        private final LoteMapper loteMapper;
        private final S3Service s3Service;

        public List<LoteDTO> getAllLotes() {
            return loteRepository.findAll().stream()
                    .map(loteMapper::toDTO)
                    .collect(Collectors.toList());
        }

        public Optional<LoteDTO> getLoteById(Long id) {
            return loteRepository.findById(id)
                    .map(loteMapper::toDTO);
        }

        public LoteDTO createLote(LoteDTO loteDto, MultipartFile image, Long usuarioId) {
            // Validar si el usuarioId es nulo
            if (usuarioId == null) {
                throw new RuntimeException("El usuarioId no puede ser nulo.");
            }
            // Buscar el usuario en la base de datos
            UsuarioModel usuario = usuarioRepository.findById(loteDto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (image != null && !image.isEmpty()) {
                loteDto.setImage(s3Service.uploadImage(image));
            }


            // Convertir DTO a entidad y asignar el usuario
            LoteModel lote = loteMapper.toEntity(loteDto, usuario);
            lote.setUsuario(usuario);
            lote.setArea(loteDto.getArea());

            return loteMapper.toDTO(loteRepository.save(lote));
        }

        public Optional<LoteDTO> updateLote(Long id, String title, String description, Double price, String location, Double area, MultipartFile image) {
            return loteRepository.findById(id).map(lote -> {

                if (title != null && !title.isEmpty()) {
                    lote.setTitle(title);
                }
                if (description != null && !description.isEmpty()) {
                    lote.setDescription(description);
                }
                if (price != null) {
                    lote.setPrice(price);
                }
                if (location != null && !location.isEmpty()) {
                    lote.setLocation(location);
                }
                if (image != null && !image.isEmpty()) {
                    lote.setImage(s3Service.uploadImage(image));
                }
                if (area != null) {
                    lote.setArea(area); // Actualizar el tamaño
                }

                return loteMapper.toDTO(loteRepository.save(lote));
            });
        }

        public Boolean deleteLote(Long id) {
            return loteRepository.findById(id).map(lote -> {
                loteRepository.delete(lote);
                return true;
            }).orElse(false);
        }

        public List<LoteDTO> getLotesByUsuarioId(Long usuarioId) {
            return loteRepository.findByUsuarioId(usuarioId)
                    .stream()
                    .map(loteMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }
