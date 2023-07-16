package com.JuliaSystem.JuliaSystem.Component;


import com.JuliaSystem.JuliaSystem.Component.Component;
import com.JuliaSystem.JuliaSystem.Component.ComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Component getComponentByID(Integer componentID) {
        return componentRepository.findById(componentID).orElseThrow(() ->
                new IllegalStateException("Component is not found"));
    }

    public void newComponent (ComponentType componentType, String name, Integer price){
        Component component = new Component();
        component.setComponentType(componentType);
        component.setName(name);
        component.setPrice(price);
        componentRepository.save(component);
    }

    public void editComponent(Integer componentID,ComponentType componentType, String name, Integer price){
        if(componentType != null || name != null || price != null){
            Component component = getComponentByID(componentID);

            if(!component.getComponentType().equals(componentType) && componentType != null) {
                component.setComponentType(componentType);
            }
            if(!component.getName().equals(name) && name != null){
                component.setName(name);
            }
            if(!component.getPrice().equals(price) && price != null){
                component.setPrice(price);
            }
            componentRepository.save(component);
        }
    }
}
