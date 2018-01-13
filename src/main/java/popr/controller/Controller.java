package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import popr.model.Person;
import popr.service.AdminOperationsService;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final AdminOperationsService adminOperationsService;

    @GetMapping("/")
    public String index(Model model) {
        return "/providerView/index";
    }

    @GetMapping("/providerView")
    public String provider(Model model) {
        return "/providerView/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute(new Person());
        return "/register";
    }

    @PostMapping("/register")
    public String registerPerson(@ModelAttribute Person person) {
        adminOperationsService.addPerson(person);
        return "redirect:/register-success";
    }

    @GetMapping("/register-success")
    public String registerSuccess() {
        return "/register-success";
    }

    @GetMapping("/providerView/new-service")
    public String newService(Model model) {
        return "providerView/new-service";
    }

    @GetMapping("/providerView/info")
    public String info(Model model) {
        return "providerView/info";
    }

    @GetMapping("/userView/my")
    public String myOrder(Model model) {
        return "userView/my";
    }

    @GetMapping("/userView/area")
    public String areaOrder(Model model) {
        return "userView/area";
    }

    @GetMapping("/userView/endedAssembling")
    public String endedAssembling(Model model) {
        return "userView/endedAssembling";
    }

    @GetMapping("/userView/assembling")
    public String assemblingOrder(Model model) {
        return "userView/assembling";
    }

    @GetMapping("/admin")
    public String admin(Model model) {return "admin/index";}

    @GetMapping("/admin/new-provider")
    public String adminNewProvider(Model model) {
        return "admin/new-provider";
    }

    @GetMapping("/admin/new-user")
    public String adminNewUser(Model model) {
        return "admin/new-user";
    }

    @GetMapping("/admin/edit-provider")
    public String adminEditProvider(Model model) {
        return "admin/edit-provider";
    }

    @GetMapping("/admin/edit-user")
    public String adminEditUser(Model model) {
        return "admin/edit-user";
    }
}
