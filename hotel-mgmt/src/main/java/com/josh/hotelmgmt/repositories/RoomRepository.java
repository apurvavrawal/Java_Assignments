package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    long countByRoomId(Long roomId);
}
