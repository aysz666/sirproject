package com.yue.service;

import com.yue.domain.Project;

import java.util.List;

public interface AdminService {
    List<Project> get_all_project();
    List<Project> search_project(String project_name);
    Boolean dosu_project(String id,String suggestion,String state);
}
