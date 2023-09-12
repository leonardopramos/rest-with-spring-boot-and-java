package br.com.portifolioleonardo.restwithspringbootandjava.controller;

import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.portifolioleonardo.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import br.com.portifolioleonardo.restwithspringbootandjava.services.PersonServices;
import br.com.portifolioleonardo.restwithspringbootandjava.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints for managing People")
public class PersonController {

    @Autowired
    private PersonServices PersonServices;


    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person",
            description = "Finds a Person",
            tags = "People",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =@Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return PersonServices.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People",
            description = "Finds all People",
            tags = "People",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content ={
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                                    )}),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public List<PersonVO> findAll() {
        return PersonServices.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person",
            tags = "People",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =@Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVO create(@RequestBody PersonVO personVO) throws Exception {
        return PersonServices.createPerson(personVO);
    }

    @PostMapping(value = "/createV2", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person",
            tags = "People",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =@Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2) throws Exception {
        return PersonServices.createPersonV2(personVOV2);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update a Person",
            description = "Updated",
            tags = "People",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =@Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVO update(@RequestBody PersonVO personVO) throws Exception {
        return PersonServices.updatePerson(personVO);
    }
    @Operation(summary = "Delete a Person",
            description = "Delete a person",
            tags = "People",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content =@Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    @DeleteMapping(value = "{id}")

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        PersonServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}