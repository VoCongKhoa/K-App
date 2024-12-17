package di.fa.kaauth.core.repository;

import di.fa.kaauth.core.entity.UserRelationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRelationsRepository extends JpaRepository<UserRelationsEntity, UUID> {
}
