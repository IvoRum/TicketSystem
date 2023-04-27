package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.payload.request.FavorCreationReqest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v2/favor")
public class FavorController {
    private final FavorService favorService;
    @Autowired
    public FavorController(FavorService favorService){this.favorService = favorService;}

    @GetMapping
    public ResponseEntity<List<Favor>> getService(){
        List<Favor> responseBody=new ArrayList<>();
        try{
            responseBody=favorService.getService();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewService(@RequestBody FavorCreationReqest favor){
        CreationResponse responce=null;
        try{
            responce=favorService.addNewService(favor);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responce);
    }
    //TODO Make better
    @PatchMapping(path="{favorName}")
    public ResponseEntity updateService(@PathVariable("favorName")String name,
                              @RequestBody Favor favor){
        try{
            favorService.updateService(name, favor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavor(
            @PathVariable Long id
    ) {
        try{
            favorService.deleteFavor(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
