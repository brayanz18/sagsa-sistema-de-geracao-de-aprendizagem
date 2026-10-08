package com.brayan_guilherme.services;

import com.brayan_guilherme.models.User;
import com.brayan_guilherme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Camada de serviço contendo as regras de negócio para a entidade Usuário
 */
@Service // Registra a classe como um componente de serviço Spring
public class UserService {

    @Autowired // Injeta a dependência do repositório
    private UserRepository userRepository;

    /**
     * Busca um usuário pelo ID ou lança exceção caso não exista
     */
    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! ID: " + id));
    }

    /**
     * Persiste um novo usuário
     */
    public User create(User obj) {
        obj.setId(null); // Garante que o ID é nulo para acionar o AUTO_INCREMENT
        return this.userRepository.save(obj);
    }

    /**
     * Atualiza dados de um usuário existente
     */
    public User update(User obj) {
        User newObj = findById(obj.getId()); // Verifica a existência antes de alterar
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setPerfil(obj.getPerfil());
        return this.userRepository.save(newObj);
    }

    /**
     * Exclui o usuário pelo ID
     */
    public void delete(Long id) {
        findById(id); // Valida se o registro existe no banco antes da exclusão
        this.userRepository.deleteById(id);
    }
}