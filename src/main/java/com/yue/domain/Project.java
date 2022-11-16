package com.yue.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("project")
public class Project implements Serializable {

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

    private int clientNumber;

    private String compileName;

    private float investPrice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp submitTime;

    private int state;

    private String suggestion;

    private String date;

    private String userUsername;
    private int userId;

    public Project(String projectName, String sectionName, String projectOwner, String clientUnit, String compileInstitution, String reviewSort, String inventoryNorm, String quotaSystem, Date approvalTime, String projectCondition, String projectAddress, String approvalNumber, String username, String clientUsername, String institutionNumber, String industrySort, String priceGist, float approvalPrice, String technicalNumber, String userNumber, int clientNumber, String compileName, float investPrice) {
        this.projectName = projectName;
        this.sectionName = sectionName;
        this.projectOwner = projectOwner;
        this.clientUnit = clientUnit;
        this.compileInstitution = compileInstitution;
        this.reviewSort = reviewSort;
        this.inventoryNorm = inventoryNorm;
        this.quotaSystem = quotaSystem;
        this.approvalTime = approvalTime;
        this.projectCondition = projectCondition;
        this.projectAddress = projectAddress;
        this.approvalNumber = approvalNumber;
        this.username = username;
        this.clientUsername = clientUsername;
        this.institutionNumber = institutionNumber;
        this.industrySort = industrySort;
        this.priceGist = priceGist;
        this.approvalPrice = approvalPrice;
        this.technicalNumber = technicalNumber;
        this.userNumber = userNumber;
        this.clientNumber = clientNumber;
        this.compileName = compileName;
        this.investPrice = investPrice;
    }

    public Project(int i) {
        this.state  = i;
    }
}
