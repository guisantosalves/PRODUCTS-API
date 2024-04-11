Olá.
para rodar o projeto:
- precisa estar com o docker instalado
- caso nao esteja instalado [Clique aqui](https://docs.docker.com/engine/install/“)
- rodar o seguinte comando:
````

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management

````
- Fazer Run do file: ProducerApplication.java
- Fazer Run do file: ConsumerApplication.java
- Cadastrar um pedido com os dados de Cliente e Produto já inseridos.
