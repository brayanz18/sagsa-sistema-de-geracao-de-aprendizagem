package com.brayan_guilherme.repositories;

// Importação do modelo de dados (Entidade User)
import com.brayan_guilherme.models.User;
// Interface base do Spring Data JPA que fornece métodos de manipulação de dados pré-implementados
import org.springframework.data.jpa.repository.JpaRepository;
// Anotação do Spring para marcar a interface como um componente da camada de acesso a dados (DAO)
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório responsável pela persistência de dados da entidade
 * User.
 * 
 * Ao herdar de JpaRepository<User, Long>, o Spring Data JPA gera
 * automaticamente
 * em tempo de execução a implementação das operações de banco para a entidade
 * User
 * cuja chave primária (id_matricula) é do tipo Long.
 */
@Repository // Indica que a interface é um repositório Spring (opcional quando se estende
            // JpaRepository)
public interface UserRepository extends JpaRepository<User, Long> {

    // O Spring Data JPA disponibiliza nativamente sem necessidade de implementar:
    // - save(User user) -> Insere ou atualiza um utilizador na base de dados
    // - findById(Long id) -> Procura um utilizador pela chave primária
    // - findAll() -> Lista todos os utilizadores cadastrados
    // - deleteById(Long id) -> Exclui um utilizador pelo seu ID
    // - existsById(Long id) -> Verifica se existe algum registo com o ID informado

}