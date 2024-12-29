package di.fa.kadevtools.core.repository;

import di.fa.kadevtools.core.entity.DevToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DevToolRepository extends JpaRepository<DevToolEntity, UUID> {

}
