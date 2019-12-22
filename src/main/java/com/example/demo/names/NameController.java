package com.example.demo.names;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveName(@RequestBody SubmitNameDto submitNameDto, UriComponentsBuilder b) {
        Name name = Name.valueOf(submitNameDto.getName());

        final Name savedValue = nameRepository.save(name);

        UriComponents uriComponents = b.path("/api/{id}").buildAndExpand(savedValue.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<Name>(savedValue, headers, HttpStatus.CREATED);
    }
}
