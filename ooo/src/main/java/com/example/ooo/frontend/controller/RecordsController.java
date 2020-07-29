package com.example.ooo.frontend.controller;

import com.example.ooo.backend.model.RecordJournal;
import com.example.ooo.backend.service.PropertiesService;
import com.example.ooo.backend.service.RecordJournalService;
import com.example.ooo.frontend.forms.RecordJournalForm;
import com.example.ooo.frontend.forms.UserLoginForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class RecordsController {

    private final RecordJournalService recordJournalService;
    private final PropertiesService propertiesService;


    @GetMapping("/records")
    public String records(Model model, Pageable pageable, HttpServletRequest uriBuilder, Principal principal) {
        model.addAttribute("userName", principal.getName());
        PropertiesService.constructPageable(recordJournalService.getRecordJournal(pageable, principal), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "records";
    }

    @GetMapping("/record")
    public String record(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        return "record";
    }

    @PostMapping("/record")
    public String record(@Valid RecordJournalForm recordJournalForm,
                        BindingResult validationResult, RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/user/record";
        }

        recordJournalService.createRecord(recordJournalForm);

        return "redirect:/user/records";
    }

    @GetMapping("/records/record/{id}")
    public String recordEdit(Model model, Principal principal, @PathVariable("id") Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("record", recordJournalService.getRecord(id));
        return "recordEdit";
    }

    @GetMapping("/records/record/delete/{id}")
    public String recordDelete(@PathVariable("id") Long id) {
        recordJournalService.deleteById(id);
        return "redirect:/user/records";
    }

    @GetMapping("/records/record/success/{id}")
    public String recordSuccess(@PathVariable("id") Long id) {
        recordJournalService.successById(id);
        return "redirect:/user/records";
    }


    @PostMapping("/records/record/{id}")
    public String recordEdit(@Valid RecordJournalForm recordJournalForm,
                        BindingResult validationResult, RedirectAttributes attributes, @PathVariable("id") Long id){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/user/records/record/"+id;
        }

        recordJournalService.editRecord(recordJournalForm, id);

        return "redirect:/user/records";
    }
}
