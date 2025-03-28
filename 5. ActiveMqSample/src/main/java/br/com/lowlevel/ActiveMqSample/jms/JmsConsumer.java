package br.com.lowlevel.ActiveMqSample.jms;

import br.com.lowlevel.ActiveMqSample.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsConsumer {

    private final ObjectMapper objectMapper;

    public JmsConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(String employeeJson) {
        try {
            Employee employee = objectMapper.readValue(employeeJson, Employee.class);
            log.info("Received Employee: " + employee);
        } catch (Exception e) {
            log.error("Error processing message: " + e.getMessage(), e);
        }
    }
}

