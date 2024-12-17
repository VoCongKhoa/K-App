package di.fa.kaauth.core.repository;

import di.fa.kaauth.core.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TemplateRepository extends JpaRepository<TemplateEntity, UUID> {

    Optional<TemplateEntity> findByTemplateCode(String templateCode);
}
