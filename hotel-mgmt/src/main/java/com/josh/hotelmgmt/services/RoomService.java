package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;
import com.josh.hotelmgmt.entities.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Room getRoomByRoomId(long roomId);

    void createNewRoom(Room room);

    void deleteRoom(Long roomId) throws RoomNotFoundException;
}
