
# Exemplo Spring + SOAP

Se criar novos xsd

```shell
    mvn compile
```

## Para testar a aplicação:

- Criar request.xml

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://www.lowlevel.com.br/springsoap/gen">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:getCountryRequest>
            <gs:name>Spain</gs:name>
        </gs:getCountryRequest>
    </soapenv:Body>
</soapenv:Envelope>

```
- Fazer request
```shell
    curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws
```

Obs: Pode utilizar [SOAPUI](https://www.soapui.org/)

