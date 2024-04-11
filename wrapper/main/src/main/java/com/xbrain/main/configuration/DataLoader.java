package com.xbrain.main.configuration;

import com.xbrain.db.model.Cliente;
import com.xbrain.db.model.Produto;
import com.xbrain.db.repository.ClienteRepository;
import com.xbrain.db.repository.PedidoRepository;
import com.xbrain.db.repository.ProdutoRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

  @Autowired
  private final ProdutoRepository produtoRepository;

  @Autowired
  private final PedidoRepository pedidoRepository;

  @Autowired
  private final ClienteRepository clienteRepository;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    // produto
    Produto firstProduto = new Produto();
    Produto secondProduto = new Produto();

    firstProduto.setValor(111.0);
    secondProduto.setValor(110.0);

    produtoRepository.save(firstProduto);
    produtoRepository.save(secondProduto);

    // cliente
    Cliente firstCliente = new Cliente();
    firstCliente.setNome("Guilherme");

    clienteRepository.save(firstCliente);

  }
}
