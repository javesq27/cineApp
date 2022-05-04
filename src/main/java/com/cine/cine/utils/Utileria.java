package com.cine.cine.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

    public static List<String> getNextDays(int count) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //fecha de hoy
        Date start = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, count); // Siguientes N dias desde ahora
        Date endDate = cal.getTime();

        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(start);
        List<String> nextDays = new ArrayList<String>();
        while(!gcal.getTime().after(endDate)) {
            Date d = gcal.getTime();
            gcal.add(Calendar.DATE, 1);
            nextDays.add(sdf.format(d));
        }
        return nextDays;

    }

    public static String guardarArchivo(MultipartFile multiPart, String ruta) {

        String nombreOriginal = multiPart.getOriginalFilename();
        nombreOriginal.replace(" ", "-");
        try {
            File imageFile = new File(ruta + nombreOriginal);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            multiPart.transferTo(imageFile);
            return nombreOriginal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    
}
