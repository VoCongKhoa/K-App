package di.fa.kadevtools.core.entity;

import di.fa.kadevtools.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dev_tool_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevToolEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dev_tool_id")
    private UUID devToolId;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @Column(name = "is_need_file")
    private Boolean isNeedFile;

    @Column(name = "demo_file_uri")
    private String demoFileUri;

    @OneToMany(mappedBy = "devTools", fetch = FetchType.LAZY)
    private Set<DevToolTagEntity> devToolTags;

    @Column(name = "key_cloak_user_id")
    private String keycloakUserId;
}
