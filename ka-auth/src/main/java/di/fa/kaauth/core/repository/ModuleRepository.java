package di.fa.kaauth.core.repository;

import di.fa.kaauth.core.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
    Optional<ModuleEntity> findByModuleCode(String subDomain);
}
