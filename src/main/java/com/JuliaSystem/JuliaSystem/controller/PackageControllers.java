package com.JuliaSystem.JuliaSystem.controller;


import com.JuliaSystem.JuliaSystem.pkg.Package;
import com.JuliaSystem.JuliaSystem.pkg.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/packages")
public class PackageControllers {
    private final PackageService packageService;

    @GetMapping
    public List<Package> getAllPackages() {
     return packageService.getPackages();
    }

    @PostMapping("/add")
    public void addPackage(@RequestParam String name, List<Integer> componentIDs, Integer price) {
        packageService.addPackage(name, componentIDs,price);
    }

    @PostMapping("/{packageID}")
    public void editPackage(@PathVariable("packageID") Integer packageID, @RequestParam(required = false) String name, @RequestParam(required = false)

                            List<Integer> componentsIDs, @RequestParam(required = false) Integer price){
        packageService.editPackage(packageID,name,componentsIDs,price);
    }
}
