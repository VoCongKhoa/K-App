package di.fa.kadevtools.core.entity;

import di.fa.kadevtools.core.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dev_tools_entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevToolsEntity extends Auditable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dev_tools_id")
    private UUID devToolsId;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @Column(name = "is_need_file")
    private Boolean isNeedFile;

    @Column(name = "demo_file_uri")
    private String demoFileUri;

    @OneToMany(mappedBy = "devTools", fetch = FetchType.LAZY)
    private Set<DevToolsTagEntity> devToolsTags;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private UserEntity user;
//
//    @ManyToOne
//    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
//    private ModuleEntity module;
}
