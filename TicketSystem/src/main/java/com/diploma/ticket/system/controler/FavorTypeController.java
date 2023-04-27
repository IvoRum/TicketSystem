package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.FavorType;
import com.diploma.ticket.system.service.FavorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v2/favortype")
public class FavorTypeController {


    private final FavorTypeService favorTypeService;

    @Autowired
    public FavorTypeController(FavorTypeService favorTypeService){

        this.favorTypeService = favorTypeService;
    }

    @GetMapping
    public ResponseEntity<List<FavorType>> getServiceTypes(){
        List<FavorType> responsBbody=new ArrayList<>();
        try{
            responsBbody=favorTypeService.getServiceTypes();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responsBbody);
    }

    @PostMapping
    public ResponseEntity registerNewServiceType(@RequestBody FavorType favorType){
        FavorType responseBody= null;
        try{
            responseBody=favorTypeService.addNewServiceType(favorType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("ServiceType"))
                .body(responseBody);
    }

    @PatchMapping(path="{favorName}")
    public ResponseEntity updateTicket(@PathVariable("favorName")String nameOfFavorTypeToUpdate,
                             @RequestBody FavorType favorType) {
        try {
            favorTypeService.updateTicket(nameOfFavorTypeToUpdate, favorType);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorType(
            @PathVariable Long id
    ) {
        try{
            favorTypeService.deleteFavorType(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
