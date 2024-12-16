package di.fa.kaauth.repository;

import di.fa.kaauth.entity.UserRelationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRelationsRepository extends JpaRepository<UserRelationsEntity, UUID> {
}
