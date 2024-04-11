package com.xbrain.main;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbrain.db.model.Cliente;
import com.xbrain.db.model.Produto;
import com.xbrain.db.repository.ClienteRepository;
import com.xbrain.db.repository.ProdutoRepository;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MainApplicationTests {
  @Autowired
  MockMvc mvc;
  @Autowired
  private ProdutoRepository produtoRepository;
  @Autowired
  private ClienteRepository clienteRepository;

  @Test
  void contextLoads() {
  }

  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /*
    ------------ Clientes ------------
   */
  @Test
  public void getAllClientes() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/cliente").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
  }

  @Test
  public void getOneCliente() throws Exception {
    Cliente cliente = clienteRepository.findAll().iterator().next();

    StringBuilder moutingEndpoint = new StringBuilder();
    moutingEndpoint.append("/cliente/").append(cliente.getId().toString());

    mvc.perform(
            MockMvcRequestBuilders.get(moutingEndpoint.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }

  @Test
  public void createCliente() throws Exception {
    ;
    HashMap<String, String> map = new HashMap<>();
    map.put("nome", "NOME TESTE");

    mvc.perform(MockMvcRequestBuilders.post("/cliente").content(asJsonString(map))
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
  }

  /*
    ------------ Produtos ------------
   */
  @Test
  public void getAllProdutos() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/produto").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
  }

  @Test
  public void getOneProduto() throws Exception {
    Produto produto = produtoRepository.findAll().iterator().next();

    StringBuilder moutingEndpoint = new StringBuilder();
    moutingEndpoint.append("/produto/").append(produto.getId().toString());

    mvc.perform(
            MockMvcRequestBuilders.get(moutingEndpoint.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }

  @Test
  public void createProduto() throws Exception {
    HashMap<String, String> map = new HashMap<>();
    map.put("valor", "55555");

    mvc.perform(MockMvcRequestBuilders.post("/produto").content(asJsonString(map))
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
  }

  /*
  ------------ Criando Pedido ------------
   */
  @Test
  public void createPedido() throws Exception {
    Cliente cliente = clienteRepository.findAll().iterator().next();
    Produto produto = produtoRepository.findAll().iterator().next();

    StringBuilder building = new StringBuilder();
    building.append("[").append(produto.getId()).append("]");

    HashMap<String, String> map = new HashMap<>();
    map.put("clienteId", cliente.getId().toString());
    map.put("codigoProdutos", building.toString());
    map.put("enderecoEntrega", "rua exemple");

    mvc.perform(MockMvcRequestBuilders.post("/pedido").content(asJsonString(map))
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
  }
}
