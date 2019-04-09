package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.CourseDao;
import com.xawl.ttvideo.dao.UserRecommendDao;
import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.UserRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CourseService {
    @Autowired
    private CourseDao dao;
    @Autowired
    private UserRecommendDao userRecommendDao;

    public Course add(Course course) {
        dao.saveAndFlush(course);
        return course;
    }

    public void delete(Integer cid) {
        dao.deleteById(cid);
    }

    public Course update(Course course) {
        Course one = getOneById(course.getCid());
        if (course.getCaid() != null) {
            one.setCaid(course.getCaid());
        }
        if (course.getCaname() != null) {
            one.setCaname(course.getCaname());
        }
        if (course.getCname() != null) {
            one.setCname(course.getCname());
        }
        if (course.getDel() != null) {
            one.setDel(course.getDel());
        }
        if (course.getImg() != null) {
            one.setImg(course.getImg());
        }
        if (course.getPrice() != null) {
            one.setPrice(course.getPrice());
        }
        if (course.getsPrice() != null) {
            one.setsPrice(course.getsPrice());
        }
        if (course.getSynopsis() != null) {
            one.setSynopsis(course.getSynopsis());
        }
        if (course.getTid() != null) {
            one.setTid(course.getTid());
        }
        if (course.getTname() != null) {
            one.setTname(course.getTname());
        }
        if (course.getSvipFree() != null) {
            one.setSvipFree(course.getSvipFree());
        }
        if (course.getFree() != null) {
            one.setFree(course.getFree());
        }
        if (course.getClickNum() != null) {
            one.setClickNum(course.getClickNum());
        }
        dao.saveAndFlush(one);
        return null;
    }

    public Course getOneById(Integer cid) {
        ExampleMatcher matcher = ExampleMatcher.matching().
                withIgnorePaths("cname", "caid", "caname", "intime", "tid", "tname", "synopsis", "price", "sPrice", "del", "img", "free", "svipFree", "clickNum")
                .withMatcher("cid", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Course> example = Example.of(new Course(cid), matcher);
        Optional<Course> one = dao.findOne(example);
        boolean present = one.isPresent();
        return present ? one.get() : null;
    }

    public Page<Course> findAll(Integer pageNumber, Course course) {
        Sort sort = new Sort(Sort.Direction.DESC, "cid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<Course> example = Example.of(course, ExampleMatcher.matching());
        Page<Course> all = dao.findAll(example, pageable);
        return all;
    }

    public List<Course> findExcellentCourse() {
        Pageable pageable = PageRequest.of(0, 16, new Sort(Sort.Direction.DESC, "clickNum"));
        Page<Course> all = dao.findAll(pageable);
        return all.getContent();
    }

    public List<Course> newCourse() {
        Pageable pageable = PageRequest.of(0, 16, new Sort(Sort.Direction.DESC, "intime"));
        Page<Course> all = dao.findAll(pageable);
        return all.getContent();
    }

    public List<Course> findBycaid(Integer caid) {
        Course course = new Course();
        course.setCaid(caid);
        Pageable pageable = PageRequest.of(0, 16, new Sort(Sort.Direction.DESC, "intime"));
        Page<Course> all = dao.findAll(Example.of(course), pageable);
        return all.getContent();
    }

    public List<Course> find(Integer caid, Integer num) {
        Course course = new Course();
        if (caid != null && caid != 0) {
            course.setCaid(caid);
        }
        if (num != null && num != 0) {
            switch (num) {
                case 1:
                    course.setFree(1);
                    break;
                case 2:
                    course.setFree(0);
                    break;
                case 3:
                    course.setSvipFree(1);
                    break;
            }
        }
        List<Course> all = dao.findAll(Example.of(course));
        return all;
    }

    public List<Course> findByCname(String cname) {
        List<Course> byCnameLike = dao.findByCnameLike(cname);
        return byCnameLike;
    }

    public List<Course> findRecommend(Integer uid) {
        Course course = new Course();
        Optional<UserRecommend> optional = userRecommendDao.findById(uid);
        Pageable pageable;
        List<Course> list = new ArrayList<>();
        if (optional.isPresent()) {
            UserRecommend userRecommend = optional.get();
            if (userRecommend.getCids() != null && userRecommend.getCids().length() > 0) {
                String cids = userRecommend.getCids();
                String[] split = cids.split(",");
                for (String s : split) {
                    Optional<Course> byId = dao.findById(Integer.parseInt(s));
                    if (byId.isPresent()) {
                        list.add(byId.get());
                    }
                }
            }
        }
        pageable = PageRequest.of(0, 6, new Sort(Sort.Direction.DESC, "clickNum"));
        Page<Course> all = dao.findAll(Example.of(course), pageable);
        list.addAll(all.getContent());
        distinct(list);
        return list.subList(0, 6);
    }

    public Course findCourseByCid(Integer cid) {
        Optional<Course> byId = dao.findById(cid);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    @Transactional
    public void giveLike(Integer cid) {
        dao.giveLike(cid);
    }

    public static void distinct(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println(" remove duplicate " + list);
    }
}
