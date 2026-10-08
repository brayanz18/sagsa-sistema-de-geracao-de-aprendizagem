package com.brayan_guilherme.models;

// Importações do Jakarta Persistence para o mapeamento relacional (JPA/Hibernate)
import jakarta.persistence.*;

/**
 * List<Sapz> findByUser_Id(Integer id);
 * Entidade Java 'User' vinculada à tabela oficial 'usuario' do banco de dados
 * sagsa_db
 */
@Entity // Indica ao JPA que esta classe é uma tabela do banco de dados
@Table(name = "usuario") // Vincula a classe à tabela 'usuario' criada no servidor
public class User {

    @Id // Marca o atributo 'id' como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura como AUTO_INCREMENT no MySQL
    @Column(name = "id_matricula") // Mapeia o atributo 'id' para a coluna física 'id_matricula'
    private Integer id;

    private String nome; // Mapeia a coluna 'nome'
    private String email; // Mapeia a coluna 'email'

    @Column(name = "senha_hash") // Mapeia o atributo 'senhaHash' para a coluna física 'senha_hash'
    private String senhaHash;

    private String perfil; // Mapeia a coluna 'perfil'

    // Construtor sem argumentos (obrigatório pelo JPA)
    public User() {
    }

    // Construtor parametrizado para facilitar a criação manual de objetos
    public User(Integer id, String nome, String email, String senhaHash, String perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
    }

    // --- Getters e Setters (necessários para acesso aos atributos pela aplicação)
    // ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}