package di.fa.kanotification.entity;

import di.fa.kanotification.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "noti_tracking_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotiTrackingEntity extends Auditable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noti_tracking_id")
    private UUID templateId;

    @Column(name = "notiType", nullable = false)
    private String notiType;

    @Column(name = "template_code", nullable = false)
    private String templateCode;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "error_msg")
    private String errorMsg;
}
