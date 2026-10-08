package com.brayan_guilherme.controllers;

import com.brayan_guilherme.models.Sapz;
import com.brayan_guilherme.services.SapzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sapz")
public class SapzController {

    @Autowired
    private SapzService sapzService;

    @GetMapping("/{id}")
    public ResponseEntity<Sapz> findById(@PathVariable Long id) {
        Sapz obj = this.sapzService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Sapz>> findAllByUserId(@PathVariable Long userId) {
        List<Sapz> list = this.sapzService.findAllByUserId(userId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Sapz obj) {
        this.sapzService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Sapz obj, @PathVariable Long id) {
        obj.setId(id);
        this.sapzService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.sapzService.delete(id);
        return ResponseEntity.noContent().build();
    }
}