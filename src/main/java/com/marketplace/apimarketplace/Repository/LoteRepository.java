package com.marketplace.apimarketplace.Repository;

import com.marketplace.apimarketplace.Model.LoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoteRepository extends JpaRepository<LoteModel, Long> {

    //Como extiende JPA ya tiene las funcionalidades incorpaoradas para intercatuar con la entidad Lote
    List<LoteModel> findByUsuarioId(Long usuarioId);
}
