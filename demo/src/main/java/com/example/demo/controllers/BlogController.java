package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToOne;
import javax.validation.Valid;
import java.util.*;

@Controller
public class BlogController  {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReaderRepository chitRepository;
    @Autowired
    private JornalRepository jornalRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DirRepository dirRepository;

    @GetMapping("/")
    public String Main(Model model)
    {
        return "blog-main";
    }

    @GetMapping("/stati")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "stati";
    }

   @GetMapping("/blog/add")
    public String blogAdd(Post post,Model model)
    {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public Object blogPostAdd(@ModelAttribute("post")@Validated Post post, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "blog-add";
        }
        postRepository.save(post);
        return "redirect:/stati";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model)
    {
        Post result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }

    @GetMapping("/stati/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") long id, Model model)
    {
        Post res = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable("id") long id,
                                 @ModelAttribute("post")
                                 @Validated Post post, BindingResult bindingResult)
    {
        post.setId(id);
        if(bindingResult.hasErrors()){
            return "blog-edit";
        }
        postRepository.save(post);
        return "redirect:/stati";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable("id") long id, Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/stati";
    }















    @GetMapping("/chitateli")
    public String chitateli(Model model)
    {
        Iterable<Chitateli> chitateliIterable = chitRepository.findAll();
        model.addAttribute("chitateli", chitateliIterable);
        return "chitateli";
    }
    @GetMapping("/chitateli/filter")
    public String chitatelFilter(Model model)
    {
        return "chitateli-filter";
    }

    @PostMapping("/chitateli/filter/result")
    public String chitatelResult(@RequestParam String fam, Model model)
    {
        List<Chitateli> result = chitRepository.findByFam(fam);
        model.addAttribute("result", result);
        return "chitateli-filter";
    }
    @GetMapping("/chitateli/add")
    public String chitatelAdd(Chitateli chitateli, Model model)
    {
        return "chitateli-add";
    }

    @PostMapping("/chitateli/add")
    public Object chitatelAdd(@ModelAttribute("chitateli")@Validated Chitateli chitateli, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "chitateli-add";
        }
        chitRepository.save(chitateli);
        return "redirect:/chitateli";
    }

    @GetMapping("/chitateli/{id}")
    public String chitatelDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Chitateli> chitateli = chitRepository.findById(id);
        ArrayList<Chitateli> res = new ArrayList<>();
        chitateli.ifPresent(res::add);
        model.addAttribute("chitateli", res);
        if(!chitRepository.existsById(id)){
            return "redirect:/chitateli";
        }
        return "chitateli-details";
    }

    @GetMapping("/chitateli/{id}/edit")
    public String chitatelEdit(@PathVariable("id") long id, Model model)
    {
        Chitateli res = chitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("chitateli", res);

        return "chitateli-edit";
    }

    @PostMapping("/chitateli/{id}/edit")
    public String chitatelPostUpdate(@PathVariable("id") long id,
                                     @ModelAttribute("chitateli")
                                     @Validated Chitateli chitateli, BindingResult bindingResult)
    {
        chitateli.setId(id);
        if(bindingResult.hasErrors()){
            return "chitateli-edit";
        }
        chitRepository.save(chitateli);
        return "redirect:/chitateli";
    }

    @PostMapping("/chitateli/{id}/remove")
    public String chitatelPostRemove(@PathVariable("id") long id, Model model)
    {
        Chitateli chitateli = chitRepository.findById(id).orElseThrow();
        chitRepository.delete(chitateli);
        return "redirect:/chitateli";
    }














    @GetMapping("/jornalisti")
    public String blogJornalists(Model model)
    {
        Iterable<Jornalisti> jornalisti = jornalRepository.findAll();
        model.addAttribute("jornalisti", jornalisti);
        return "jornalisti";
    }

    @GetMapping("/jornalisti/filter")
    public String jornalFilter(Model model)
    {
        return "jornal-filter";
    }

    @PostMapping("/jornalisti/filter/result")
    public String jornalResult(@RequestParam String fam, Model model)
    {
        Jornalisti result = jornalRepository.findByFamContains(fam);
        model.addAttribute("result", result);
        return "jornal-filter";
    }
    @PostMapping("/jornalisti/filter/tochresult")
    public String jornalResultToch(@RequestParam String fam, Model model)
    {
        Jornalisti tochresult = jornalRepository.findByFamContains(fam);
        model.addAttribute("tochresult", tochresult);
        return "jornal-filter";
    }
    @GetMapping("/jornalisti/add")
    public String jornalistiAdd(Jornalisti jornalisti, Model model)
    {
        model.addAttribute("company", companyRepository.findAll());
        model.addAttribute("directors",dirRepository.findAll());
        return "jornal-add";
    }
    @PostMapping("/jornalisti/add")
    public Object jornalistiAdd(@ModelAttribute("jornalisti")
                                    @Validated Jornalisti jornalisti, BindingResult bindingResult,
                                    @RequestParam Long company_id,
                                    @RequestParam Long director_id ,Model model){

        model.addAttribute("company", companyRepository.findAll());
        model.addAttribute("directors", dirRepository.findAll());
        if(bindingResult.hasErrors()){
            return "jornal-add";
        }
        Company company;
        company = companyRepository.findById(company_id).orElseThrow();
        jornalisti.setCompany(company);
        jornalRepository.save(jornalisti);
        Director director;
        director = dirRepository.findById(director_id).orElseThrow();
        jornalisti.setDirector(director);
        jornalRepository.save(jornalisti);
        return "redirect:/jornalisti";
    }

    @GetMapping("/jornalisti/{id}")
    public String jornalDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Jornalisti> jornalisti = jornalRepository.findById(id);
        ArrayList<Jornalisti> res = new ArrayList<>();
        jornalisti.ifPresent(res::add);
        model.addAttribute("jornalisti", res);
        return "jornalisti-details";
    }

    @GetMapping("/jornalisti/{id}/edit")
    public String jornalEdit(@PathVariable("id") long id, Model model)
    {
        Jornalisti res = jornalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
        model.addAttribute("company", companyRepository.findAll());
        model.addAttribute("jornalisti", res);
        return "jornalisti-edit";
    }

    @PostMapping("/jornalisti/{id}/edit")
    public String jornalPostUpdate(@PathVariable("id") long id,
                                   @ModelAttribute("jornalisti")
                                   @Validated Jornalisti jornalisti, BindingResult bindingResult,
                                   @RequestParam Long company_id, Model model)
    {
        model.addAttribute("company", companyRepository.findAll());
        jornalisti.setId(id);
        if(bindingResult.hasErrors()){
            return "jornalisti-edit";
        }
        Company company;
        company = companyRepository.findById(company_id).orElseThrow();
        jornalisti.setCompany(company);
        jornalRepository.save(jornalisti);
        return "redirect:/jornalisti";
    }

    @PostMapping("/jornalisti/{id}/remove")
    public String jornalPostRemove(@PathVariable("id") long id, Model model)
    {
        Jornalisti jornalisti = jornalRepository.findById(id).orElseThrow();
        jornalRepository.delete(jornalisti);
        return "redirect:/jornalisti";
    }











    @GetMapping("/company")
    public String blogCompany(Model model)
    {
        Iterable<Company> company = companyRepository.findAll();
        model.addAttribute("company", company);
        return "company";
    }

    @GetMapping("/company/filter")
    public String companyFilter(Model model)
    {
        return "company-filter";
    }

    @PostMapping("/company/filter/result")
    public String companyResult(@RequestParam String namecompany, Model model)
    {
        Company result = companyRepository.findByNamecompanyContains(namecompany);
        model.addAttribute("result", result);
        return "company-filter";
    }
    @GetMapping("/company/add")
    public String companyAdd(Company company, Model model){
        Iterable<Director> dirList= dirRepository.findAll();
        model.addAttribute("directors",dirList);
        return "company-add";
    }
    @OneToOne
    @PostMapping("/company/add")
    public Object companyAdd(@ModelAttribute("company") @Valid Company company,
                             BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            Iterable<Director> dirList= dirRepository.findAll();
            model.addAttribute("directors",dirList);
            return "company-add";
        }
        companyRepository.save(company);
        return "redirect:/company";
    }
//
//
//    @GetMapping("/jornalisti/{id}")
//    public String jornalDetails(@PathVariable(value = "id") long id, Model model)
//    {
//        Optional<Jornalisti> jornalisti = jornalRepository.findById(id);
//        ArrayList<Jornalisti> res = new ArrayList<>();
//        jornalisti.ifPresent(res::add);
//        model.addAttribute("jornalisti", res);
//        return "jornalisti-details";
//    }
//
//    @GetMapping("/jornalisti/{id}/edit")
//    public String jornalEdit(@PathVariable("id") long id, Model model)
//    {
//        Jornalisti res = jornalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id: "+id));
//        model.addAttribute("jornalisti", res);
//        return "jornalisti-edit";
//    }
//
//    @PostMapping("/jornalisti/{id}/edit")
//    public String jornalPostUpdate(@PathVariable("id") long id,
//                                   @ModelAttribute("jornalisti")
//                                   @Validated Jornalisti jornalisti, BindingResult bindingResult)
//    {
//        jornalisti.setId(id);
//        if(bindingResult.hasErrors()){
//            return "jornalisti-edit";
//        }
//        jornalRepository.save(jornalisti);
//        return "redirect:/jornalisti";
//    }
//
//    @PostMapping("/jornalisti/{id}/remove")
//    public String jornalPostRemove(@PathVariable("id") long id, Model model)
//    {
//        Jornalisti jornalisti = jornalRepository.findById(id).orElseThrow();
//        jornalRepository.delete(jornalisti);
//        return "redirect:/jornalisti";
//    }








    @GetMapping("/director")
    public String GetDirector(Model model)
    {
        Iterable<Director> directors = dirRepository.findAll();
        model.addAttribute("directors", directors);
        return "director";
    }

    @GetMapping("/director/filter")
    public String directorFilter(Model model)
    {
        return "director-filter";
    }

    @PostMapping("/director/filter/result")
    public String directorResult(@RequestParam String namedirector, Model model)
    {
        List<Director> result = dirRepository.findByNamedirectorContains(namedirector);
        model.addAttribute("result", result);
        return "director-filter";
    }
    @GetMapping("/director/add")
    public String directorAdd(@ModelAttribute("directors") Director director, Model model){
        return "director-add";
    }

    @PostMapping("/director/add")
    public Object directorAdd(@ModelAttribute("directors")
                             @Validated Director director, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            return "director-add";
        }
        dirRepository.save(director);
        return "redirect:/director";
    }

    @GetMapping("/jornpost")
    public String GetJC(Model model){
        Iterable<Jornalisti> jornalistis = jornalRepository.findAll();
        model.addAttribute("jornalist", jornalistis);
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("company", posts);

        return "jorncomp";

    }

    @GetMapping("/jornpost/add")
    public String jorncompanyAdd(Jornalisti jornalisti, Post company, Model model){
        model.addAttribute("jornalistis", jornalRepository.findAll());
        model.addAttribute("postss", postRepository.findAll());
        return "jorncomp-add";
    }
    @PostMapping("/jornpost/add")
    public String jorncompanyAdd(@RequestParam String posts, @RequestParam String jornalists, Model model){
        Jornalisti jornalisti = jornalRepository.findByNameContains(jornalists);
        Post company = postRepository.findByTitleContains(posts);
        jornalisti.getPostList().add(company);
//        company2.getJornalistiList().add(jornalisti2);
        jornalRepository.save(jornalisti);
//        postRepository.save(company2);
        return "redirect:/jornpost";
    }

}
