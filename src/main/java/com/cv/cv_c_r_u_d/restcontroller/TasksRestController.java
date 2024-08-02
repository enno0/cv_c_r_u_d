package com.cv.cv_c_r_u_d.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cv.cv_c_r_u_d.customannotation.ValidId;
import com.cv.cv_c_r_u_d.models.Tasks;
import com.cv.cv_c_r_u_d.service.TasksCRUD;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TasksRestController {
    @Autowired
    private TasksCRUD tCRUD;

    @GetMapping("/byTitle")
    public ResponseEntity<List<Tasks>> getTasksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(tCRUD.findTasksByTitle(title));
    }

    @GetMapping("/byDescription")
    public ResponseEntity<List<Tasks>> getTasksByDescription(@RequestParam String description) {
        return ResponseEntity.ok(tCRUD.findTasksByDescription(description));
    }

    @GetMapping("/byStatus")
    public ResponseEntity<List<Tasks>> getTasksByStatus(@RequestParam String status) {
        return ResponseEntity.ok(tCRUD.findTasksByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tasks>> searchTasks(
            @RequestParam String operation,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String status) {

        // Validate the operation parameter
        if (!isValidOperation(operation)) {
            return ResponseEntity.badRequest().body(null);
        }

        // Perform the search operation
        List<Tasks> result;
        try {
            result = tCRUD.tasksOperation(title, description, status, operation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(result);
    }

    private boolean isValidOperation(String operation) {
        return "Title".equals(operation) || "Description".equals(operation) || "Status".equals(operation);
    }

    @GetMapping
    public ResponseEntity<List<Tasks>> getAllTasks() {
        return ResponseEntity.ok(tCRUD.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTasksById(@PathVariable @ValidId Long id) {
        Optional<Tasks> tasks = tCRUD.getById(id);
        return tasks.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createTasks(@Valid @RequestBody Tasks tasks) {
        tCRUD.saveInfo(tasks.getTitle(), tasks.getDescription(), tasks.getStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTasks(@PathVariable @ValidId Long id, @Valid @RequestBody Tasks tasks) {
        tCRUD.updateInfo(tasks.getTitle(), tasks.getDescription(), tasks.getStatus(), id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTasks(@PathVariable @ValidId Long id) {
        tCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}
