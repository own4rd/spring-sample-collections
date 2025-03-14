package br.com.lowlevel.soap_ws_sample.repositories;

import br.com.lowlevel.springsoap.gen.Country;
import br.com.lowlevel.springsoap.gen.Currency;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {

        Country spain = new Country();
        spain.setName("Spain");
        spain.setPopulation(46704314);
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        Country france = new Country();
        france.setName("France");
        france.setPopulation(65273511);
        france.setCapital("Paris");
        france.setCurrency(Currency.EUR);
        countries.put("Spain", spain);
        countries.put("France", france);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
}

