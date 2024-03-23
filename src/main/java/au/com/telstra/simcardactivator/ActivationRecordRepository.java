package au.com.telstra.simcardactivator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationRecordRepository extends JpaRepository<ActivationRecord, Long> {
    // Optionally, add custom query methods here if needed
}
