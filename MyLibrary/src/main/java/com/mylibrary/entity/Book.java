package com.mylibrary.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Book {
    String id;
    String title;
    String variety;
    int num;

}
