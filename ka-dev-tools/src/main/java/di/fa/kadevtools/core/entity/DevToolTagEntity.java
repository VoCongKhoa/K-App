package di.fa.kadevtools.core.entity;

import di.fa.kadevtools.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dev_tool_tag_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevToolTagEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dev_tool_tag_id")
    private UUID devToolTagId;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "dev_tool_id", referencedColumnName = "dev_tool_id")
    private DevToolEntity devTool;

    @Column(name = "key_cloak_user_id")
    private String keycloakUserId;
}
