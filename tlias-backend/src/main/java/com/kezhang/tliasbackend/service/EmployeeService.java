package com.kezhang.tliasbackend.service;


import com.kezhang.tliasbackend.common.PageResult;
import com.kezhang.tliasbackend.dto.EmployeeQueryDTO;
import com.kezhang.tliasbackend.dto.EmployeeResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
//    /*
//    * !!! 基于传统 SQL 的分页接口 !!!
//    * Select all employees with their department names and job titles.
//    * This method retrieves a list of EmployeeResponseDTO objects,
//    * @return List of EmployeeResponseDTO containing employee details
//    * */
//     PageResult selectAllEmployees(Integer page, Integer limit);

    /*
    * !!! 基于 PageHelper 插件的分页接口 !!!
    * Select all employees with their department names and job titles.
    * This method retrieves a list of EmployeeResponseDTO objects,
    * @Param page Current page number, starting from 1
    * @param pageSize Number of records per page
    * @return List of EmployeeResponseDTO containing employee details
    * */
//    PageResult<EmployeeResponseDTO> selectAllEmployees(Integer page, Integer pageSize);

    /*
    * !!! 基于 条件查询 + PageHelper 插件的分页接口 !!!
    * Select all employees with their department names and job titles.
    * This method retrieves a list of EmployeeResponseDTO objects,
    * @Param page Current page number, starting from 1
    * @param pageSize Number of records per page
    * @return List of EmployeeResponseDTO containing employee details
    * */
//    PageResult<EmployeeResponseDTO> selectEmployeesByCondition(String name, Integer gender,
//                                                               LocalDate startDate, LocalDate endDate,
//                                                               Integer page, Integer pageSize);

    PageResult<EmployeeResponseDTO> selectEmployeesByCondition(EmployeeQueryDTO employeeQueryDTO);

}
