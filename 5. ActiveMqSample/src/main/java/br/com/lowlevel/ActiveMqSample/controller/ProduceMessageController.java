package br.com.lowlevel.ActiveMqSample.controller;


import br.com.lowlevel.ActiveMqSample.jms.JmsProducer;
import br.com.lowlevel.ActiveMqSample.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProduceMessageController {

    @Autowired
    JmsProducer jmsProducer;

    @PostMapping(value="/api/employee")
    public Employee sendMessage(@RequestBody Employee employee){
        try {
            jmsProducer.sendMessage(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
}
