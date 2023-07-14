package com.JuliaSystem.JuliaSystem.pkg;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;

    public List<Package> getPackages(){
        return packageRepository.findAll();
    }

    public void addPackage(String name,List<Integer> componentsIds,Integer price) {
        Package pkg = new Package(name,componentsIds,price);
        packageRepository.save(pkg);
    }

    public void editPackage(Integer pkgID,String name, List<Integer> componentsIds, Integer price) {
        Package pkg = packageRepository.findById(pkgID).orElseThrow(() -> new IllegalStateException("Package is not found"));
        if(!pkg.getName().equals(name) && name != null){
            pkg.setName(name);
        }
        if(!pkg.getComponentsIDs().equals(componentsIds) && componentsIds != null){
            pkg.setComponentsIDs(componentsIds);
        }
        if(!pkg.getPrice().equals(price) && price != null){
            pkg.setPrice(price);
        }
    }

    public Package getPackageByID (Integer packageID) {
        return packageRepository.findById(packageID).orElseThrow(() -> new IllegalStateException("Package is not found"));
    }


}
