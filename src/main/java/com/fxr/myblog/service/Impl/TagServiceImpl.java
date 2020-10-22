package com.fxr.myblog.service.Impl;

import com.fxr.myblog.dao.TagDao;
import com.fxr.myblog.po.Tag;
import com.fxr.myblog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanXiaoRui
 * @date 2020/10/12 8:51
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.getOne(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return tagDao.findTagTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagDao.findAllById(convertToList(ids));
    }

    //将多个字符串转换为数组
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        BeanUtils.copyProperties(tag, tagDao.getOne(id));
        return tagDao.save(tagDao.getOne(id));
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }

}
