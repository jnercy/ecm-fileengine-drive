//package com.nextcont.drive;
//
//import com.github.tobato.fastdfs.FdfsClientConfig;
//import com.github.tobato.fastdfs.domain.StorePath;
//import com.github.tobato.fastdfs.service.AppendFileStorageClient;
//import com.nextcont.drive.utils.IdGenService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * Created with IntelliJ IDEA.
// * User: Wangxudong
// * Date: 2017/3/9
// * Time: 14:22
// * To change this template use File | Settings | File Templates.
// */
//@Import(FdfsClientConfig.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class DfsTest {
//
//
//    @Autowired
//    private AppendFileStorageClient storageClient;
//
//    @Autowired
//    private IdGenService idGenService;
//
//    @Test
//    public void test() throws FileNotFoundException {
//        File file = new File("D://6m.avi.1.part");
//        InputStream in = new FileInputStream(file);
//        StorePath result = storageClient.uploadAppenderFile("group1",in,file.length(),"6m.avi");
//        System.out.println(result.getPath());
//        storageClient.appendFile("group1","M00/00/3F/wKgPzVjBC8-EA_gaAAAAALCu_Cg.6m.avi",in,file.length());
//    }
//
//    @Test
//    public void testAppend() throws FileNotFoundException {
//        File file = new File("D://6m.avi.2.part");
//        InputStream in = new FileInputStream(file);
//        storageClient.appendFile("group1","M00/00/3F/wKgPzVjBC8-EA_gaAAAAALCu_Cg.6m.avi",in,file.length());
//    }
//
//
//    @Test
//    public void testId(){
//        List<String> authorities = Arrays.asList("b","c");
//
//
//        List<String> configAttributes = Arrays.asList("a","b","c","d","e");
//
//        authorities
//                .stream()
//                .filter(gra->gra.equals("b"))
//                .map(g-> configAttributes.stream().filter(g::equals).findFirst())
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }
//
//
//
//}
