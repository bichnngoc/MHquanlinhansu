package com.example.demo5.repository;

import java.util.List;
import java.util.Optional;

public interface JOOQRepository <T>{
    T save(T tablePojo);
}
