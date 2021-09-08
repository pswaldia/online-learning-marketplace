package com.example.demodocker.controllers;

import com.example.demodocker.dto.ButtonDto;
import com.example.demodocker.dto.CardDto;
import com.example.demodocker.dto.CardResponseDto;
import com.example.demodocker.dto.DialogFlowCardReponseDto;
import com.example.demodocker.entity.Course;
import com.example.demodocker.services.CourseService;
import com.example.demodocker.services.DialogFlowWebhookResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private DialogFlowWebhookResponseService dialogFlowWebhookResponseService;

    @GetMapping("/courses/")
    public ResponseEntity listCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.readCourse(pageable));
    }

    @GetMapping("courses/filter")
    public ResponseEntity filterCoursesByKeyword(@RequestParam("query") String query, Pageable pageable) {
        return ResponseEntity.ok(courseService.filterCourse(query, pageable));
    }

    @PostMapping("/courses/new")
    public ResponseEntity<Course> create(@RequestBody Course course) {
        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/courses/{id}").buildAndExpand(course.getId()).toUriString();
        course.setUrl(baseUrl);
        return new ResponseEntity<Course>(courseService.save(course), HttpStatus.CREATED);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getById(@PathVariable Integer id) {
        return new ResponseEntity<Course>(courseService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/courses/dialogFlow")
    public ResponseEntity<?> dialogFlowResponseCourseCard(@RequestBody String StringRequest) throws IOException {
        Course course = courseService.getById(3);
        DialogFlowCardReponseDto dialogFlowCardReponseDto = new DialogFlowCardReponseDto();
        ButtonDto buttonDto = new ButtonDto();
        buttonDto.setText("Click to get more details");
        buttonDto.setPostback("https://www.udemy.com/course/spring-web-services-tutorial/");
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setButtons(List.of(buttonDto));
        cardResponseDto.setTitle(course.getName());
        cardResponseDto.setSubtitle(course.getFullDescription());
        cardResponseDto.setImageUri("http://imgur.com/a/vCQ6KKi");
        CardDto cardDto = new CardDto();
        cardDto.setCard(cardResponseDto);
        dialogFlowCardReponseDto.setFulfillmentMessages(List.of(cardDto)); // final : to be returned.....................
        return new ResponseEntity<DialogFlowCardReponseDto>(dialogFlowCardReponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/courses/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.delete(id);
    }

    @PutMapping("/courses/update")
    public ResponseEntity<Course> update(@RequestBody Course course) {
        return new ResponseEntity<Course>(courseService.update(course), HttpStatus.OK);
    }


}


