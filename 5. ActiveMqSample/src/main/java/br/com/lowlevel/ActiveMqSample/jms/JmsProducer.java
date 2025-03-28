package br.com.lowlevel.ActiveMqSample.jms;

import br.com.lowlevel.ActiveMqSample.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public JmsProducer(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Employee employee) throws Exception {
        String employeeJson = objectMapper.writeValueAsString(employee);
        jmsTemplate.convertAndSend("demo.topic", employeeJson);
    }
}

