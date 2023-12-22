package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
