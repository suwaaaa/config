package com.portal.controller;

import com.portal.pojo.*;
import com.portal.service.ResumesService;
import com.portal.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class ResumesController {

    @Autowired
    private ResumesService resumesService;

    @RequestMapping(value = "/resumes/queryResumesAll",method = RequestMethod.GET)
    public List<Resumes> queryResumesAll() {
        return resumesService.queryResumesAll();
    }

    @RequestMapping(value = "/resumes/queryResumesDelAll",method = RequestMethod.GET)
    public List<Resumes> queryResumesDelAll() {
        return resumesService.queryResumesDelAll();
    }

    @RequestMapping(value = "/resume/getDelResumesByUserId",method = RequestMethod.GET)
    public List<UserResumes> getDelResumesByUserId(@RequestParam("user_id") Integer user_id) {
        return resumesService.getDelResumesByUserId(user_id);
    }

    @RequestMapping(value = "/resumes/countResumesNumAll",method = RequestMethod.GET)
    public Integer countResumesNumAll() {
        return resumesService.countResumesNumAll();
    }

    @RequestMapping(value = "/resumes/countResumesNumDel",method = RequestMethod.GET)
    public Integer countResumesNumDel() {
        return resumesService.countResumesNumDel();
    }

    @RequestMapping(value = "/resumes/queryResumesSkillsByUserId",method = RequestMethod.GET)
    public List<Resumes> queryResumesSkillsByUserId(@RequestParam("user_id") Integer user_id) {
        return resumesService.queryResumesSkillsByUserId(user_id);
    }

    @RequestMapping(value = "/resumes/queryResumesPerformancesByUserId",method = RequestMethod.GET)
    public List<Resumes> queryResumesPerformancesByUserId(@RequestParam("user_id") Integer user_id) {
        return resumesService.queryResumesPerformancesByUserId(user_id);
    }

    @RequestMapping(value = "/resumes/queryResumesExperiencesByUserId",method = RequestMethod.GET)
    public List<Resumes> queryResumesExperiencesByUserId(@RequestParam("user_id") Integer user_id) {
        return resumesService.queryResumesExperiencesByUserId(user_id);
    }

    @RequestMapping(value = "/resumes/queryResumesAwardsByUserId",method = RequestMethod.GET)
    public List<Resumes> queryResumesAwardsByUserId(@RequestParam("user_id") Integer user_id) {
        return resumesService.queryResumesAwardsByUserId(user_id);
    }



    @Value("${file.path}")
    private String filePath;
    @RequestMapping(value = "/resumes/uploadResumesImg", method = RequestMethod.POST)
    @ResponseBody
    public Boolean uploadResumesImg(
            @RequestParam("resumepho_url") MultipartFile file, HttpServletRequest request
    ) {
        Resumes resumes = new Resumes();
        User user = new User();
        System.out.println("文件的地址是---  ---" + filePath);
        String userrealname = request.getParameter("name").replace(" ", "");
        System.out.println("使用的用户名字是---  ---" + userrealname);
        Integer resumeId = Integer.parseInt(request.getParameter("id"));
        System.out.println("使用的用户简历id是---  ---" + resumeId);
        resumes.setResume_id(resumeId);
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        fileName = UUID.randomUUID() + suffixName;
        fileName = userrealname+ UUID.randomUUID()+ suffixName;
        resumes.setResumepho_url(fileName);
        File dest = new File(filePath + fileName);


        if (file.isEmpty() && file.getSize() > 30720) {
            return false;
        }
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return resumesService.uploadResumesImg(resumes);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resumesService.uploadResumesImg(resumes);
    }



    @RequestMapping(value = "/resumes/queryUserAllResumesByUserId",method = RequestMethod.GET)
    public List<UserResumes> queryUserAllResumesByUserId(@RequestParam("user_id") Integer user_id) {
        return resumesService.queryUserAllResumesByUserId(user_id);
    }

    @RequestMapping(value = "/resumes/insertResumesBasicById",method = RequestMethod.PUT)
    public Integer insertResumesBasic(@RequestBody Resumes resumes) {
        resumes.setResume_creatime(new Date());
        return resumesService.insertResumesBasic(resumes);
    }



    @RequestMapping(value = "/resumes/addResumesSkills",method = RequestMethod.PUT)
    public Object addResumesSkills(@RequestBody Skill skill) {
        Integer i = resumesService.addResumesSkills(skill);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("addResumesSkills fail");
    }

    @RequestMapping(value = "/resumes/addResumesPerformance",method = RequestMethod.PUT)
    public Object addResumesPerformance(@RequestBody Performance performance) {
        Integer i = resumesService.addResumesPerformance(performance);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("addResumesPerformance fail");
    }

    @RequestMapping(value = "/resumes/addResumesExperience",method = RequestMethod.PUT)
    public Object addResumesExperience(@RequestBody Experience experience) {
        Integer i = resumesService.addResumesExperience(experience);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("addResumesExperience fail");
    }

    @RequestMapping(value = "/resumes/addResumesAward",method = RequestMethod.PUT)
    public Object addResumesAward(@RequestBody Award award) {
        Integer i = resumesService.addResumesAward(award);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("addResumesAward fail");
    }

    @RequestMapping(value = "/resumes/getResumesIdByUserId",method = RequestMethod.GET)
    public Object getResumesIdByUserId(@RequestParam("user_id") Integer user_id) {
        Integer i = resumesService.getResumesIdByUserId(user_id);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("getResumesIdByUserId fail");
    }

    @RequestMapping(value = "/resumes/updateResumesBasis",method = RequestMethod.PUT)
    public Integer updateResumesBasis(@RequestBody Resumes resumes) {
        resumes.setResume_creatime(new Date());
        return resumesService.updateResumesBasis(resumes);
    }

    @RequestMapping(value = "/resumes/updateResumesBasisSelective",method = RequestMethod.POST)
    public Integer updateResumesBasisSelective(@RequestBody Resumes resumes) {
        resumes.setResume_creatime(new Date());
//        System.out.println("Resume_id------"+resumes.getResume_id()+"-----"+resumes.getResumepho_url());
        return resumesService.updateResumesBasisSelective(resumes);
    }


    @RequestMapping(value = "/resumes/updateResumesSkills",method = RequestMethod.POST)
    public Object updateResumesSkills(@RequestBody List<Skill> skills) {
        Integer i = resumesService.updateResumesSkills(skills);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("updateResumesSkills fail");
    }

    @RequestMapping(value = "/resumes/updateResumesAwards",method = RequestMethod.POST)
    public Object updateResumesAwards(@RequestBody List<Award> awards) {
        Integer i = resumesService.updateResumesAwards(awards);
        if (i == 1 ){
            return JsonData.buildSuccess(resumesService.updateResumesAwards(awards));
        }
        return JsonData.buildError("updateResumesAwards fail");
    }

    @RequestMapping(value = "/resumes/updateResumesExperiences",method = RequestMethod.POST)
    public Object updateResumesExperiences(@RequestBody List<Experience> experiences) {
        Integer i = resumesService.updateResumesExperiences(experiences);
        if (i == 1 ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("updateResumesExperiences fail");
    }

    @RequestMapping(value = "/resumes/updateResumesPerformances",method = RequestMethod.POST)
    public Integer updateResumesPerformances(@RequestBody List<Performance> performances) {
        return resumesService.updateResumesPerformances(performances);
    }

    @RequestMapping(value = "/resumes/deleteResumesByResumeId",method = RequestMethod.GET)
    public Integer deleteResumesByResumeId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.deleteResumesByResumeId(resume_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesSkillOneBySkillId",method = RequestMethod.DELETE)
    public Integer deleteResumesSkillOneBySkillId(@RequestParam("skill_id") Integer skill_id) {
        return resumesService.deleteResumesSkillOneBySkillId(skill_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesSkillsAllByResumeId",method = RequestMethod.DELETE)
    public Integer deleteResumesSkillsAllByResumeId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.deleteResumesSkillsAllByResumeId(resume_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesAwardOneByAwardId",method = RequestMethod.DELETE)
    public Integer deleteResumesAwardOneByAwardId(@RequestParam("award_id") Integer award_id) {
        return resumesService.deleteResumesAwardOneByAwardId(award_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesAwardsAllByResumeId",method = RequestMethod.DELETE)
    public Integer deleteResumesAwardsAllByResumeId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.deleteResumesAwardsAllByResumeId(resume_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesPerformanceOneByPerformanceId",method = RequestMethod.DELETE)
    public Integer deleteResumesPerformanceOneByPerformanceId(@RequestParam("performance_id") Integer performance_id) {
        return resumesService.deleteResumesPerformanceOneByPerformanceId(performance_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesPerformancesAllByResumeId",method = RequestMethod.DELETE)
    public Integer deleteResumesPerformancesAllByResumeId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.deleteResumesPerformancesAllByResumeId(resume_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesExperienceOneByProgram_id",method = RequestMethod.DELETE)
    public Integer deleteResumesExperienceOneByExperienceId(@RequestParam("program_id") Integer program_id) {
        return resumesService.deleteResumesExperienceOneByExperienceId(program_id);
    }

    @RequestMapping(value = "/resumes/deleteResumesExperiencesAllByResumeId",method = RequestMethod.DELETE)
    public Integer deleteResumesExperiencesAllByResumeId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.deleteResumesExperiencesAllByResumeId(resume_id);
    }



    @RequestMapping(value = "/resumes/falseDeleteResumesByResumesId",method = RequestMethod.GET)
    public Integer falseDeleteResumesByResumesId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.falseDeleteResumesByResumesId(resume_id);
    }

    @RequestMapping(value = "/resumes/reuseResumesByResumesId",method = RequestMethod.GET)
    public Integer reuseResumesByResumesId(@RequestParam("resume_id") Integer resume_id) {
        return resumesService.reuseResumesByResumesId(resume_id);
    }

    @RequestMapping(value = "/resumes/countUserResumesNumber",method = RequestMethod.GET)
    public Integer countUserResumesNumber(@RequestParam("user_id") Integer user_id) {
        return resumesService.countUserResumesNumber(user_id);
    }

    @RequestMapping(value = "/resumes/changeResumesStatus",method = RequestMethod.GET)
    public Integer changeResumesStatus(Integer resume_id, Integer oldresume_id) {
        Integer i = resumesService.reuseResumesByResumesId(resume_id);
        return resumesService.falseDeleteResumesByResumesId(oldresume_id);
    }


    @RequestMapping(value = "/resumes/updateResumesExperiencesOne",method = RequestMethod.POST)
    public Integer updateResumesExperiencesOne(@RequestBody Experience experience) {
        return resumesService.updateResumesExperiencesOne(experience);
    }

    @RequestMapping(value = "/resumes/updateResumesSkillsOne",method = RequestMethod.POST)
    public Integer updateResumesSkillsOne(@RequestBody Skill skill) {
        return resumesService.updateResumesSkillsOne(skill);
    }

    @RequestMapping(value = "/resumes/updateResumesPerformancesOne",method = RequestMethod.POST)
    public Integer updateResumesPerformancesOne(@RequestBody Performance performance) {
        return resumesService.updateResumesPerformancesOne(performance);
    }

    @RequestMapping(value = "/resumes/updateResumesAwardsOne",method = RequestMethod.POST)
    public Integer updateResumesAwardsOne(@RequestBody Award award) {
        return resumesService.updateResumesAwardsOne(award);
    }

}
