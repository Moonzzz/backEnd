package com.aiit.controller.recycleController;

import com.aiit.pojo.Member;
import com.aiit.service.IRecycleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ActionMember {
    @Autowired
    private IRecycleService recycleService;
    @RequestMapping(value="/toMember.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toMember(HttpServletRequest request) throws UnsupportedEncodingException {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Member> members =this.recycleService.showAllMember((page-1)*limit,limit);
        int count=this.recycleService.showMemberCount();
        System.out.println(count);
        Gson gson=new Gson();
        String result=gson.toJson(members);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }

    @RequestMapping(value = "/toDeleteMember.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int toDeleteMember(String id) throws UnsupportedEncodingException {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i= this.recycleService.deleteMember(Integer.parseInt(id1));
        }
        return i;
    }
    @RequestMapping(value = "/toRevocationMember.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int revocationMember(String id)
    {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i=  this.recycleService.revocationMember(Integer.parseInt(id1));
            this.recycleService.deleteMember(Integer.parseInt(id1));
        }
        return i;
    }
}
