package com.xbrain.db.repository;

import com.xbrain.db.model.Pedido;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
