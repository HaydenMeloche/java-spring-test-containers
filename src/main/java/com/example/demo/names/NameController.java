package com.example.demo.names;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NameController {

    private final NameRepository nameRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getName(@PathVariable Integer id) {

        final Optional<Name> byId = nameRepository.findById(id);

        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveName(@RequestBody SubmitNameDto submitNameDto) {
        Name name = Name.valueOf(submitNameDto.getName());

        return ResponseEntity.ok(nameRepository.save(name));
    }
}
