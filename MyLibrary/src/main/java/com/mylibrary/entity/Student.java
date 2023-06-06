package com.mylibrary.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Student {
    int id;
    String name;
}
