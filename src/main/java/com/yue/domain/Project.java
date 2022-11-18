package com.yue.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("project")
@AllArgsConstructor
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


    public ProjectFu toFu(){
        ProjectFu fu = new ProjectFu();
        fu.setProjectId(this.id);
        fu.setDate(this.date);
        fu.setApprovalNumber(this.approvalNumber);
        fu.setClientNumber(this.clientNumber);
        fu.setApprovalPrice(this.approvalPrice);
        fu.setClientUnit(this.clientUnit);
        fu.setInventoryNorm(this.inventoryNorm);
        fu.setCompileName(this.compileName);
        fu.setCompileInstitution(this.compileInstitution);
        fu.setPriceGist(this.priceGist);
        fu.setIndustrySort(this.industrySort);
        fu.setProjectOwner(this.projectOwner);
        fu.setUserUsername(this.userUsername);
        fu.setUserNumber(this.userNumber);
        fu.setProjectAddress(this.projectAddress);
        fu.setState(-1);
        fu.setSuggestion(this.suggestion);
        fu.setApprovalTime(this.approvalTime);
        fu.setTechnicalNumber(this.technicalNumber);
        fu.setSubmitTime(this.submitTime);
        fu.setSectionName(this.sectionName);
        fu.setInstitutionNumber(this.institutionNumber);
        fu.setUserId(this.userId);
        fu.setQuotaSystem(this.quotaSystem);
        fu.setProjectName(this.projectName);
        fu.setReviewSort(this.reviewSort);
        fu.setProjectCondition(this.projectCondition);
        fu.setUsername(this.username);
        fu.setClientUsername(this.clientUsername);
        fu.setInvestPrice(this.investPrice);

        return fu;
    }

}
