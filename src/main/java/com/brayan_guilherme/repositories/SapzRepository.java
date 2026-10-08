package com.brayan_guilherme.repositories;

// Importação da entidade Sapz que este repositório irá gerir
import com.brayan_guilherme.models.Sapz;
// Interface base do Spring Data JPA que fornece métodos de CRUD pré-implementados (save, findById, delete, etc.)
import org.springframework.data.jpa.repository.JpaRepository;
// Anotação que indica que esta interface é um componente de acesso a dados (repositório)
import org.springframework.stereotype.Repository;

// Importação da estrutura de dados List para retornar conjuntos de registos
import java.util.List;

/**
 * Interface de repositório responsável pelas operações de persistência da
 * entidade Sapz.
 */
@Repository // Nota: Esta anotação é opcional, pois o Spring Data JPA regista
            // automaticamente interfaces que estendem JpaRepository
public interface SapzRepository extends JpaRepository<Sapz, Long> {

    /**
     * Procura e retorna uma lista de registos Sapz associados a um utilizador
     * específico.
     * 
     * O Spring Data JPA interpreta o nome do método ("findByUser_Id") e gera
     * automaticamente a consulta SQL correspondente (Derived Query):
     * SELECT * FROM sapz WHERE user_id = ?
     * 
     * @param id Identificador do utilizador (User)
     * @return Lista contendo os registos Sapz associados ao ID informado
     */
    List<Sapz> findByUser_Id(Long id);
}