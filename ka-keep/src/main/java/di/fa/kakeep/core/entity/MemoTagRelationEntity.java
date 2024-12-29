package di.fa.kakeep.core.entity;

import di.fa.kakeep.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "memo_tag_relation_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoTagRelationEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memo_tag_relation_id")
    private UUID memoTagRelationId;

    @ManyToOne
    @JoinColumn(name = "memo_id", referencedColumnName = "memo_id")
    private MemoEntity memo;

    @ManyToOne
    @JoinColumn(name = "memo_tag_id", referencedColumnName = "memo_tag_id")
    private MemoTagEntity memoTag;
}
