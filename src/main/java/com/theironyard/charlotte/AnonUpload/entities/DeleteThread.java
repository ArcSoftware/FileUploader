package com.theironyard.charlotte.AnonUpload.entities;

import com.theironyard.charlotte.AnonUpload.services.AnonFileRepo;

import java.io.File;

/**
 * Created by Jake on 5/15/17.
 */
public class DeleteThread implements Runnable {
    private AnonFile file;
    private AnonFileRepo repo;

    public DeleteThread(AnonFile file, AnonFileRepo repo) {
        this.file = file;
        this.repo = repo;
    }


    @Override
    public void run() {
        try {
            System.out.println("Starting a time for " + file.getLength());
            Thread.sleep(file.getLength());
            System.out.println("Time is up! Deleting " + file + ".");

            File fileToDelete = new File ("public/files/" + file.getFilename());
            fileToDelete.delete();
            repo.delete(file);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
