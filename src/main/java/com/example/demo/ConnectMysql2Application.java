package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ConnectMysql2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConnectMysql2Application.class, args);
//		StudentRepository studentRepository = context.getBean(StudentRepository.class);
//		studentRepository.save(new Student("Thang Pham",18));
//		studentRepository.save(new Student("Thang Nguyen",20));

	}

}
