package com.JuliaSystem.JuliaSystem.Component;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Components")
public class Component {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ComponentType componentType;
    private String name;
    private Integer price;

    public Component(ComponentType componentType, String name, Integer price) {
    }
}


