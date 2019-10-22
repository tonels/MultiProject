package com.redisson.model;

import lombok.Data;

@Data
public class Ur {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Ur{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
