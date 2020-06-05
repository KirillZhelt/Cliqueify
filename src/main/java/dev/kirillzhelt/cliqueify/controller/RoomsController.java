package dev.kirillzhelt.cliqueify.controller;

import dev.kirillzhelt.cliqueify.dto.RoomCreationParamsDTO;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.service.RoomsService;
import dev.kirillzhelt.cliqueify.validator.ExpiryDateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
class RoomsController {

    private final RoomsService roomsService;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter dateFormatter;

    public RoomsController(RoomsService roomsService, ModelMapper modelMapper, DateTimeFormatter dateFormatter) {
        this.roomsService = roomsService;
        this.modelMapper = modelMapper;
        this.dateFormatter = dateFormatter;
    }

    @GetMapping("/")
    public String getRoomCreationForm(Model model) {
        this.setupRoomCreationForm(model);
        model.addAttribute("roomParams", new RoomCreationParamsDTO());
        return "create_room";
    }

    @PostMapping("/")
    public String createRoom(@Valid @ModelAttribute(name = "roomParams") RoomCreationParamsDTO roomParams, BindingResult result, Model model) {
        if (result.hasErrors()) {
            this.setupRoomCreationForm(model);
            return "create_room";
        }

        Room room = this.modelMapper.map(roomParams, Room.class);
        Room createdRoom = this.roomsService.createRoom(room);

        return String.format("redirect:/rooms/%d", createdRoom.getId());
    }

    private void setupRoomCreationForm(Model model) {
        String minExpiryDate = ExpiryDateValidator.getMinExpiryDate().format(this.dateFormatter);
        model.addAttribute("minExpiryDate", minExpiryDate);
    }

    @GetMapping("/rooms/{roomId}")
    public String room(@PathVariable long roomId, Model model) {
        Optional<Room> room = this.roomsService.getRoomById(roomId);

        if (room.isPresent()) {
            model.addAttribute("room", room.get());
            return "room";
        }

        return "error";
    }
}
