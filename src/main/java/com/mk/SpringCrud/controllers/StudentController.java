package com.mk.SpringCrud.controllers;

import com.google.gson.Gson;
import com.mk.SpringCrud.models.Student;
import com.mk.SpringCrud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentService studentService;

    private String TAG = this.getClass().getSimpleName();

    /*
    REST Operations
    ---------------
    GET /api/v1/users
    POST /api/v1/users
    GET /api/v1/users/{userId}
    PUT /api/v1/users/{userId}
    DELETE /api/v1/users/{userId}

	https://medium.com/@salisuwy
	https://spring.io/guides/gs/accessing-data-mysql/
	https://www.onlinetutorialspoint.com/spring-boot/spring-boot-hibernate-integration-example.html
	
	https://hellokoding.com/full-stack-crud-web-app-and-restful-apis-web-services-example-with-spring-boot-jpa-hibernate-mysql-vuejs-and-docker/
	
	MySQL: https://www.youtube.com/watch?v=GIRcpjg-3Eg
	Spring Initializr: https://www.youtube.com/watch?v=_ckyhzJiTEk
    */

    @RequestMapping(value = "/index")
    public String index() {
        return "hello world!";
    }

    //POST: http://localhost:8080/api/v1/add
    //body: {"id": 3, "name": "Ann", "age": 544}
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Student add(@RequestBody Student student) {
        System.out.println(TAG + " : " + new Gson().toJson(student));
        return studentService.add(student);
    }

    //GET: http://localhost:8080/api/v1/students
    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Student> getAll() {
        // This returns a JSON or XML with the users
        return studentService.getAll();
    }

    //GET: http://localhost:8080/api/v1/student/1
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getById(@PathVariable String id) {
        int mId = Integer.parseInt(id);
        return studentService.getById(mId);
    }

    //PUT: http://localhost:8080/api/v1/student/update/1
    //body: {"id": 3, "name": "Ann", "age": 544}
    @RequestMapping(value = "/student/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student updateById(@PathVariable String id, @RequestBody Map<String, String> body) {
        int mId = Integer.parseInt(id);
        String name = body.get("name");
        int age = Integer.parseInt(body.get("age"));
        return studentService.updateById(mId, name, age);
    }

    //DELETE: http://localhost:8080/api/v1/student/delete/1
    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteById(@PathVariable String id) {
        return studentService.deleteById(Integer.parseInt(id));
    }

    //DELETE: http://localhost:8080/api/v1/students/delete
    @RequestMapping(value = "/students/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Student> deleteAll() {
        return studentService.deleteAll();
    }

    @PostMapping(value = "/updatePhoto" )
    public String updateUserPhoto(@RequestPart(name = "img") MultipartFile img) {
        System.out.println("Request  update photo "+ img.getOriginalFilename());
        return "OK";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("files") MultipartFile[] files) {
        String uploadDirectory = "/uploads/";
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
        return fileNames.toString();
    }

    /*@RequestMapping("/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
        String uploadDirectory = "/uploads/";
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
        return "uploadstatusview";
    }*/

}
