package com.cv.cv_c_r_u_d.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cv.cv_c_r_u_d.DTO.cvRegistrationDto;
import com.cv.cv_c_r_u_d.Model.cvRegistration;
import com.cv.cv_c_r_u_d.Service.cvRegistrationService;

@Controller
@RequestMapping("/Registration")
public class cvRegController {

    @Autowired
    private cvRegistrationService cService;

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        List<cvRegistration> cList = cService.getAll();
        model.addAttribute("cList", cList);
        return "allUsers";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("cvRegistrationDto", new cvRegistrationDto());
        return "cvRegistration";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute cvRegistrationDto cDto) {
        cService.saveInfo(cDto.getName(), cDto.getEmail(), cDto.getPhone(), cDto.getDepartment(), cDto.getCity(),
                cDto.getCountry(), cDto.getYearOfBirth());
        return "redirect:/Registration/allUsers";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<cvRegistration> cRegistration = cService.getById(id);
        if (cRegistration.isPresent()) {
            model.addAttribute("cRegistration", cRegistration.get());
            model.addAttribute("cvRegistrationDto",
                    new cvRegistrationDto(cRegistration.get().getName(), cRegistration.get().getEmail(),
                            cRegistration.get().getPhone(), cRegistration.get().getDepartment(),
                            cRegistration.get().getCity(),
                            cRegistration.get().getCountry(), cRegistration.get().getYearOfBirth()));
            return "update";
        } else {
            return "redirect:/Registration/allUsers";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute cvRegistrationDto cDto) {
        cService.updateInfo(id, cDto.getName(), cDto.getEmail(), cDto.getPhone(), cDto.getDepartment(), cDto.getCity(),
                cDto.getCountry(), cDto.getYearOfBirth());
        return "redirect:/Registration/allUsers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cService.delete(id);
        return "redirect:/Registration/allUsers";
    }
}
