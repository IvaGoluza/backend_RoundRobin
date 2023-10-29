package com.web2.RoundRobin.repository;

import com.web2.RoundRobin.model.Match;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    String GET_UNIQUE_KEYS_QUERY = """
            SELECT count(*)
            FROM pg_constraint
            WHERE conrelid = CAST('match' AS regclass)
              AND contype = 'u'
            """;

    @Query(value = GET_UNIQUE_KEYS_QUERY, nativeQuery = true)
    Integer getUniqueKeys();


    String REMOVE_CONSTRAIN_QUERY = """
                DO $$
                BEGIN
                  EXECUTE (
                    'ALTER TABLE "match" DROP CONSTRAINT ' ||
                    (SELECT conname
                     FROM pg_constraint
                     WHERE conrelid = CAST('match' AS regclass)
                       AND contype = 'u'
                     LIMIT 1)
                  );
                END $$;
            """;

    @Modifying
    @Transactional
    @Query(value = REMOVE_CONSTRAIN_QUERY, nativeQuery = true)
    void removeConstraint();
}
