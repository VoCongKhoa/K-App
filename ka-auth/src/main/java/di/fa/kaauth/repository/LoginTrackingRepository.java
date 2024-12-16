package di.fa.kaauth.repository;

import di.fa.kaauth.entity.LoginTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginTrackingRepository extends JpaRepository<LoginTrackingEntity, UUID> {

    Optional<LoginTrackingEntity> findByUserIdAndModuleId(UUID userId, UUID moduleId);

}
