package com.xbrain.db.repository;

import com.xbrain.db.model.Produto;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
