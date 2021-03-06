package dev.kirillzhelt.cliqueify.controller;

import dev.kirillzhelt.cliqueify.dto.RoomCreationParamsDTO;
import dev.kirillzhelt.cliqueify.model.PublicityType;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.model.User;
import dev.kirillzhelt.cliqueify.service.RoomService;
import dev.kirillzhelt.cliqueify.service.UserService;
import dev.kirillzhelt.cliqueify.validator.ExpiryDateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Controller
class RoomController {

    private final RoomService roomService;
    private final UserService userService;

    private final ModelMapper modelMapper;
    private final DateTimeFormatter dateFormatter;

    public RoomController(RoomService roomService, ModelMapper modelMapper, DateTimeFormatter dateFormatter,
                          UserService userService) {
        this.roomService = roomService;
        this.userService = userService;

        this.modelMapper = modelMapper;
        this.dateFormatter = dateFormatter;
    }

    @GetMapping("/create-room")
    public String getRoomCreationForm(Model model) {
        this.setupRoomCreationForm(model);
        model.addAttribute("roomParams", new RoomCreationParamsDTO());
        return "create_room";
    }

    @PostMapping("/create-room")
    public String createRoom(@Valid @ModelAttribute(name = "roomParams") RoomCreationParamsDTO roomParams, BindingResult result, Model model,
                             Principal principal) {
        if (result.hasErrors()) {
            this.setupRoomCreationForm(model);
            return "create_room";
        }

        Room room = this.modelMapper.map(roomParams, Room.class);
        User user = this.getUserFromPrincipal(principal);
        room.setOwner(user);
        Room createdRoom = this.roomService.createRoom(room);

        return String.format("redirect:/rooms/%d", createdRoom.getId());
    }

    private void setupRoomCreationForm(Model model) {
        String minExpiryDate = ExpiryDateValidator.getMinExpiryDate().format(this.dateFormatter);
        model.addAttribute("minExpiryDate", minExpiryDate);
    }

    @GetMapping("/rooms")
    public String rooms(Model model, Principal principal) {
        User user = this.getUserFromPrincipal(principal);
        model.addAttribute("rooms", user.getLastExpiringRooms());
        return "rooms";
    }

    @GetMapping("/rooms/{roomId}")
    public String room(@PathVariable long roomId, Model model, Principal principal) {
        Optional<Room> roomOptional = this.roomService.getRoomById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();

            if (room.getPublicityType() == PublicityType.PRIVATE && room.getOwner() != this.getUserFromPrincipal(principal)) {
                model.addAttribute("errorMessage", "You don't have access to this room");
                return "error";
            }

            model.addAttribute("room", room);
            return "room";
        } else {
            model.addAttribute("errorMessage", "Room not found");
            return "error";
        }
    }

    @GetMapping("/rooms/link/{token}")
    public String roomByToken(@PathVariable String token, Model model) {
        Optional<Room> roomOptional = this.roomService.getRoomByToken(token);
        if (roomOptional.isPresent()) {
            model.addAttribute("room", roomOptional.get());
            return "room";
        } else {
            model.addAttribute("errorMessage", "Invalid link");
            return "error";
        }
    }

    @GetMapping("/browse-rooms")
    public String browseRooms(Model model) {
        Set<Room> rooms = this.roomService.getPublicRooms();
        model.addAttribute("rooms", rooms);
        return "browse_rooms";
    }

    private User getUserFromPrincipal(Principal principal) {
        return this.userService.findUserByUsername(principal.getName()).orElseThrow();
    }
}
