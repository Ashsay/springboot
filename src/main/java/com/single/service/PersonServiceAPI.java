package com.single.service;

import com.single.common.exception.CommonServiceException;
import com.single.controller.respvo.AddPersonRespVO;
import com.single.dao.entity.Person;

import java.util.List;

public interface PersonServiceAPI {

    public List<Person> queryPersons() throws CommonServiceException;

    public void addPerson(AddPersonRespVO respVO) throws CommonServiceException;

    public void updatePerson(String userId);

}
