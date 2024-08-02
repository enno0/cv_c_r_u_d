package com.cv.cv_c_r_u_d.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.cv_c_r_u_d.dataaccessopject.TasksAccOp;
import com.cv.cv_c_r_u_d.models.Tasks;
import com.cv.cv_c_r_u_d.specification.TaskSpecifications;

@Service
public class TasksCRUD {
    @Autowired
    private TasksAccOp tAP;

    public List<Tasks> tasksOperation(String title, String description,
            String status, String operation) {
        switch (operation) {
            case "Title":
                return tAP.findAll(TaskSpecifications.hasTitle(title));
            case "Description":
                return tAP.findAll(TaskSpecifications.hasDescription(description));
            case "Status":
                return tAP.findAll(TaskSpecifications.hasStatus(status));
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

    }

    public List<Tasks> findTasksByTitle(String title) {
        return tAP.findAll(TaskSpecifications.hasTitle(title));
    }

    public List<Tasks> findTasksByDescription(String description) {
        return tAP.findAll(TaskSpecifications.hasDescription(description));
    }

    public List<Tasks> findTasksByStatus(String status) {
        return tAP.findAll(TaskSpecifications.hasStatus(status));
    }

    // Create
    public void saveInfo(String title, String description,
            String status) {
        Tasks tasks = new Tasks(title, description, status);
        tAP.save(tasks);

    }

    // update
    public void updateInfo(String title, String description,
            String status, Long id) {
        Optional<Tasks> existingRecord = tAP.findById(id);
        if (existingRecord.isPresent()) {
            Tasks tasks = existingRecord.get();
            tasks.setTitle(title);
            tasks.setDescription(description);
            tasks.setStatus(status);
            tAP.save(tasks);
        } else {
            throw new IllegalArgumentException("Invalid input data or record not found.");
        }

    }

    // Delete
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }
        tAP.deleteById(id);
    }

    // Read
    public Optional<Tasks> getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }
        return tAP.findById(id);
    }

    public List<Tasks> getAll() {
        return tAP.findAll();
    }
}
