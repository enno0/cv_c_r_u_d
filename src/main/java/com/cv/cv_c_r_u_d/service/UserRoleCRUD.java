package com.cv.cv_c_r_u_d.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.cv_c_r_u_d.dataaccessopject.UserRoleAccessOpj;
import com.cv.cv_c_r_u_d.models.UserRole;

@Service
public class UserRoleCRUD {
    @Autowired
    private UserRoleAccessOpj uAO;

    // Create
    public void saveInfo(String name, String role) {
        UserRole roles = new UserRole(name, role);
        uAO.save(roles);

    }

    // Read
    public Optional<UserRole> getUserRoleById(Long id) {
        return uAO.findById(id);
    }

    public List<UserRole> getAllUserRoles() {
        return uAO.findAll();
    }

    // update
    public void updateInfo(String name, String role, Long id) {
        Optional<UserRole> existingRecord = uAO.findById(id);
        if (existingRecord.isPresent()) {
            UserRole usersRole = existingRecord.get();
            usersRole.setName(name);
            usersRole.setRole(role);
            uAO.save(usersRole);
        } else {
            throw new IllegalArgumentException("Invalid input data or record not found.");
        }

    }

    // Delete
    public void delete(Long id) {
        uAO.deleteById(id);
    }

}