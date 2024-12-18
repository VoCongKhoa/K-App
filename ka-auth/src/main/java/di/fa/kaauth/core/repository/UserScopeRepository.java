package di.fa.kaauth.core.repository;

import di.fa.kaauth.core.entity.UserScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserScopeRepository extends JpaRepository<UserScopeEntity, UUID> {
}
