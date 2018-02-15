package eu.maciejfjalkowski.pelopeHibernate.controler;

import eu.maciejfjalkowski.pelopeHibernate.model.Person;
import eu.maciejfjalkowski.pelopeHibernate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/people")
    public String displayAll(ModelMap modelMap){
        modelMap.addAttribute("people",personRepository.findAll());
        return "index";
    }

    @GetMapping("/people/new")
    public String addUser(){
        return "adduser";
    }

    @PostMapping("/people/savenew")
    public String create(@ModelAttribute Person person){
        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/people/edit/{id}")
    public String editUser(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("person",personRepository.findById(id).get());
        return "edituser";
    }

    @PostMapping("/people/saveedit")
    public String edit(@ModelAttribute Person person){
        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/people/del/{id}")
    public String addDel(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("people",personRepository.findById(id).get());
        return "delete";
    }

    @DeleteMapping("/people/delete")
    public String addDelOk(@ModelAttribute Person person){
        personRepository.deleteById(person.getId());
        return "redirect:/people";
    }
}
