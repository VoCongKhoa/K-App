package di.fa.kaauth.core.entity;

import di.fa.kaauth.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "login_tracking_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginTrackingEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login_tracking_id")
    private UUID loginTrackingId;

    @Column(name = "status")
    private String status;

    @Column(name = "failed_count", columnDefinition = "int4 default 0")
    private Integer failedCount;

    @Column(name = "last_login_time")
    private Timestamp lastLoginTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
}
