package di.fa.kaauth.core.entity;

import di.fa.kaauth.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "module_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleEntity extends Auditable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "module_id")
    private UUID moduleId;

    @Column(name = "module_name", nullable = false)
    private String moduleName;

    @Column(name = "module_code", nullable = false)
    private String moduleCode;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "timezone_code")
    private String timezoneCode;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<PermissionEntity> permissions;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private Set<LoginTrackingEntity> loginTrackings;
}
