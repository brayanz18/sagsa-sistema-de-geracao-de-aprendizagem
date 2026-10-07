package com.brayan_guilherme.models;

// Mapeamento Objeto-Relacional (JPA / Jakarta Persistence)
import jakarta.persistence.Column; // Mapeia atributos para colunas específicas da tabela no banco
import jakarta.persistence.Entity; // Define a classe como uma entidade persistível no banco de dados
import jakarta.persistence.GeneratedValue; // Define a estratégia de geração automática de valores para a chave primária
import jakarta.persistence.GenerationType; // Enum com as estratégias de geração do ID (ex: IDENTITY / Autoincremento)
import jakarta.persistence.Id; // Marca o atributo como a chave primária (PK) da tabela
import jakarta.persistence.JoinColumn; // Define a coluna de chave estrangeira (FK) que conecta as tabelas
import jakarta.persistence.ManyToOne; // Mapeia o relacionamento de muitos-para-um (N:1) entre entidades
import jakarta.persistence.Table; // Define o nome e propriedades da tabela no banco de dados

// Validações de Dados (Jakarta Validation)
import jakarta.validation.constraints.NotBlank; // Garante que a String não seja nula, nem vazia, nem apenas espaços
import jakarta.validation.constraints.NotNull; // Garante que o atributo não receba valores nulos
import jakarta.validation.constraints.Size; // Restringe o tamanho mínimo e máximo de caracteres de uma String

// Geração Automática de Código Boilerplate (Lombok)
import lombok.AllArgsConstructor; // Gera um construtor com argumentos para todos os campos
import lombok.EqualsAndHashCode; // Gera os métodos equals() e hashCode() automaticamente
import lombok.Getter; // Gera todos os métodos Getters da classe
import lombok.NoArgsConstructor; // Gera o construtor padrão sem parâmetros (exigido pelo JPA)
import lombok.Setter; // Gera todos os métodos Setters da classe

// Utilitários Padrão do Java
import java.util.Objects; // Utilitário Java para operações com objetos (ex: checagem de nulos)

// Anotações do JPA para Mapeamento Objeto-Relacional (ORM)
@Entity // Declara a classe como uma entidade gerenciada pelo JPA
@Table(name = Sapz.TABLE_NAME) // Define o nome da tabela correspondente na base de dados ("task")

// Anotações do Lombok para geração automática de métodos utilitários
@AllArgsConstructor // Gera o construtor com todos os campos da classe
@NoArgsConstructor // Gera o construtor padrão sem argumentos
@Getter // Gera automaticamente os métodos Getters para todos os atributos
@Setter // Gera automaticamente os métodos Setters para todos os atributos
@EqualsAndHashCode // Gera os métodos equals() e hashCode() com base nos campos da classe
public class Sapz {

    // Constante que armazena o nome oficial da tabela no banco de dados
    public static final String TABLE_NAME = "task";

    // Mapeamento da chave primária (ID)
    @Id // Identifica o atributo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o autoincremento gerenciado pelo banco de dados
    @Column(name = "id", unique = true) // Mapeia a coluna "id" definindo unicidade
    private Long id;

    // Relacionamento muitos-para-um (Muitas tarefas/Sapz pertencem a um único
    // utilizador)
    @ManyToOne // Indica a relação N:1 com a entidade User
    @JoinColumn(name = "user_id", nullable = false, updatable = false) // Mapeia a FK "user_id", obrigatória e que não
                                                                       // pode ser atualizada após ser criada
    private User user;

    // Mapeamento da descrição da tarefa com restrições e validações
    @Column(name = "description", length = 255, nullable = false) // Coluna não nula com limite máximo de 255 caracteres
    @NotNull // Impede valores nulos na validação
    @NotBlank // Impede strings vazias ou com apenas espaços em branco
    @Size(min = 1, max = 255) // Define a faixa de tamanho permitida (1 a 255 caracteres)
    private String description;

}