package di.fa.kakeep.core.entity;

import di.fa.kakeep.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "memo_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memo_id")
    private UUID memoId;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_pinned")
    private Boolean inPinned;

    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY)
    private Set<MemoTagRelationEntity> memoTagRelations;

    @Column(name = "key_cloak_user_id")
    private String keycloakUserId;
}
