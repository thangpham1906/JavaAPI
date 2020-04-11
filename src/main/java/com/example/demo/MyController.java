package com.example.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {
    @Autowired
    public StudentRepository studentRepository;
    public BaseResponse baseResponse = new BaseResponse();



    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ResponseEntity<?> getAll(){
        List<Student> listStudent = (List<Student>) studentRepository.findAll();
        if(listStudent.size()==0){
            baseResponse.setStatus(1);
            baseResponse.setMessage("No Student");
            listStudent = new ArrayList<>();
            baseResponse.setResult(listStudent);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        }
        baseResponse.setStatus(1);
        baseResponse.setMessage("Success");
        baseResponse.setResult(listStudent);
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentRestRequest request){
        studentRepository.save(new Student(request.getName(),Integer.valueOf(request.getAge())));
        baseResponse.setStatus(1);
        baseResponse.setMessage("Add Student Success");
        return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
        if(studentRepository.existsById(id)){
            Student student = studentRepository.findById(id).get();
            baseResponse.setStatus(1);
            baseResponse.setMessage("Success");
            baseResponse.setResult(student);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("This student not exits");
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        }
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id){
        if(studentRepository.existsById(id)){
           studentRepository.deleteById(id);
            baseResponse.setStatus(1);
            baseResponse.setMessage("Success");
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        }else{
            baseResponse.setStatus(-1);
            baseResponse.setMessage("This student not exits");
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        }
    }
//    @Autowired
//    private StorageService storageService;
//
//    @RequestMapping(value = "/test", method = RequestMethod.POST,
//            consumes = {"multipart/form-data"})
//    public String test(@RequestParam("file") MultipartFile[] files){
//        Arrays.asList(files).stream().forEach(item -> storageService.uploadFile(item));
////        storageService.uploadFile(file);
//        return "list";
//    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "Helloooooooooooooooooooooo";
    }
}
