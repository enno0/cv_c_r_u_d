package com.cv.cv_c_r_u_d.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.cv_c_r_u_d.DAO.UsersAccOp;
import com.cv.cv_c_r_u_d.model.Users;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsersCRUD {
    @Autowired
    private UsersAccOp uAO;

    // Create
    public void saveInfo(String name, String email, String password, String mobilePhone) {
        if (validateInputs(name, password, email, mobilePhone)) {
            Users users = new Users(name, mobilePhone, password, email);
            uAO.save(users);
        }
    }

    // update
    public void updateInfo(String name, String email, String password, String mobilePhone, Long id) {
        Optional<Users> existingRecord = uAO.findById(id);
        if (existingRecord.isPresent() && validateInputs(name, password, email, mobilePhone)) {
            Users users = existingRecord.get();
            users.setName(name);
            users.setEmail(email);
            users.setPassword(password);
            users.setMobilePhone(mobilePhone);
            uAO.save(users);
        } else {
            throw new IllegalArgumentException("Invalid input data or record not found.");
        }

    }

    // validation
    private boolean validateInputs(String name, String password, String email, String mobilePhone) {
        // Add proper validation logic
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()
                || mobilePhone.isEmpty() || mobilePhone == null) {
            return false;
        }
        // simple email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }
        return true;
    }

    // Delete
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }
        uAO.deleteById(id);
    }

    // Read
    public Optional<Users> getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }
        return uAO.findById(id);
    }

    public List<Users> getAll() {
        return uAO.findAll();
    }

}
