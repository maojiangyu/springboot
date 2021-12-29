package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@EntityListeners(AuditingEntityListener.class)
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = false)
    @NotEmpty(message = "公告名不能为空")
    private String name;

    @CreatedDate
    @Column(name = "create_time",updatable = false,nullable = false)
    private Date createTime;

    @LastModifiedDate
    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(nullable = false,columnDefinition="varchar(300) COMMENT '公告内容'")
    private String describeA;

    @ManyToOne
    @JoinColumn(name="cid")
    private Community community;
}
