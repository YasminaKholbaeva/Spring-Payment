package com.example.payment.conroller;

import com.example.payment.dto.EventDto;
import com.example.payment.model.Event;
import com.example.payment.repository.EventRepository;
import com.example.payment.repository.ParticipantRepository;
import com.example.payment.service.impl.EventServiceImpl;
import com.example.payment.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private EventServiceImpl eventService;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto dto) {
        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Integer id, @RequestBody EventDto dto) {
        return ResponseEntity.ok(eventService.updateEvent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(Map.of("result", 0, "comment", "Мероприятие было удалено."));
    }

    @GetMapping("allEvents")
    public ResponseEntity<List<EventDto>> getAllEvents(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(eventService.getAllEvents());
    }


    //todo CRUD



}
