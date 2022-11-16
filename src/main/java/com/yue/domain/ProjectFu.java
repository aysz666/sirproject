package com.yue.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("project_fu")
public class ProjectFu extends Project{
    @TableId(type = IdType.AUTO)
    private int id;

    private String projectName;

    private String sectionName;

    private String projectOwner;

    private String clientUnit;

    private String compileInstitution;

    private String reviewSort;

    private String inventoryNorm;

    private String quotaSystem;

    private Date approvalTime;

    private String projectCondition;

    private String projectAddress;

    private String approvalNumber;

    private String username;

    private String clientUsername;

    private String institutionNumber;

    private String industrySort;

    private String priceGist;

    private float approvalPrice;

    private String technicalNumber;

    private String userNumber;

    private String clientNumber;

    private String compileName;

    private float investPrice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp submitTime;

    private int state;

    private String suggestion;

    private String date;

    private String userUsername;
    private int userId;
    private int projectId;
}
