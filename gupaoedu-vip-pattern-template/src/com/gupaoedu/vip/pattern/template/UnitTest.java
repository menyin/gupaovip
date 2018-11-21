package com.gupaoedu.vip.pattern.template;

import com.gupaoedu.vip.pattern.template.dao.MemberDao;
import com.gupaoedu.vip.pattern.template.pojo.Member;

import java.util.List;

public class UnitTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
        try {
            List<Member> members = memberDao.equeryAll();
            for (Member member : members) {
                System.out.println(member);
            }

        } catch (Exception e) {
            System.out.println("数据库查询有误");
            e.printStackTrace();
        }
    }
}
