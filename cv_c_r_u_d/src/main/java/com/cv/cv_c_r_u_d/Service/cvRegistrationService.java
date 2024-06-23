package com.cv.cv_c_r_u_d.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cv.cv_c_r_u_d.DAO.cvRegistrationDao;
import com.cv.cv_c_r_u_d.Model.cvRegistration;

@Service
@Transactional
public class cvRegistrationService {
    @Autowired
    private cvRegistrationDao cDao;

    // Create
    public void saveInfo(String name, String email, String phone, String department, String city, String country,
            String yearOfBirth) {
        if (validateInputs(name, email, phone, department, city, country, yearOfBirth)) {
            cvRegistration cRegistration = new cvRegistration(name, email, phone, department, city, country,
                    yearOfBirth);
            cDao.save(cRegistration);
        } else {
            throw new IllegalArgumentException("Invalid input data.");
        }
    }

    // Update
    public void updateInfo(Long id, String name, String email, String phone, String department, String city,
            String country,
            String yearOfBirth) {
        Optional<cvRegistration> existingRecord = cDao.findById(id);
        if (existingRecord.isPresent() && validateInputs(name, email, phone, department, city, country, yearOfBirth)) {
            cvRegistration cRegistration = existingRecord.get();
            cRegistration.setName(name);
            cRegistration.setEmail(email);
            cRegistration.setPhone(phone);
            cRegistration.setDepartment(department);
            cRegistration.setCity(city);
            cRegistration.setCountry(country);
            cRegistration.setYearOfBirth(yearOfBirth);
            cDao.save(cRegistration);
        } else {
            throw new IllegalArgumentException("Invalid input data or record not found.");
        }
    }

    // Delete
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }
        cDao.deleteById(id);
    }

    // Read
    public Optional<cvRegistration> getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }
        return cDao.findById(id);
    }

    public List<cvRegistration> getAll() {
        return cDao.findAll();
    }

    // Helper method for input validation
    private boolean validateInputs(String name, String email, String phone, String department, String city,
            String country,
            String yearOfBirth) {
        // Add proper validation logic (e.g., null checks, regex checks for email/phone)
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty()
                || department == null || department.isEmpty() || city == null || city.isEmpty()
                || country == null || country.isEmpty() || yearOfBirth == null || yearOfBirth.isEmpty()) {
            return false;
        }
        // Example of a simple email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }
        return true;
    }

}
