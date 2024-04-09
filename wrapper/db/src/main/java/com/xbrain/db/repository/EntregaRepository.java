package com.xbrain.db.repository;

import com.xbrain.db.model.Entrega;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, UUID> {
}
