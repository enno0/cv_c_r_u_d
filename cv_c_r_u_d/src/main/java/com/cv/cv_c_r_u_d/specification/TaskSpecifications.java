package com.cv.cv_c_r_u_d.specification;

import org.springframework.data.jpa.domain.Specification;

import com.cv.cv_c_r_u_d.models.Tasks;

public class TaskSpecifications {

    public static Specification<Tasks> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Tasks> hasDescription(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("description"), description);
    }

    public static Specification<Tasks> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}