package com.portal.service.lmp;

import com.portal.mapper.ResumesDao;
import com.portal.pojo.*;
import com.portal.service.ResumesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ResumesServiceImp implements ResumesService {

    @Autowired
    private ResumesDao resumesDao;

    @Override
    public List<Resumes> queryResumesAll() {
        return resumesDao.queryResumesAll();
    }

    @Override
    public List<Resumes> queryResumesDelAll() {
        return resumesDao.queryResumesDelAll();
    }

    @Override
    public Integer countResumesNumAll() {
        return resumesDao.countResumesNumAll();
    }

    @Override
    public Integer countResumesNumDel() {
        return resumesDao.countResumesNumDel();
    }

    @Override
    public List<UserResumes> getDelResumesByUserId(Integer user_id) {
        return resumesDao.getDelResumesByUserId(user_id);
    }

    @Override
    public List<Resumes> queryResumesSkillsByUserId(Integer user_id) {
        return resumesDao.queryResumesSkillsByUserId(user_id);
    }

    @Override
    public List<Resumes> queryResumesPerformancesByUserId(Integer user_id) {
        return resumesDao.queryResumesPerformancesByUserId(user_id);
    }

    @Override
    public List<Resumes> queryResumesExperiencesByUserId(Integer user_id) {
        return resumesDao.queryResumesExperiencesByUserId(user_id);
    }

    @Override
    public List<Resumes> queryResumesAwardsByUserId(Integer user_id) {
            return resumesDao.queryResumesAwardsByUserId(user_id);
    }

    @Override
    public Boolean uploadResumesImg(Resumes resumes) {
        return resumesDao.uploadResumesImg(resumes);
    }

    @Override
    public List<UserResumes> queryUserAllResumesByUserId(Integer user_id) {
        return resumesDao.queryUserAllResumesByUserId(user_id);
    }

    @Override
    public Integer insertResumesBasic(Resumes resumes) {
        return resumesDao.insertResumesBasic(resumes);
    }

    @Override
    public Integer addResumesSkills(Skill skill) {
        return resumesDao.addResumesSkills(skill);
    }

    @Override
    public Integer addResumesPerformance(Performance performance) {
        return resumesDao.addResumesPerformance(performance);
    }

    @Override
    public Integer addResumesExperience(Experience experience) {
        return resumesDao.addResumesExperience(experience);
    }

    @Override
    public Integer addResumesAward(Award award) {
        return resumesDao.addResumesAward(award);
    }

    @Override
    public Integer getResumesIdByUserId(Integer user_id) {
        return resumesDao.getResumesIdByUserId(user_id);
    }

    @Override
    public Integer updateResumesBasis(Resumes resumes) {
        return resumesDao.updateResumesBasis(resumes);
    }

    @Override
    public Integer updateResumesSkills(@RequestBody List<Skill> skills) {
        return resumesDao.updateResumesSkills(skills);
    }

    @Override
    public Integer updateResumesAwards(@RequestBody List<Award> awards) {
        return resumesDao.updateResumesAwards(awards);
    }

    @Override
    public Integer updateResumesExperiences(@RequestBody List<Experience> experiences) {
        return resumesDao.updateResumesExperiences(experiences);
    }

    @Override
    public Integer updateResumesPerformances(@RequestBody List<Performance> performances) {
        return resumesDao.updateResumesPerformances(performances);
    }

    @Override
    public Integer deleteResumesByResumeId(Integer resume_id) {
        return resumesDao.deleteResumesByResumeId(resume_id);
    }

    @Override
    public Integer deleteResumesSkillOneBySkillId(Integer skill_id) {
        return resumesDao.deleteResumesSkillOneBySkillId(skill_id);
    }

    @Override
    public Integer deleteResumesSkillsAllByResumeId(Integer resume_id) {
        return resumesDao.deleteResumesSkillsAllByResumeId(resume_id);
    }

    @Override
    public Integer deleteResumesAwardOneByAwardId(Integer award_id) {
        return resumesDao.deleteResumesAwardOneByAwardId(award_id);
    }

    @Override
    public Integer deleteResumesAwardsAllByResumeId(Integer resume_id) {
        return resumesDao.deleteResumesAwardsAllByResumeId(resume_id);
    }

    @Override
    public Integer deleteResumesPerformanceOneByPerformanceId(Integer performance_id) {
        return resumesDao.deleteResumesPerformanceOneByPerformanceId(performance_id);
    }

    @Override
    public Integer deleteResumesPerformancesAllByResumeId(Integer resume_id) {
        return resumesDao.deleteResumesPerformancesAllByResumeId(resume_id);
    }

    @Override
    public Integer deleteResumesExperienceOneByExperienceId(Integer program_id) {
        return resumesDao.deleteResumesExperienceOneByExperienceId(program_id);
    }

    @Override
    public Integer deleteResumesExperiencesAllByResumeId(Integer resume_id) {
        return resumesDao.deleteResumesExperiencesAllByResumeId(resume_id);
    }

    @Override
    public Integer falseDeleteResumesByResumesId(Integer resume_id) {
        return resumesDao.falseDeleteResumesByResumesId(resume_id);
    }

    @Override
    public Integer reuseResumesByResumesId(Integer resume_id) {
        return resumesDao.reuseResumesByResumesId(resume_id);
    }

    @Override
    public Integer countUserResumesNumber(Integer user_id) {
        return resumesDao.countUserResumesNumber(user_id);
    }

    @Override
    public Integer updateResumesBasisSelective(Resumes resumes) {
        return resumesDao.updateResumesBasisSelective(resumes);
    }

    @Override
    public Integer updateResumesExperiencesOne(Experience experience) {
        return resumesDao.updateResumesExperiencesOne(experience);
    }

    @Override
    public Integer updateResumesSkillsOne(Skill skill) {
        return resumesDao.updateResumesSkillsOne(skill);
    }

    @Override
    public Integer updateResumesPerformancesOne(Performance performance) {
        return resumesDao.updateResumesPerformancesOne(performance);
    }

    @Override
    public Integer updateResumesAwardsOne(Award award) {
        return resumesDao.updateResumesAwardsOne(award);
    }


}


