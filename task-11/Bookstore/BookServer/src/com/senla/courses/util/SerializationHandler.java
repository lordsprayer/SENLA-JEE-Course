package com.senla.courses.util;

import com.senla.courses.exception.ServiceException;
import com.senla.courses.model.AId;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializationHandler {

    private static final Logger log = Logger.getLogger(SerializationHandler.class.getName());
    private static final String PATH_TO_FILE = PropertiesHandler.getProperties("server.serialization.path_to_file")
            .orElseThrow(() -> new ServiceException("Serialization file path not found"));

    @SafeVarargs
    public static void serialize(List<? extends AId> ... entities){
        List<List<? extends AId>> marshalingList = List.of(entities);
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_FILE))){
            outStream.writeObject(marshalingList);
        } catch (IOException e){
            log.log(Level.WARNING, "Serialization to file failed: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> deserialize(Class<T> tClass){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_TO_FILE))){
            List<List<? extends AId>> list = (List<List<? extends AId>>) inputStream.readObject();
            for(List<? extends AId> entities: list){
                if(!entities.isEmpty() && entities.get(0).getClass().equals(tClass)){
                    return (ArrayList<T>) entities;
                }
            }
        } catch (IOException | ClassNotFoundException e){
            log.log(Level.WARNING, "Deserialization from file failed: " + e.getMessage());
            throw new ServiceException(e);

        }
        return new ArrayList<>();
    }


}
