package di.fa.kakeep.core.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Auditable<U extends Serializable> implements Serializable {

    @Column(name = "created_by", nullable = false)
    private U createdBy;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "modified_by", nullable = false)
    private U modifiedBy;

    @Column(name = "modified_date", nullable = false)
    private Timestamp modifiedDate;

    public void prePersist(U userId) {
        var currentTimeMillis = System.currentTimeMillis();
        var now = new Timestamp(currentTimeMillis);
        this.createdDate = now;
        this.createdBy = userId;
        this.modifiedDate = now;
        this.modifiedBy = userId;
    }

    public void preUpdate(U userId) {
        var currentTimeMillis = System.currentTimeMillis();
        this.modifiedDate = new Timestamp(currentTimeMillis);
        this.modifiedBy = userId;
    }
}
