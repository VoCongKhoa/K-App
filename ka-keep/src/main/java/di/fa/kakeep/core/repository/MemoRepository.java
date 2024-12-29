package di.fa.kakeep.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemoRepository extends JpaRepository<MemoRepository, UUID> {


}
