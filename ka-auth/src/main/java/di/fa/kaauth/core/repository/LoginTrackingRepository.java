package di.fa.kaauth.core.repository;

import di.fa.kaauth.core.entity.LoginTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginTrackingRepository extends JpaRepository<LoginTrackingEntity, UUID> {

    Optional<LoginTrackingEntity> findByUser_UserIdAndModule_ModuleId(UUID userId, UUID moduleId);

}
