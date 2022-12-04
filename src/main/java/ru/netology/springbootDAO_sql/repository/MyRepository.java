package ru.netology.springbootDAO_sql.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MyRepository {
    private final String SCRIPT = read("Script.sql");

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProductName(String name) {
        return namedParameterJdbcTemplate.queryForObject(read(SCRIPT),
                Map.of("name", name),
                (rs, prod) -> rs.getString("product_name"));
    }
}

