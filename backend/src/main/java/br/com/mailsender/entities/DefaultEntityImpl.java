package br.com.mailsender.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.internal.util.Objects;

import java.util.Date;

@Data
@MappedSuperclass
public class DefaultEntityImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, length = 38, nullable = false, unique = true)
    private String oid;

    @Column(nullable = false)
    private Date creationDate = generateCreationDate();

    private Date generateCreationDate() {
        return Objects.firstNonNull(this.creationDate, new Date());
    }

}
