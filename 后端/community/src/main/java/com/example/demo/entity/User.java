package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "学生编号",nullable = false,unique = true)
    @NotEmpty(message = "学生编号不能为空")
    private String SSID;

    @Column(name = "学生名",nullable = false,unique = false)
    @NotEmpty(message = "学生名不能为空")
    private String SName;

    @Column(name = "密码",nullable = false,unique = false)
    @NotEmpty(message = "密码不能为空")
    private String pwd;



}
