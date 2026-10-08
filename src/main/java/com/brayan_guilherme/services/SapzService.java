package com.brayan_guilherme.services;

import com.brayan_guilherme.models.Sapz;
import com.brayan_guilherme.models.User;
import com.brayan_guilherme.repositories.SapzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SapzService {

    @Autowired
    private SapzRepository sapzRepository;

    @Autowired
    private UserService userService;

    public Sapz findById(Long id) {
        Optional<Sapz> sapz = this.sapzRepository.findById(id);
        return sapz.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada! Id: " + id + ", Tipo: " + Sapz.class.getName()
        ));
    }

    public List<Sapz> findAllByUserId(Long userId) {
        this.userService.findById(userId);
        return this.sapzRepository.findByUser_Id(userId);
    }

    @Transactional
    public Sapz create(Sapz obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        return this.sapzRepository.save(obj);
    }

    @Transactional
    public Sapz update(Sapz obj) {
        Sapz newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.sapzRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.sapzRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível eliminar a tarefa pois existem entidades relacionadas!");
        }
    }
}