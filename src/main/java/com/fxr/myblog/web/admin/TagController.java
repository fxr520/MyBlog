package com.fxr.myblog.web.admin;

import com.fxr.myblog.po.Tag;
import com.fxr.myblog.service.TagService;
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
 * @date 2020/10/12 8:53
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    @Transactional
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult bindingResult,
                           @PathVariable Long id,
                           RedirectAttributes redirectAttributes) {
        Tag tag2 = tagService.getTagByName(tag.getName());
        if (tag2 != null) {
            bindingResult.rejectValue("name", "nameError", "不能修改成已有的标签");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        Tag tag1 = tagService.updateTag(id, tag);
        if ( tag1 == null) {
            redirectAttributes.addFlashAttribute("message","更新失败！");
        } else {
            redirectAttributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Tag tag2 = tagService.getTagByName(tag.getName());
        if (tag2 != null) {
            bindingResult.rejectValue("name", "nameError", "不能添加已有的标签");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        Tag tag1 = tagService.saveTag(tag);
        if ( tag1 == null) {
            redirectAttributes.addFlashAttribute("message","新增失败！");
        } else {
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/tags";
    }

}
