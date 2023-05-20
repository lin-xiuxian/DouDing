package com.douding.business.controller.admin;




import com.douding.server.dto.TeacherDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.TeacherService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="讲师";

    @Resource
    private TeacherService teacherService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){
        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        teacherService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto){
        ValidatorUtil.require(teacherDto.getName(), "name");
        ValidatorUtil.length(teacherDto.getName(), "name", 1, 8);
        ValidatorUtil.length(teacherDto.getNickname(), "nickname", 1, 50);
        ValidatorUtil.length(teacherDto.getImage(), "image", 1, 100);
        ValidatorUtil.length(teacherDto.getPosition(), "position", 1, 50);
        ValidatorUtil.length(teacherDto.getMotto(), "motto", 1, 50);
        ValidatorUtil.length(teacherDto.getIntro(), "intro", 1, 500);
        teacherService.save(teacherDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(teacherDto);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        teacherService.delete(id);
        return responseDto;
    }

    @RequestMapping("/all")
    public ResponseDto all(){


        return null;
    }

}//end class