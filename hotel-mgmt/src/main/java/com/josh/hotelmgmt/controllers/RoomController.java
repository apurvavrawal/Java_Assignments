package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    // Provides details of all Rooms
    @GetMapping("/")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    // Provides details based on roomId
    @GetMapping("/{roomId}")
    public Room getRoomByRoomId(@PathVariable long roomId){
        return roomService.getRoomByRoomId(roomId);
    }

    // Create new Room
    /**
     *
     * @param room
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<String> createNewRoom(@RequestBody Room room){
        roomService.createNewRoom(room);
        return new ResponseEntity<>("Room created successfully", HttpStatus.CREATED);
    }

    // Delete Room based on Room Number
    /**
     *
     * @param roomId
     * @return
     */
    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId) {
        try {
            roomService.deleteRoom(roomId);
            return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
        } catch (RoomNotFoundException e) {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }
    }

}
