package di.fa.kadevtools.core.entity;

import di.fa.kadevtools.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dev_tools_tag_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevToolsTagEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dev_tools_tag_id")
    private UUID devToolsTagId;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "dev_tools_id", referencedColumnName = "dev_tools_id")
    private DevToolsEntity devTools;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private UserEntity user;
//
//    @ManyToOne
//    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
//    private ModuleEntity module;
}
