package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@EntityListeners(AuditingEntityListener.class)
public class applyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "isPass",nullable = false,unique = false)
    private boolean isPass = false;

    @ManyToOne
    @JoinColumn(name="cid")
    private Community community;

    @ManyToOne
    @JoinColumn(name="sid")
    private User user;

    @CreatedDate
    @Column(name = "create_time",updatable = false,nullable = false)
    private Date createTime;

}
