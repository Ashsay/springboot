package com.single.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.single.common.exception.CommonServiceException;
import com.single.common.utils.MD5Util;
import com.single.controller.respvo.AddPersonRespVO;
import com.single.dao.entity.Person;
import com.single.dao.mapper.PersonMapper;
import com.single.service.PersonServiceAPI;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonServiceAPI {

    @Autowired
    private Sid sid;

    @Autowired
    private PersonMapper personMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Person> queryPersons() throws CommonServiceException {
        return personMapper.selectList(null);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addPerson(AddPersonRespVO respVO) throws CommonServiceException {
        Person person = new Person();

        String userId = sid.nextShort();
        String password = MD5Util.encrypt(respVO.getPassword());

        person.setId(userId);
        person.setUsername(respVO.getUsername());
        person.setPassword(password);
        person.setNickname(respVO.getPassword());

        personMapper.insert(person);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updatePerson(String userId) {
        QueryWrapper wrapper = new QueryWrapper();
        Person person = new Person();
        wrapper.eq("id",userId);
        personMapper.update(person,wrapper);
    }

}
