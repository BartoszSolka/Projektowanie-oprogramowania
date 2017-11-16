package popr.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/new-service")
    public String newService(Model model) {
        return "new-service";
    }

    @GetMapping("/info")
    public String info(Model model) {
        return "info";
    }

    @GetMapping("/order/my")
    public String myOrder(Model model) {
        return "order/my";
    }

    @GetMapping("/order/area")
    public String areaOrder(Model model) {
        return "order/area";
    }

    @GetMapping("/order/assembling")
    public String assemblingOrder(Model model) {
        return "order/assembling";
    }

    @GetMapping("/order/mark")
    public String markOrder(Model model) {
        return "order/mark";
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
