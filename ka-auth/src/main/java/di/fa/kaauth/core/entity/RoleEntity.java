package di.fa.kaauth.core.entity;

import di.fa.kaauth.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "role_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity extends Auditable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "module_id")
    private UUID moduleId;
}
