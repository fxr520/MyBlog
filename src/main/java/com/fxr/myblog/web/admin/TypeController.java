package com.fxr.myblog.web.admin;

import com.fxr.myblog.po.Type;
import com.fxr.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @author fanXiaoRui
 * @date 2020/10/9 19:54
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    @Transactional
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Type type2 = typeService.getTypeByName(type.getName());
        if (type2 != null) {
            bindingResult.rejectValue("name", "nameError", "不能修改为已有的分类");
        }
        if (bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        Type type1 = typeService.updateType(id, type);
        if ( type1 == null) {
            redirectAttributes.addFlashAttribute("message","更新失败！");
        } else {
            redirectAttributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Type type2 = typeService.getTypeByName(type.getName());
        if (type2 != null) {
            bindingResult.rejectValue("name", "nameError", "不能添加已有的分类");
        }
        if (bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if ( type1 == null) {
            redirectAttributes.addFlashAttribute("message","新增失败！");
        } else {
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/types";
    }

}
