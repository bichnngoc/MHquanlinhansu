package com.example.demo5.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterCondition {
    String field;
    String operation;  // Changed from operator to operation
    String value;
}
