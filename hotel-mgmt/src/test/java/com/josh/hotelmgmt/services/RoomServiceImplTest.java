package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.implementations.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomServiceImplTest {
    @InjectMocks
    private RoomServiceImpl roomServiceImpl;

    @Mock
    private RoomRepository roomRepository;

    @Test
    public void testGetAllRooms() {

        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(1,  1000.0));
        roomList.add(new Room(2, 1500.0));

        when(roomRepository.findAll()).thenReturn(roomList);

        List<Room> result = roomServiceImpl.getAllRooms();
        assertEquals(2, result.size());
        assertEquals(1500.0, result.get(1).getPricePerDay());
    }

    @Test
    public void testGetRoomByRoomId() {

        Room room = new Room(1, 1000.0);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Room result = roomServiceImpl.getRoomByRoomId(1L);
        assertNotNull(result);
        assertEquals(1000.0, result.getPricePerDay());
    }

    @Test
    public void testGetRoomByRoomIdNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());
        Room result = roomServiceImpl.getRoomByRoomId(1L);
        assertNull(result);
    }

    @Test
    public void testCreateNewRoom() {
        Room newRoom = new Room(3, 1200.0);
        assertDoesNotThrow(() -> {roomServiceImpl.createNewRoom(newRoom);});
        verify(roomRepository, times(1)).save(newRoom);
    }

    @Test
    public void testDeleteRoom() {
        Room existingRoom = new Room(1 , 1000.0);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(existingRoom));
        assertDoesNotThrow(() -> {roomServiceImpl.deleteRoom(1L);});
        verify(roomRepository, times(1)).delete(existingRoom);
    }

    @Test
    public void testDeleteRoomNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RoomNotFoundException.class, () -> {roomServiceImpl.deleteRoom(1L);});
        verify(roomRepository, never()).delete(any(Room.class));
    }
}
