/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mccoc.ksmbootstrap.controllers;

import com.mccoc.ksmbootstrap.entities.Datamhs;
import com.mccoc.ksmbootstrap.entities.Matkul;
import com.mccoc.ksmbootstrap.services.DatamhsService;
import com.mccoc.ksmbootstrap.services.MatkulService;
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
    DatamhsService datamhsservices;

    @Autowired
    MatkulService matkulservices;

    @GetMapping("")
    public String awal(Model model) {
        model.addAttribute("datamhs", new Datamhs());
        return "index";
    }
    
    @GetMapping("/index")
    public String login(Model model) {
        model.addAttribute("datamhs", new Datamhs());
        return "index";
    }
    
    @RequestMapping("/ksm/{nim}")
    public ModelAndView ksm(Model model, @PathVariable(name = "nim") String nim) {
        ModelAndView mav = new ModelAndView("ksm");
        mav.addObject("matkul", new Matkul());
        mav.addObject("matkuls", matkulservices.getbyNIM(nim));
        mav.addObject("mahasiswa", datamhsservices.getbynim(nim));
        model.addAttribute("matkulll", matkulservices.getAll());
        return mav;
    }

    

    @RequestMapping("/delete/{kode}/{nim}")
    public String delete(@PathVariable(name = "kode") String kode, @PathVariable(name = "nim") String nim) {
        matkulservices.deleteksm(kode, nim);
        return "redirect:/ksm/" + nim;
    }

    @PostMapping("/savedata/{nim}")
    public String save(@ModelAttribute(value = "kode") Matkul matkul, @PathVariable(name = "nim") String nim, Model model) {
        if (matkulservices.checkksm(matkul.getKode(), nim)) {
            model.addAttribute("error", true);
        } else if (matkulservices.checkkode(matkul.getKode()) == false) {
            model.addAttribute("error", true);
        } else {
            matkulservices.savetoksm(matkul.getKode(), nim);
        }
        return "redirect:/ksm/" + nim;
    }

    @GetMapping("/alljadwal/{nim}")
    public String alljadwal(@PathVariable(name = "nim") String nim, Model model) {
        model.addAttribute("matkulll", matkulservices.getAll());
        model.addAttribute("mahasiswa", datamhsservices.getbynim(nim));
        return "jadwal";
    }

    //mengecek isi dari form login, apakah sesuai di database
    @RequestMapping("/check")
    public String checkLogin(@ModelAttribute(name = "datamhs") Datamhs datamhs, Model model) {

        String nim = datamhs.getNim();
        String password = datamhs.getPassword();

        if ((nim.equalsIgnoreCase("admin")) && (password.equalsIgnoreCase("admin"))) {
            return "redirect:/adminpage";
        }

        if (datamhsservices.checknim(nim)) {
            if (password.equalsIgnoreCase(datamhsservices.checkpass(nim))) {
                model.addAttribute("name", datamhsservices.checkname(nim));
                return "redirect:/ksm/" + nim;
            } else {
                model.addAttribute("loginError", true);
                return "index";
            }
        } else {
            model.addAttribute("loginError", true);
            return "index";
        }
    }

    @GetMapping("/adminpage")
    public String ksm(Model model) {
        model.addAttribute("matkul", new Matkul());
        model.addAttribute("matkulll", matkulservices.getAll());
        return "adminpage";
    }

    @GetMapping("/delete/{kode}")
    public String delete(@PathVariable(name = "kode") String kode) {
        matkulservices.deletematkul(kode);
        return "redirect:/adminpage";
    }

    @PostMapping("/savematkul")
    public String save(@Valid Matkul matkul) {
         matkul.setKode(matkul.getKode().toUpperCase());
         matkulservices.savematkul(matkul);
        return "redirect:/adminpage";
    }

    @RequestMapping("/updatematkul/{kode}")
    public String update(@Valid Matkul matkul, Model model,@PathVariable(name = "kode") String kode) {
        model.addAttribute("matkul", new Matkul(kode));
        model.addAttribute("matkulll", matkulservices.getAll());
        matkulservices.savematkul(matkul);
        return "redirect:/adminpage";
    }

}
