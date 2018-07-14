package com.chengyu.demo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.chengyu.demo.entity.Standard;
import com.chengyu.demo.service.StandardService;
import com.chengyu.demo.util.Constants;
import com.chengyu.demo.util.PageSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class StandardController {

    @Autowired
    StandardService service;

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/index")
    public String getAll(@RequestParam(value = "pageNo",defaultValue = "1",required = false)Integer pageNo,
                         @RequestParam(value = "condition", required = false) String condition, Model model) {

        System.out.println(">>>>>>>>>>>>>>>条件为：" + condition);
        PageSupport pageSupport = service.getByCondition(condition, pageNo, Constants.pageSize);
        model.addAttribute("pagination", pageSupport);
        if (condition != null) {
            model.addAttribute("condition", condition);
        }
        return "index";
    }

    @RequestMapping(value = "/query")
    public String getAll(@ModelAttribute("condition") String condition,
                         Integer pageNo, Model model) {
        if (pageNo == null) {
            pageNo = 1;
        }
        PageSupport pageSupport = service.getByCondition(condition, pageNo,
                Constants.pageSize);
        model.addAttribute("pagination", pageSupport);
        return "index";
    }



    // 去添加页面
    @RequestMapping(value = "toAdd")
    public String toAdd() {
        return "add2";
    }

    @RequestMapping(value = "addStan")
    public String addStan(@RequestParam(value = "fuJian", required = false) MultipartFile attach,
                          Standard standard, HttpServletRequest request, Model model) {
        System.out.println("前台传递地:" + standard.getStdNum());
        System.out.println("前台传递地:" + standard.getZhname());
        System.out.println("前台传递地:" + standard.getVersion());
        System.out.println("前台传递地:" + standard.getKeys());
        System.out.println("前台传递地:" + standard.getReleaseDate());
        System.out.println("前台传递地:" + standard.getImplDate());
        System.out.println("前台传递地:" + standard.getPackagePath());

        String packagePath = null;
        if (!attach.isEmpty()) {
            // 文件上传路径
            String path = request.getSession().getServletContext()
                    .getRealPath("statics" + File.separator + "uploadfiles");
            System.out.println("文件上传的路径为:" + path);
            String oldFileName = attach.getOriginalFilename();// 原文件名
            System.out.println("源文件名:" + oldFileName);
            String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
            System.out.println("源文件后缀" + prefix);
            int filesize = 500000;
            System.out.println("文件大小为:" + attach.getSize());
            if (attach.getSize() > filesize) {// 上传大小不得超过 500k
                model.addAttribute("msg", "上传大小不得超过 500");
                return "add2";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
                // 防止上传的文件同名
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(100000) + "_P.jpg";
                System.out.println("新文件名:" + fileName);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("msg", "上传失败！");
                    return "add2";
                }
                packagePath = path + File.separator + fileName;
            } else {
                model.addAttribute("msg", "上传图片格式不正确");
                return "add2";
            }
        }
        standard.setPackagePath(packagePath);
        if (service.addStan(standard) > 0) {
            model.addAttribute("msg", "添加成功!");
            return "redirect:/index";
        } else {
            model.addAttribute("msg", "添加失败!");
            return "add2";
        }
    }

    @RequestMapping(value = "isExists")
    @ResponseBody
    public Object stdNumExists(@RequestParam String stdNum) {
        int count = service.stdNumExists(stdNum);
        if (count > 0) {
            return "err";
        } else {
            return "ok";
        }
    }

    @RequestMapping(value = "queryById")
    public String queryById(@RequestParam String id, Model model) {
        Standard standard = service.queryById(id);
        model.addAttribute("standard", standard);
        return "modify";
    }

    @RequestMapping(value = "modify")
    public String modify(Standard standard, HttpServletRequest request,
                         @RequestParam(value = "fuJian", required = false) MultipartFile attach, Model model) {
        if (!attach.isEmpty()) {
            String packagePath = null;
            // 文件上传路径
            String path = request.getSession().getServletContext()
                    .getRealPath("statics" + File.separator + "uploadfiles");
            System.out.println("文件上传的路径为:" + path);
            String oldFileName = attach.getOriginalFilename();// 原文件名
            System.out.println("源文件名:" + oldFileName);
            String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
            System.out.println("源文件后缀" + prefix);
            int filesize = 500000;
            System.out.println("文件大小为:" + attach.getSize());
            if (attach.getSize() > filesize) {// 上传大小不得超过 500k
                model.addAttribute("msg", "上传大小不得超过 500");
                return "add2";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
                // 防止上传的文件同名
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(100000) + "_P.jpg";
                System.out.println("新文件名:" + fileName);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("msg", "上传失败！");
                    return "add2";
                }
                packagePath = path + File.separator + fileName;
            } else {
                model.addAttribute("msg", "上传图片格式不正确");
                return "add2";
            }
            standard.setPackagePath(packagePath);
            if (service.modifyStandard(standard) > 0) {
                model.addAttribute("msg", "修改成功!");
                return "redirect:/index";
            } else {
                model.addAttribute("msg", "修改失败!");
                return "redirect:/queryById";
            }

        } else {
            standard.setPackagePath(null);
            if (service.modifyStandard(standard) > 0) {
                model.addAttribute("msg", "修改成功!");
                return "redirect:/index";
            } else {
                model.addAttribute("msg", "修改失败!");
                return "redirect:/queryById";
            }
        }
    }

    @RequestMapping(value = "download")
    public void downLoad(@RequestParam(value = "id") String id, HttpServletRequest request,
                         HttpServletResponse response) {
        Standard standard = service.queryById(id);
        System.out.println("文件路径为:" + standard.getPackagePath());
        String filename = standard.getPackagePath();
        ServletOutputStream out;
        // 得到要下载的文件
        File file = new File(filename);
        try {
            // 设置响应头，控制浏览器下载该文件
            response.setContentType("multipart/form-data");
            // 获得浏览器信息,并处理文件名
            String headerType = request.getHeader("User-Agent").toUpperCase();
            String fileName = null;
            if (headerType.indexOf("EDGE") > 0 || headerType.indexOf("MSIE") > 0 || headerType.indexOf("GECKO") > 0) {
                fileName = URLEncoder.encode(file.getName(), "UTF-8");
            } else {
                fileName = new String(file.getName().replaceAll(" ", "").getBytes("utf-8"), "iso8859-1");
            }
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            FileInputStream inputStream = new FileInputStream(file);

            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = inputStream.read(buffer);
                // 写到输出流(out)中
                if (b != -1) {
                    out.write(buffer, 0, b);
                }
            }
            inputStream.close();
            out.close();// 关闭输出流
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "deleteStan")
    public String del(Integer[] chk_value) {
        service.delStandard(chk_value);
        return "redirect:/index";
    }

}