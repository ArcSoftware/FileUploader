package com.theironyard.charlotte.AnonUpload.controllers;

import com.theironyard.charlotte.AnonUpload.entities.AnonFile;
import com.theironyard.charlotte.AnonUpload.entities.DeleteThread;
import com.theironyard.charlotte.AnonUpload.services.AnonFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepo files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)

    //multipart file represents the file we are uploading
    //Httpservletresponse ishow we can modify the response before
    //it is sent back in this case, we are redirecting.
    public void upload(MultipartFile file, HttpServletResponse response, int length) throws IOException {

        File dir = new File("public/files");

        //this call makes directories, if they are missing, from the file path.
        dir.mkdirs();

        //create a new file that starts with "file"(first parameter) and ends with the file name you specified (second parameter).
        //the last parameter says where the new file will be going into
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);

        //this creates an output stream (a way to read the raw contents of the file).
        FileOutputStream fos = new FileOutputStream(f);
        Thread t = new Thread();


        //write to the stream, all the bites in the file upload.
        fos.write(file.getBytes());

        // we have the file in the filesystem, now we need to reference it in the database.
        //                    (name of the temp file, name of the file you uploaded)
        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), length);
        files.save(anonFile);

        //for regular controllers, we would do this:
        //return "redirect:/";

        //this is a restcontroller so we do this:

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() {


        //begin execution of thread.
        for (AnonFile file: files.findAll()) {
            Thread t = new Thread(new DeleteThread(file, files));
            t.start();
        }


        return (List<AnonFile>) files.findAll();
    }

}