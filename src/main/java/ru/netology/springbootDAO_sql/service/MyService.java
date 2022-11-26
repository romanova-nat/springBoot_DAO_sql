package ru.netology.springbootDAO_sql.service;

import org.springframework.stereotype.Service;
import ru.netology.springbootDAO_sql.repository.MyRepository;

@Service
public class MyService {
    private final MyRepository myRepository;

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public String getProductName(String name) {
        return myRepository.getProductName(name);
    }
}