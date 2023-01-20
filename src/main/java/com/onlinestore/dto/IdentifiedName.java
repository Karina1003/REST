package com.onlinestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class IdentifiedName {
    private long id;
    private String name;
}
