package com.brayan_guilherme.controllers;

// Importação da entidade User mapeada para a tabela 'usuario'
import com.brayan_guilherme.models.User;
// Importação do serviço com as regras de negócio do usuário
import com.brayan_guilherme.services.UserService;
// Injeção de dependências do Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
// Utilitário para construir respostas HTTP personalizadas (códigos de status e corpo)
import org.springframework.http.ResponseEntity;
// Anotações Spring MVC para criação de APIs REST
import org.springframework.web.bind.annotation.*;
// Utilitário para gerar a URI de um novo recurso criado na resposta HTTP
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controladora REST responsável por expor os endpoints da rota /usuario
 */
@RestController // Define esta classe como um controlador REST que retorna respostas JSON
@RequestMapping("/usuario") // Define a rota base de acesso para todos os métodos da classe
public class UserController {

    @Autowired // Injeta automaticamente a instância de UserService gerenciada pelo Spring
    private UserService userService;

    /**
     * Endpoint para consultar um usuário através do seu ID.
     * Rota: GET http://localhost:8080/usuario/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        // Busca o usuário na camada de serviço utilizando o ID enviado pela URL
        User obj = this.userService.findById(id);
        // Retorna HTTP status 200 OK com o objeto Usuário no corpo da resposta
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Endpoint para cadastrar um novo usuário na base de dados.
     * Rota: POST http://localhost:8080/usuario
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User obj) {
        // Envia o objeto recebido no corpo do JSON para persistência na camada de
        // serviço
        this.userService.create(obj);
        // Monta a URI do novo recurso criado (ex: /usuario/1) para o cabeçalho
        // 'Location'
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        // Retorna HTTP status 201 Created contendo a URI no cabeçalho
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para atualizar as informações de um usuário existente.
     * Rota: PUT http://localhost:8080/usuario/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Long id) {
        // Associa o ID vindo da URL ao objeto recebido no corpo da requisição
        obj.setId(id);
        // Executa a atualização na camada de serviço
        this.userService.update(obj);
        // Retorna HTTP status 204 No Content (sucesso na operação sem conteúdo no
        // corpo)
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para excluir um usuário pelo ID.
     * Rota: DELETE http://localhost:8080/usuario/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Solicita a exclusão do registro à camada de serviço
        this.userService.delete(id);
        // Retorna HTTP status 204 No Content
        return ResponseEntity.noContent().build();
    }
}