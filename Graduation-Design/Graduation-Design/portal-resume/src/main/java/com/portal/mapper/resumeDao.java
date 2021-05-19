package com.portal.mapper;

import com.portal.pojo.Resume;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository
public interface resumeDao {

    Boolean insertByIdSelective(Resume resume);

    Boolean insertByIdAll(Resume resume);

    Boolean deleteById(Integer id);

    Boolean updateByIdSelective(Resume resume);

    Boolean updateByIdAll(Resume resume);

    Resume queryResumeSelective(Resume resume);

    List<Resume> queryResumeAll();
}
