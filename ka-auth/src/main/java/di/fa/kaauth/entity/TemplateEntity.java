package di.fa.kaauth.entity;

import jakarta.persistence.*;
import di.fa.kaauth.entity.base.Auditable;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "template_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateEntity extends Auditable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "template_id")
    private UUID templateId;

    @Column(name = "template_name", nullable = false)
    private String templateName;

    @Column(name = "template_code", nullable = false)
    private String templateCode;

    @Column(name = "template_html")
    private String templateHtml;

    @Column(name = "template_type")
    private String templateType;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;
}
