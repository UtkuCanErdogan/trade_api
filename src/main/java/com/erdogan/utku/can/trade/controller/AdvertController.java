package com.erdogan.utku.can.trade.controller;

import com.erdogan.utku.can.trade.dto.AdvertDto;
import com.erdogan.utku.can.trade.dto.CreateAdvertRequest;
import com.erdogan.utku.can.trade.dto.UpdateAdvertRequest;
import com.erdogan.utku.can.trade.service.AdvertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/advert")
public class AdvertController {

    private final AdvertService service;

    public AdvertController(AdvertService service) {
        this.service = service;
    }

    @GetMapping("/advert{id}")
    public ResponseEntity<AdvertDto> getAdvertById(@PathVariable Long advertId){
        return ResponseEntity.ok(service.getAdvertById(advertId));
    }

    @PostMapping("/createAdvert")
    public ResponseEntity<AdvertDto> createAdvert(@RequestBody CreateAdvertRequest request){
        return ResponseEntity.ok(service.createAdvert(request));
    }

    @PutMapping("/updateAdvert")
    public ResponseEntity<AdvertDto> updateAdvert(@RequestBody UpdateAdvertRequest request, @PathVariable Long advertId){
        return ResponseEntity.ok(service.updateAdvert(request,advertId));
    }

    @DeleteMapping("/{id}/delete}")
    public ResponseEntity<Void> deleteAdvert(@PathVariable("advertId") Long advertId){
        service.deleteAdvert(advertId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/toActive")
    public ResponseEntity<Void> toActiveAdvert(@PathVariable("advertId") Long advertId){
        service.toActiveAdvert(advertId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/deActive")
    public ResponseEntity<Void> deActiveAdvert(@PathVariable("advertId") Long advertId){
        service.deActiveAdvert(advertId);
        return ResponseEntity.ok().build();
    }
}
