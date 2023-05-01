# Requisitos

<strong>Um pagamento possui os seguintes campos</strong>:
- Código do Débito sendo pago (valor inteiro)
- CPF ou CNPJ do Pagador
- Método de pagamento (boleto, pix, cartao_credito ou cartao_debito)
- Número do cartão
- Este campo só será enviado se o método de pagamento for cartao_credito ou cartao_debito.
  Valor do pagamento


<strong>A API deve ser capaz de atualizar o status de um pagamento.</strong>
- A atualização do status de um pagamento sempre irá conter o ID do Pagamento e o novo status.
- Quando o pagamento está Pendente de Processamento, ele pode ser alterado para Processado com Sucesso ou Processado com Falha.
- Quando o pagamento está Processado com Sucesso, ele não pode ter seu status alterado.
- Quando o pagamento está Processado com Falha, ele só pode ter seu status alterado para Pendente de Processamento.

<strong>A API deve ser capaz de listar todos os pagamentos recebidos e oferecer filtros de busca para o cliente.
</strong> <br>
<strong> Os filtros de busca devem ser: </strong>
- Por código do débito
- Por CPF/CNPJ do pagador
- Por status do pagamento

<strong>A API deve ser capaz de deletar um pagamento, desde que este ainda esteja com status Pendente de Processamento.</strong>

# Tecnologias
- Padrão REST na construção da API
- Payloads enviados para a API em formato JSON
- Respostas da API em formato JSON
- Spring Boot com Java 17
- Banco de dados H2 embutido no Spring Boot
- Swagger

# Observações
- Segue em anexo a collection para testes via postman também
- A documentação da API com swagger ao rodar o projeto fica disponível por meio do link
http://localhost:8080/swagger-ui/index.html

