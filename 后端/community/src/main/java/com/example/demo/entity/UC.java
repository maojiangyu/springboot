package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@EntityListeners(AuditingEntityListener.class)
public class UC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne
    @JoinColumn(name="cid")
    private Community community;

    @ManyToOne
    @JoinColumn(name="sid")
    private User user;

    @Column(name = "isLeader",nullable = false,unique = false)
    private boolean isLeader = false;

    @CreatedDate
    @Column(name = "create_time",updatable = false,nullable = false)
    private Date createTime;

}
