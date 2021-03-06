package com.tucklets.app.db.repositories;

import com.tucklets.app.entities.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsletterRepository extends CrudRepository<Newsletter, Long>, JpaRepository<Newsletter, Long>{

    @Query("select n from Newsletter n where n.newsletterId = :newsletterId")
    Optional<Newsletter> fetchNewslettersByNewsletterId(@Param("newsletterId") Long newsletterId);

    @Query("select n from Newsletter n order by n.uploadDate desc")
    List<Newsletter> fetchAllAvailableNewsletters();

    @Query("select n from Newsletter n where n.filename = :filename")
    Optional<Newsletter> fetchNewsletterByFilename(@Param("filename") String filename);

    Optional<Newsletter> findFirstByOrderByUploadDateDesc();
}
