package com.JuliaSystem.JuliaSystem.pkg;

import com.JuliaSystem.JuliaSystem.component.Component;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Packages")
public class Package {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private List<Integer> componentsIDs;
    private Integer price;

    public Package(String name, List<Integer> componentsIds, Integer price) {
    }
}
