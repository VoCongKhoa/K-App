package di.fa.kaauth.core.entity;

import di.fa.kaauth.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_scope_entity")
public class UserScopeEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_scope_id")
    private UUID userScopeId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
    private PermissionEntity permission;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private RoleEntity role;
}
