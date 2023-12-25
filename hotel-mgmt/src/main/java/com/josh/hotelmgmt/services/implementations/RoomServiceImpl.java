package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomByRoomId(long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public void createNewRoom(Room room) {
        room.setPricePerDay(room.getPricePerDay());
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long roomId) throws RoomNotFoundException {
        Room room= roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException("Room is Not Available with RoomId: " + roomId));
        roomRepository.delete(room);
    }
}
