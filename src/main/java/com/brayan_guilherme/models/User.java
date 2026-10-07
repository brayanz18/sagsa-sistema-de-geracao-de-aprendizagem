package com.brayan_guilherme.models;

// Serialização / Deserialização JSON (Jackson)
import com.fasterxml.jackson.annotation.JsonProperty; // Controla regras de leitura/escrita de atributos no JSON

// Mapeamento Objeto-Relacional (JPA / Jakarta Persistence)
import jakarta.persistence.Column; // Mapeia atributos para colunas específicas da tabela no banco
import jakarta.persistence.Entity; // Define a classe como uma entidade persistível no banco de dados
import jakarta.persistence.GeneratedValue; // Define a estratégia de geração automática de valores para a chave primária
import jakarta.persistence.GenerationType; // Enum com as estratégias de geração do ID (ex: IDENTITY / Autoincremento)
import jakarta.persistence.Id; // Marca o atributo como a chave primária (PK) da tabela
import jakarta.persistence.OneToMany; // Mapeia o relacionamento de um-para-muitos (1:N) entre tabelas
import jakarta.persistence.Table; // Define o nome e propriedades da tabela no banco de dados

// Validações de Dados (Jakarta Validation)
import jakarta.validation.constraints.NotBlank; // Garante que a String não seja nula, nem vazia, nem apenas espaços
import jakarta.validation.constraints.NotNull; // Garante que o atributo não receba valores nulos
import jakarta.validation.constraints.Size; // Restringe o tamanho mínimo e máximo de caracteres de uma String

// Geração Automática de Código Boilerplate (Lombok)
import lombok.AllArgsConstructor; // Gera um construtor com argumentos para todos os campos
import lombok.EqualsAndHashCode; // Gera os métodos equals() e hashCode() automaticamente
import lombok.Getter; // Gera todos os métodos Getters da classe
import lombok.NoArgsConstructor; // Gera o construtor padrão sem parâmetros
import lombok.Setter; // Gera todos os métodos Setters da classe

// Estuturas de Dados do Java Utilitários
import java.util.ArrayList; // Implementação de lista dinâmica baseada em array
import java.util.List; // Interface padrão do Java para coleções do tipo lista
import java.util.Objects; // Utilitário Java para operações com objetos (ex: checagem de nulos)

// Anotações do Lombok para geração automática de métodos utilitários
@AllArgsConstructor // Gera o construtor com todos os campos da classe
@NoArgsConstructor // Gera o construtor padrão sem argumentos (exigido pelo JPA)
@Getter // Gera automaticamente os métodos Getters para todos os atributos
@Setter // Gera automaticamente os métodos Setters para todos os atributos
@EqualsAndHashCode // Gera os métodos equals() e hashCode() com base nos campos da classe

// Anotações do JPA para Mapeamento Objeto-Relacional (ORM)
@Entity // Declara a classe como uma entidade gerenciada pelo JPA
@Table(name = User.TABLE_NAME) // Define o nome da tabela correspondente na base de dados
public class User {

    // Interfaces marcadoras para grupos de validação do Spring Validation
    public interface CreateUser { // Usado para validar campos específicos na criação do utilizador
    }

    public interface UpdateUser { // Usado para validar campos específicos na atualização do utilizador
    }

    // Constante que armazena o nome oficial da tabela no banco de dados
    public static final String TABLE_NAME = "user";

    // Mapeamento da chave primária (ID)
    @Id // Identifica o atributo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o autoincremento gerenciado pelo banco de dados
    @Column(name = "id", unique = true) // Mapeia a coluna "id" definindo unicidade
    private Long id;

    // Mapeamento do nome do utilizador com restrições e validações
    @Column(name = "username", length = 100, nullable = false, unique = true) // Coluna não nula, única e limite de 100
                                                                              // caracteres
    @NotNull // Impede valores nulos na validação
    @NotBlank // Impede strings vazias ou apenas com espaços em branco
    @Size(min = 2, max = 100) // Define a faixa de tamanho permitida (2 a 100 caracteres)
    private String username;

    // Mapeamento da palavra-passe/senha com controle de visibilidade em JSON
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Permite receber a senha via requisição (JSON->Objeto), mas
                                                           // nunca a expõe na resposta (Objeto->JSON)
    @Column(name = "password", length = 60, nullable = false) // Coluna não nula com tamanho 60 (ideal para armazenar
                                                              // hashes como BCrypt)
    @NotNull // Impede valores nulos na validação
    @NotBlank // Impede strings vazias ou com apenas espaços em branco
    @Size(min = 8, max = 60) // Exige tamanho mínimo de 8 caracteres para a palavra-passe
    private String password;

    // Relacionamento um-para-muitos (Um utilizador possui várias tarefas/Sapz)
    @OneToMany(mappedBy = "user") // Indica o relacionamento 1:N e aponta que o atributo "user" na classe Sapz é o
                                  // dono da relação
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Oculta a lista de tarefas ao serializar o User para evitar
                                                           // recursão infinita (loop de JSON)
    private List<Sapz> tasks = new ArrayList<>();

}