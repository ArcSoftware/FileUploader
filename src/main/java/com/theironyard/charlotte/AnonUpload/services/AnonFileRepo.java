package com.theironyard.charlotte.AnonUpload.services;

import com.theironyard.charlotte.AnonUpload.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jake on 5/15/17.
 */
public interface AnonFileRepo extends CrudRepository<AnonFile, Integer> {
}
