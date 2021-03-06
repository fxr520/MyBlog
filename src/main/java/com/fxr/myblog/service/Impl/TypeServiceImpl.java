package com.fxr.myblog.service.Impl;

import com.fxr.myblog.NotFoundException;
import com.fxr.myblog.dao.TypeDao;
import com.fxr.myblog.po.Type;
import com.fxr.myblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author fanXiaoRui
 * @date 2020/10/9 18:14
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getOne(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeDao.findTypeTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeDao.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeDao.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);
    }
}
