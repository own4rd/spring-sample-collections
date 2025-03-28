### Detalhes:
O JmsTemplate é uma abstração do Spring para enviar e receber mensagens JMS (Java Message Service). Ele facilita a comunicação com o broker de mensagens (no caso, o ActiveMQ).

jmsTemplate.setConnectionFactory(connectionFactory()): Aqui, estamos dizendo ao JmsTemplate para usar o ConnectionFactory que acabamos de configurar para se conectar ao ActiveMQ.


#### Exemplo sendMessage:
```shell
curl -X POST "http://localhost:8080/api/employee"      
      -H "accept: */*"      -H "Content-Type: application/json"      -d '{
           "employeeFirstName": "shine",
           "employeeFullName": "shine m test",
           "employeeId": "129",
           "employeeLastName": "test",
           "employeeMiddleName": "m"
         }'

```