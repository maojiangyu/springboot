package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.NotEmpty;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@EntityListeners(AuditingEntityListener.class)
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "社团老师",nullable = false,unique = false)
    @NotEmpty(message = "社团老师不能为空")
    private String teacher;

    @Column(name = "社团名",nullable = false,unique = false)
    @NotEmpty(message = "社团名不能为空")
    private String name;

    @CreatedDate
    @Column(name = "create_time",updatable = false,nullable = false)
    private Date createTime;

    @Column(nullable = false,columnDefinition="varchar(300) COMMENT '社团描述'")
    private String describeC;

}
