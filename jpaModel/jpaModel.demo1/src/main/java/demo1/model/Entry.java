package demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Entry.
 */
@Data
@Entity
@Table(name = "entry")
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("entries")
    private Blog blog;

    @ManyToMany
    @JoinTable
    private Set<Tag> tags = new HashSet<>();

}
