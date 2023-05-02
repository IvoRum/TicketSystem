package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.payload.request.FavorCreationReqest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        responseBody=favorService.getService();

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewService(@RequestBody FavorCreationReqest favor){
        CreationResponse responce=null;
        responce=favorService.addNewService(favor);
        return ResponseEntity.ok(responce);
    }
    @PatchMapping(path="{favorName}")
    public ResponseEntity<String> updateService(
            @PathVariable("favorName")String name,
            @RequestBody Favor favor
    ){

        favorService.updateService(name, favor);
        return ResponseEntity.ok("Favor whit name"+name+"hase bean updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavor(
            @PathVariable Long id
    ) {
        favorService.deleteFavor(id);
        return ResponseEntity.ok().build();
    }
}
