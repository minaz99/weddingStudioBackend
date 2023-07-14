package com.JuliaSystem.JuliaSystem.controller;


import com.JuliaSystem.JuliaSystem.component.ComponentService;
import com.JuliaSystem.JuliaSystem.component.ComponentType;
import lombok.AllArgsConstructor;
import com.JuliaSystem.JuliaSystem.component.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/components")
public class ComponentController {

    private final ComponentService componentService;

    @GetMapping
    public List<Component> getComponents() {
        return componentService.getAllComponents();
    }
    @GetMapping("/{componentID}")
    public Component getComponentByID(@PathVariable("componentID") Integer componentID){
        return componentService.getComponentByID(componentID);
    }

    @PostMapping("/add")
    public void addComponent(@RequestParam ComponentType componentType,
                             @RequestParam String name,@RequestParam Integer price){
        componentService.newComponent(componentType,name,price);
    }

    @PostMapping("/{componentID}")
    public void editComponent(@PathVariable("componentID") Integer contractID, @RequestParam(required = false) ComponentType componentType,
                              @RequestParam(required = false) String name,@RequestParam(required = false) Integer price){
        componentService.editComponent(contractID,componentType,name,price);
    }


}
