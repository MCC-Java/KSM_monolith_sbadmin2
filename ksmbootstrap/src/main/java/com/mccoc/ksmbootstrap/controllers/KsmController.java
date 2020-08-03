/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.controllers;

import com.mccoc.ksmbootstrap.entities.Students;
import com.mccoc.ksmbootstrap.entities.Courses;
import com.mccoc.ksmbootstrap.entities.Accounts;
import com.mccoc.ksmbootstrap.entities.Request;
import com.mccoc.ksmbootstrap.services.AccountsService;
import com.mccoc.ksmbootstrap.services.StudentsService;
import com.mccoc.ksmbootstrap.services.CoursesService;
import com.mccoc.ksmbootstrap.services.RequestService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class KsmController {

    @Autowired
    StudentsService Studentsservices;

    @Autowired
    CoursesService Coursesservices;

    @Autowired
    AccountsService Accountservices;

    @Autowired
    RequestService Requestservices;

    @GetMapping("")
    public String awal(Model model) {
        model.addAttribute("account", new Accounts());
        return "index";
    }

    @GetMapping("/index")
    public String login(Model model) {
        model.addAttribute("account", new Accounts());
        return "index";
    }

    @RequestMapping("/ksm/{nim}")
    public ModelAndView ksm(Model model, @PathVariable(name = "nim") String nim) {
        ModelAndView mav = new ModelAndView("ksm");
        mav.addObject("matkul", new Courses());
        mav.addObject("sksnow", Coursesservices.sksnow(nim));
        mav.addObject("matkull", Coursesservices.getbyNIM(nim));
        mav.addObject("mahasiswa", Studentsservices.getbynim(nim));
        model.addAttribute("matkulll", Coursesservices.showtostudent());
        return mav;
    }

    @GetMapping("/savereq/{kode}/{nim}/{ket}")
    public String accept(@PathVariable(name = "kode") String kode, @PathVariable(name = "nim") String nim, @PathVariable(name = "ket") String ket) {
        Requestservices.savereq(nim, kode, ket);
        return "redirect:/requestmhs/" + nim;
    }

    @RequestMapping(value = "/save/{nim}/{kode}", method = RequestMethod.GET)
    public String savemhs(@PathVariable(name = "nim") String nim, @PathVariable(name = "kode") String kode, Model model) {
        if (Coursesservices.checkksm(kode, nim)) {
            model.addAttribute("mahasiswa", Studentsservices.getbynim(nim));
            return "gagal";
        } else if (Studentsservices.getsks(nim) < (Coursesservices.sks(kode) + Coursesservices.sksnow(nim))) {
            model.addAttribute("mahasiswa", Studentsservices.getbynim(nim));
            return "gagal";
        } else if (Coursesservices.checknama(Coursesservices.nama(kode), nim)) {
            model.addAttribute("mahasiswa", Studentsservices.getbynim(nim));
            return "gagal";
        }
        else {
            Coursesservices.savetoksm(nim, kode);
            Coursesservices.minkuota(kode);
            return "redirect:/ksm/" + nim;
        }
    }

    @GetMapping("/alljadwal/{nim}")
    public String alljadwal(@PathVariable(name = "nim") String nim, Model model) {
        model.addAttribute("matkulll", Coursesservices.showtostudent());
        model.addAttribute("mahasiswa", Studentsservices.getbynim(nim));
        return "jadwal";
    }

    @GetMapping("/requestmhs/{nim}")
    public String requestmhs(@PathVariable(name = "nim") String nim, Model model) {
        model.addAttribute("mahasiswa", Studentsservices.getbynim(nim));
        model.addAttribute("active", Requestservices.findactivemhs(nim));
        model.addAttribute("accept", Requestservices.findaccmhs(nim));
        model.addAttribute("reject", Requestservices.findrejectmhs(nim));
        return "requestmhs";
    }

    //mengecek isi dari form login, apakah sesuai di database
    @RequestMapping("/check")
    public String checkLogin(@ModelAttribute(name = "account") Accounts account, Model model) {

        String username = account.getUsername();
        String password = account.getPassword();

        if (Accountservices.checkusername(username)) {
            if (password.equalsIgnoreCase(Accountservices.getpass(username))) {
                String role = Accountservices.getrole(username);
                if (role.equalsIgnoreCase("student")) {
                    return "redirect:/ksm/" + username;
                } else {
                    return "redirect:/adminpage";
                }
            } else {
                model.addAttribute("loginError", true);
                return "index";
            }
        } else {
            model.addAttribute("loginError", true);
            return "index";
        }
    }

//    START ADMIN CONTROLLER
    @GetMapping("/adminpage")
    public String ksm(Model model) {
        model.addAttribute("matkul", new Courses());
        model.addAttribute("matkulll", Coursesservices.getAll());
        return "adminpage";
    }

    @GetMapping("/delete/{kode}")
    public String delete(@PathVariable(name = "kode") String kode) {
        Coursesservices.deleteCourses(kode);
        return "redirect:/adminpage";
    }

    @PostMapping("/savematkul")
    public String save(@Valid Courses Courses) {
        Courses.setKode(Courses.getKode().toUpperCase());
        Coursesservices.saveCourses(Courses);
        return "redirect:/adminpage";
    }

    @RequestMapping("/updatematkul/{kode}")
    public String update(@Valid Courses Courses, Model model, @PathVariable(name = "kode") String kode) {
        model.addAttribute("Courses", new Courses(kode));
        model.addAttribute("Coursesll", Coursesservices.getAll());
        Coursesservices.saveCourses(Courses);
        return "redirect:/adminpage";
    }

    @GetMapping("/requestadmin")
    public String reqadmin(Model model) {
        model.addAttribute("request", Requestservices.getactive());
        return "requestadmin";
    }

    @GetMapping("/acceptrequest/{kode}/{ket}")
    public String accept(@PathVariable(name = "kode") String kode, @PathVariable(name = "ket") String ket) {
        Requestservices.accadmin(kode, ket);
        Coursesservices.addkuota(Requestservices.getkodematkul(Integer.parseInt(kode)));
        return "redirect:/requestadmin";
    }

    @GetMapping("/rejectrequest/{kode}/{ket}")
    public String reject(@PathVariable(name = "kode") String kode, @PathVariable(name = "ket") String ket) {
        Requestservices.deladmin(kode, ket);
        return "redirect:/requestadmin";
    }

    @GetMapping("peserta")
    public String awal() {
        return "peserta";
    }

    @RequestMapping("/peserta/{kode}")
    public ModelAndView peserta(Model model, @PathVariable(name = "kode") String kode) {
        ModelAndView mav = new ModelAndView("peserta");
        mav.addObject("peserta", new Students());
        mav.addObject("kode", kode);
        mav.addObject("pesertaa", Studentsservices.lihatpeserta(kode));
        return mav;
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("requestacc", Requestservices.getaccept());
        model.addAttribute("requestreject", Requestservices.getreject());
        return "history";
    }
}
