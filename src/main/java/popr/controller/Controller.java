package popr.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {


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
    public String admin(Model model) {
        return "admin/index";
    }

    @GetMapping("/admin/new-provider")
    public String adminNewProvider(Model model) {
        return "admin/new-provider";
    }

    @GetMapping("/admin/new-user")
    public String adminNewUser(Model model) {
        return "admin/new-user";
    }

}
