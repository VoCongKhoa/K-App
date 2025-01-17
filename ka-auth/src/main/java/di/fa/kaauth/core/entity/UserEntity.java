package di.fa.kaauth.core.entity;

import jakarta.persistence.*;
import di.fa.kaauth.core.entity.base.Auditable;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_entity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    private String status;

    @Column(name = "active_date")
    private Timestamp activeDate;

    @Column(name = "key_cloak_user_id")
    private String keycloakUserId;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    private ModuleEntity module;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserScopeEntity> userScopes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<LoginTrackingEntity> loginTrackings;
}
