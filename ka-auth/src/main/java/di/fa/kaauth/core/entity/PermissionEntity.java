package di.fa.kaauth.core.entity;

import di.fa.kaauth.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "permission_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity extends Auditable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private UUID permissionId;

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

    @Column(name = "permission_code", nullable = false)
    private String permissionCode;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "is_system")
    private Boolean isSystem;

    @Column(name = "module_id")
    private UUID moduleId;
}
