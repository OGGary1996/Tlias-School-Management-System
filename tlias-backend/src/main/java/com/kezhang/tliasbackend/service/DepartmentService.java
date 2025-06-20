package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.dto.DepartmentInsertDTO;
import com.kezhang.tliasbackend.dto.DepartmentResponseDTO;
import com.kezhang.tliasbackend.dto.DepartmentUpdateDTO;

import java.util.List;

public interface DepartmentService {
    /**
     * Get all departments.
     *
     * @return the List of departments DTO
     */
    List<DepartmentResponseDTO> getAllDepartments();

    /*
    * Delete a department by id.
    *
    * @param id the id of the department to be deleted
    * @return the number of rows affected by the deletion
    * */
    void deleteDepartmentById(Integer id);

    /*
    * Insert a new department.
    *
    * @param departmentInsertDTO the DTO containing department creation details
    * @return the number of rows affected by the insertion
    * */
    void insertDepartment(DepartmentInsertDTO departmentInsertDTO);

    /*
    * Select a department by id.
    *
    * @param id the id of the department to be selected
    *
    * @return the DepartmentResponseDTO containing department details
    *
    * */
    DepartmentResponseDTO getDepartmentById(Integer id);


    /*
    * Update a department by id.
    *
    * @param departmentUpdateDTO the DTO containing updated department details
    *
    * @return the number of rows affected by the update
    * */
    void updateDepartmentById(DepartmentUpdateDTO departmentUpdateDTO);
}
