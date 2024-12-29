package di.fa.kakeep.core.entity;

import di.fa.kakeep.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "memo_tag_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoTagEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memo_tag_id")
    private UUID devToolTagId;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "memoTag", fetch = FetchType.LAZY)
    private Set<MemoTagRelationEntity> memoTagRelations;

    @Column(name = "key_cloak_user_id")
    private String keycloakUserId;
}
