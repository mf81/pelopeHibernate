package eu.maciejfjalkowski.pelopeHibernate.controler;

import eu.maciejfjalkowski.pelopeHibernate.model.Person;
import eu.maciejfjalkowski.pelopeHibernate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String www(){
        return "redirect:/people";
    }

    @GetMapping("/people")
    public String displayAll(ModelMap modelMap){
        modelMap.addAttribute("people",personRepository.findAll());
        modelMap.addAttribute("searchForm",true);
        return "index";
    }

    @GetMapping("/people/new")
    public String addUser(ModelMap modelMap){
        modelMap.addAttribute("person",new Person());
        return "adduser";
    }

    @PostMapping("/people/savenew")
    public String create(@ModelAttribute @Valid Person person, BindingResult bindingResult, ModelMap modelMap){
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("person",person);
            return "adduser";
        }
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

    @PostMapping("/people/search")
    public String displaySearch(@RequestParam String nameOrSurname, @RequestParam String search, ModelMap modelMap){
        if(nameOrSurname.equals("name")){
            modelMap.addAttribute("people",personRepository.findByNameIgnoreCase(search.toLowerCase()));
        }
        if(nameOrSurname.equals("surname")){
            modelMap.addAttribute("people",personRepository.findBySurnameIgnoreCase(search.toLowerCase()));
        }
        modelMap.addAttribute("searchForm",false);
        return "index";

    }

    @GetMapping("/people/single/{id}")
    public String singlePeople(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("people",personRepository.findById(id).get());
        return "single";
    }

}
