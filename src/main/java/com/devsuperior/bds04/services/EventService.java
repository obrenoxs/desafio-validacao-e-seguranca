package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        event.setDate(dto.getDate());
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não existe"));

        event.setCity(city);

        event = eventRepository.save(event);
        return new EventDTO(event);
    }
}
