package com.brayan_guilherme.controllers;

// Importação da entidade Sapz que representa o modelo de dados
import com.brayan_guilherme.models.Sapz;
// Importação do serviço responsável pela regra de negócio da entidade Sapz
import com.brayan_guilherme.services.SapzService;
// Injeção de dependências do Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
// Utilitário para estruturar respostas HTTP completas (status, cabeçalhos e corpo)
import org.springframework.http.ResponseEntity;
// Anotações para mapeamento de endpoints e binding de parâmetros da requisição
import org.springframework.web.bind.annotation.*;
// Utilitário para construir a URI do novo recurso gerado na resposta HTTP
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controladora REST responsável por expor os endpoints da rota /sapz
 */
@RestController // Define esta classe como um controlador REST que retorna dados estruturados
                // (JSON)
@RequestMapping("/sapz") // Define a rota base /sapz para todas as requisições mapeadas nesta classe
public class SapzController {

    @Autowired // Injeta a instância da camada de serviço SapzService gerenciada pelo Spring
    private SapzService sapzService;

    /**
     * Endpoint para consultar um registro Sapz individual pelo seu ID.
     * Rota: GET http://localhost:8080/sapz/{id}
     *
     * @param id Identificador do registro Sapz
     * @return Objeto Sapz encapsulado em um ResponseEntity com HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sapz> findById(@PathVariable Integer id) {
        // Executa a consulta na camada de serviço utilizando o ID vindo da URL
        Sapz obj = this.sapzService.findById(id);
        // Retorna o status HTTP 200 OK com o objeto localizado no corpo da resposta
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Endpoint para listar todos os registros Sapz associados a um determinado
     * usuário.
     * Rota: GET http://localhost:8080/sapz/user/{userId}
     *
     * @param userId Identificador do usuário proprietário dos registros
     * @return Lista de registros Sapz com HTTP 200 (OK)
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Sapz>> findAllByUserId(@PathVariable Integer userId) {
        // Solicita ao serviço a lista de registros vinculados ao ID do usuário
        List<Sapz> list = this.sapzService.findAllByUserId(userId);
        // Retorna o status HTTP 200 OK com a lista no corpo da resposta
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para cadastrar um novo registro Sapz.
     * Rota: POST http://localhost:8080/sapz
     *
     * @param obj Objeto Sapz serializado vindo no corpo do JSON
     * @return Resposta HTTP 201 (Created) com o cabeçalho 'Location' apontando para
     *         a URI do novo recurso
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Sapz obj) {
        // Persiste o novo objeto na base de dados através do serviço
        this.sapzService.create(obj);
        // Constrói a URI dinamicamente para o novo recurso (ex: /sapz/12)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        // Retorna o status HTTP 201 Created com a URI no cabeçalho
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para atualizar um registro Sapz existente.
     * Rota: PUT http://localhost:8080/sapz/{id}
     *
     * @param obj Objeto contendo os dados atualizados enviados no corpo do JSON
     * @param id  Identificador do registro que será atualizado
     * @return Resposta HTTP 204 (No Content) indicando sucesso na alteração
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Sapz obj, @PathVariable Integer id) {
        // Garante que o ID recebido na URL seja atribuído ao objeto antes de atualizar
        obj.setId(id);
        // Invoca a camada de serviço para atualizar as informações no banco
        this.sapzService.update(obj);
        // Retorna HTTP 204 No Content (sucesso na operação sem conteúdo no corpo)
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para excluir um registro Sapz pelo seu ID.
     * Rota: DELETE http://localhost:8080/sapz/{id}
     *
     * @param id Identificador do registro a ser removido
     * @return Resposta HTTP 204 (No Content) indicando sucesso na exclusão
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        // Executa a remoção do registro na camada de serviço
        this.sapzService.delete(id);
        // Retorna HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }
}