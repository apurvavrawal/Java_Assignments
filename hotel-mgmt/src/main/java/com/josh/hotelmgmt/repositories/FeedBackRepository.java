package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
}
