package com.kezhang.tliasbackend.controller;

import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.dto.*;
import com.kezhang.tliasbackend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports")
@Tag(name = "ReportController", description = "Controller for managing reports")
public class ReportController {
    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /*
     * 用于处理前端的部门员工报表请求
     * @return Result<?> 返回报表数据
     */
    @GetMapping("/department-employee-count")
    @Operation(summary = "Get data for employee per department report ", description = "Retrieve data for employee report")
    public Result<?> departmentEmployeeReport(){
        log.info("Received request for department employee report.");
        DepartmentEmployeeCountResponseDTO departmentEmployeeCountResponseDTO = reportService.getDepartmentEmployeeCount();
        log.info("Returning department employee report data: {}", departmentEmployeeCountResponseDTO);
        return Result.success(departmentEmployeeCountResponseDTO);
    }

    /*
    * 用于处理前端的岗位员工报表请求
    * @return Result<?> 返回报表数据
    * */
    @GetMapping("/position-employee-count")
    @Operation(summary = "Get data for employee per position report", description = "Retrieve data for employee report")
    public Result<?> positionEmployeeReport(){
        log.info("Received request for position employee report.");
        PositionEmployeeCountResponseDTO positionEmployeeCountResponseDTO = reportService.getPositionEmployeeCount();
        log.info("Returning position employee report data: {}", positionEmployeeCountResponseDTO);
        return Result.success(positionEmployeeCountResponseDTO);
    }

    /*
    * 用于处理前端的员工性别的报表请求
    * @return Result<?>
    * */
    @GetMapping("/employee-gender-count")
    @Operation(summary = "Get data for gender distribution report", description = "Retrieve data for gender distribution report")
    public Result<?> employeeGenderReport(){
        log.info("Received request for gender distribution report.");
        List<EmployeeGenderDTO> employeeGenderCountList = reportService.getEmployeeGenderCount();
        log.info("Returning employee gender report data: {}", employeeGenderCountList);
        return Result.success(employeeGenderCountList);
    }

    /*
    * 用于处理前端的学生学位报表请求
    * @return Result<?>
    * */
    @Operation(summary = "Get data for student degree report", description = "Retrieve data for student degree report")
    @GetMapping("/student-degree-count")
    public Result<?> studentDegreeReport() {
        log.info("Received request for student degree report.");
        List<StudentDegreeReportDTO> studentDegreeCountList = reportService.getStudentDegreeCount();
        log.info("Returning student degree report data: {}", studentDegreeCountList);
        return Result.success(studentDegreeCountList);
    }

    /*
    * 处理前端的学生班级报表请求
    * @return Result<?>
    * */
    @Operation(summary = "Get data for student class report", description = "Retrieve data for student class report")
    @GetMapping("/student-clazz-count")
    public Result<?> studentClazzReport(){
        log.info("Received request for student class report.");
        StudentClazzCountDTO studentClazzCount = reportService.getStudentClazzCount();
        log.info("Returning student class report data: {}", studentClazzCount);
        return Result.success(studentClazzCount);
    }

}
