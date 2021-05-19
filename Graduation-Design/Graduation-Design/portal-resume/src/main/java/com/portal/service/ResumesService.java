package com.portal.service;

import com.portal.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResumesService {

    List<Resumes> queryResumesAll();

    List<Resumes> queryResumesDelAll();

    Integer countResumesNumAll();

    Integer countResumesNumDel();

    List<UserResumes> getDelResumesByUserId(Integer user_id);

    List<Resumes> queryResumesSkillsByUserId(Integer user_id);

    List<Resumes> queryResumesPerformancesByUserId(Integer user_id);

    List<Resumes> queryResumesExperiencesByUserId(Integer user_id);

    List<Resumes> queryResumesAwardsByUserId(Integer user_id);

    Boolean uploadResumesImg(Resumes resumes);

    List<UserResumes> queryUserAllResumesByUserId(Integer user_id);

    Integer insertResumesBasic(Resumes resumes);

    Integer addResumesSkills(Skill skill);

    Integer addResumesPerformance(Performance performance);

    Integer addResumesExperience(Experience experience);

    Integer addResumesAward(Award award);

    Integer getResumesIdByUserId(Integer user_id);

    Integer updateResumesBasis(Resumes resumes);

    Integer updateResumesSkills(@Param(value = "skills") List<Skill> skills);

    Integer updateResumesAwards(@Param(value = "awards") List<Award> awards);

    Integer updateResumesExperiences(@Param(value = "experiences") List<Experience> experiences);

    Integer updateResumesPerformances(@Param(value = "experiences") List<Performance> performances);

    Integer deleteResumesByResumeId(Integer resume_id);

    Integer deleteResumesSkillOneBySkillId(Integer skill_id);

    Integer deleteResumesSkillsAllByResumeId(Integer resume_id);

    Integer deleteResumesAwardOneByAwardId(Integer award_id);

    Integer deleteResumesAwardsAllByResumeId(Integer resume_id);

    Integer deleteResumesPerformanceOneByPerformanceId(Integer performance_id);

    Integer deleteResumesPerformancesAllByResumeId(Integer resume_id);

    Integer deleteResumesExperienceOneByExperienceId(Integer program_id);

    Integer deleteResumesExperiencesAllByResumeId(Integer resume_id);



    Integer falseDeleteResumesByResumesId(Integer resume_id);

    Integer reuseResumesByResumesId(Integer resume_id);

    Integer countUserResumesNumber(Integer user_id);


    Integer updateResumesBasisSelective(Resumes resumes);



    Integer updateResumesExperiencesOne(Experience experience);

    Integer updateResumesSkillsOne(Skill skill);

    Integer updateResumesPerformancesOne(Performance performance);

    Integer updateResumesAwardsOne(Award award);
}
