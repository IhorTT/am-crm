package net.ukr.tigor.controller;

import net.ukr.tigor.service.SettingsService;
import net.ukr.tigor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "settings")
public class SettingsController {
    @Autowired
    SettingsService settingsService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String get(@RequestParam(value = "tab", required = false, defaultValue = "typeEq") String tab, Model model) {
        model.addAttribute("positions", settingsService.getPositions());
        model.addAttribute("typeEquipment", settingsService.getTypesEquipment());
        model.addAttribute("manufacturers", settingsService.getManufacturers());
        model.addAttribute("tab", tab);
        model.addAttribute("users", userService.getUserList());
        return "/settings";
    }

    @GetMapping("/{thesaurus}/edit")
    public String getThesaurus(
            @PathVariable("thesaurus") String thesaurus,
            @RequestParam(value = "id", required = false, defaultValue = "0") Long id,
            @RequestParam(value = "tab", required = false) String tab,
            Model model) {
        Map<String, Object> param = settingsService.getParamForEditThesaurus(thesaurus, id);
        model.addAttribute("thesaurus", thesaurus);
        model.addAttribute("name", param.get("name"));
        model.addAttribute("object", param.get("object"));
        model.addAttribute("tab", tab);

        return "/edit/thesaurusEdit";
    }

    @PostMapping("/save")
    public String saveThesaurus(
            @RequestParam("thesaurus") String thesaurus,
            @RequestParam(value = "id", required = false, defaultValue = "0") Long id,
            @RequestParam(value = "tab", required = false) String tab,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "btn", required = false) String btn) {
        if (btn.equals("btnOk")) {
            settingsService.saveThesaurus(thesaurus, id, name);
        }

        return "redirect:/settings?tab=" + tab;
    }

    @GetMapping("/{thesaurus}/delete")
    public String delThesaurus(
            @PathVariable("thesaurus") String thesaurus,
            @RequestParam(value = "id", required = false, defaultValue = "0") Long id,
            @RequestParam(value = "tab", required = false) String tab,
            Model model) {

        settingsService.deleteThesaurus(thesaurus, id);

        return "redirect:/settings?tab=" + tab;
    }
}