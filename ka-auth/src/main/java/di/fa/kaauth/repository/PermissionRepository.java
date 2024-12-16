package di.fa.kaauth.repository;

import di.fa.kaauth.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {
}
