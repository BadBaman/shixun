package com.example.contact.Controller;

import com.example.contact.dao.FileRepository;
import com.example.contact.entity.File;
import com.sun.net.httpserver.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class fileController {
    private static final Logger log= (Logger) LoggerFactory.getLogger(fileController.class);

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(value="/upload",method=POST)
    public String upload(@RequestParam("file")MultipartFile file) throws Exception
    {//上传文件
        //未规定路径，默认保存在项目根路径下

        String name=file.getOriginalFilename();
        long size=file.getSize();
        //Date date = new Date();
        //Timestamp createTime = new Timestamp(date.getTime());
        //Calendar calendar = Calendar.getInstance();
        //DateTime createTime = new DateTime();
        //Timestamp createTime2 = new Timestamp();
        //long time1=20200227;
        //log.info(String.valueOf(createTime));
        File file1=new File();
        //file1.setCreateTime(time1);
        file1.setName(name);
        int i=1;
        for(;i<100;i++){
            List<File> files = fileRepository.findById(i);
            if(files==null)
                break;
        }
        file1.setId(i);
        file1.setSize(size);
        fileRepository.save(file1);
        log.info(file.toString()+" 保存文件信息");
        String filepath="D:/MyProjects/repository/"+file.getOriginalFilename();
        if(file.isEmpty()) return"上传失败";
        //String filepath=file.getOriginalFilename();
        BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(filepath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        return "file";
    }
    @RequestMapping(value="/download",method=RequestMethod.POST)
    public ResponseEntity download(@RequestBody File file1) throws Exception
    {//下载文件
        log.info("download");
        String name=file1.getName()+"";
        log.info(name);
        FileSystemResource file=new FileSystemResource("D:\\MyProjects\\repository\\"+name);
        log.info(file.toString());
        //根据情况换成本地仓库文件夹路径或者云路径
        HttpHeaders headers=new HttpHeaders();
        //在响应头中添加值，设置下载文件默认名称
        headers.add("Content-Disposition","attachment;filename="+name);
        log.info(headers.toString());
        return ResponseEntity.ok().headers(headers).contentLength(file.contentLength()).contentType
                (MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(file.getInputStream()));
    }

    @GetMapping(path="/file")
    public String getAllFiles(Model model) {
        Iterable<File> files=fileRepository.findAll();
        model.addAttribute("index" , files);
        return "file";
    }
    @GetMapping(path="/delete")
    public String deletefile(Model model) {
        Iterable<File> files=fileRepository.findAll();
        model.addAttribute("index" , files);
        return "history";
    }
}
