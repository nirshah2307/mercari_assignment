package com.mercari.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utils {
    protected static final Logger log = LogManager.getLogger(Utils.class);
    public static boolean IsFilePresent(String Dir, String FileName) {
        String location = Dir + FileName;
        File file = new File(location);
        Awaitility.await()
                .pollInterval(5, TimeUnit.SECONDS)
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> file.exists());

        return file.exists();
    }

    public static void ClearOrCreateDir() {
        File file = new File(Constants.CURRENT_USER_DIRECTORY + Constants.DEFAULT_FILE_DOWNLOAD_PATH);
        if (!file.exists()) {
            if (file.mkdir())
                log.info("Dir created");
        } else {
            log.info("Dir already exists");
            String LocAndFileDelcmd = "del /q " + Constants.CURRENT_USER_DIRECTORY + Constants.DEFAULT_FILE_DOWNLOAD_PATH + "*";
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", LocAndFileDelcmd);
                builder.start();
                log.info("Required Files deleted.");
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Error Occurred while deleting file in " + LocAndFileDelcmd + " location.");
            }
        }
    }

    /**
     * Method to Generate Random mobile no
     *
     * @return
     */
    public static String getRandomMobileNumber() {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        return "79393" + randomNumbers;
    }

    /**
     * Method to Generate Random Email Id
     *
     * @return
     */
    public static String getRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@gmail.com";
    }

    /**
     * Method to get random number
     *
     * @param aStart
     * @param aEnd
     * @return randomNumber
     */
    public static int getRandomInteger(int aStart, int aEnd) {
        Random aRandom = new Random();
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        // get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        return (int) (fraction + aStart);
    }

    /**
     * Method to get todays date
     *
     * @return
     */
    public static String todaysDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    public static String todaysDate1() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("ddMMMyyyy");
        return format.format(date);
    }

    public static String Date(String inputDate) throws ParseException {

        //Expected String is : 22-Jul-19 12:15 PM

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(inputDate);
        return new SimpleDateFormat("dd-MMM-yy hh:mm a").format(date);
    }

    /**
     * Method to find last day of previous month
     *
     * @return lastDay
     */
    public static int lastDayOfPreviousMonth() {
        final SimpleDateFormat format = new SimpleDateFormat("dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);

        Date preMonthDate = cal.getTime();
        int lastDay = Integer.parseInt(format.format(preMonthDate));
        log.info("Last day of previous month :: " + lastDay);
        return lastDay;
    }

    /**
     * Method to find date earlier than today
     *
     * @param previousDays
     * @return day
     */
    public static int findDayErlierToday(int previousDays) {
        final SimpleDateFormat format = new SimpleDateFormat("dd");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -previousDays);
        int day = Integer.parseInt(format.format(cal.getTime()));
        log.info(previousDays + " days back date is :: " + day);
        return day;
    }

    /**
     * Method to find date earlier than today
     *
     * @param previousDays
     * @return
     */
    public static String getPrviousDate(int previousDays) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -previousDays);
        return format.format(cal.getTime());
    }


    /**
     * @param stringDate
     * @return localDate
     */
    public static String convertStringToDate(String stringDate) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy")
                .toFormatter(Locale.UK);
        LocalDate ld = LocalDate.parse(stringDate, formatter);
        return ld.toString();
    }

    /**
     * @param count
     * @param data
     * @param dataList
     * @return count
     */
    public static int bulkUpdateRecordsWithDBRecords(int count, List<String[]> data, List<String[]> dataList) {
        for (int i = 0; i < data.size(); i++) {
            if (Arrays.equals(data.get(i), dataList.get(i))) {
                count = count - 1;
                log.info("Count is :: " + count);
            } else {
                log.info("Data not matching in bulk employee update record ::" + i + 1);
                for (int j = 0; j < data.get(i).length; j++) {
                    log.info(data.get(i)[j]);
                    log.info(dataList.get(i)[j]);
                }
            }
        }
        return count;
    }

    /**
     * Method to delete file if exists
     *
     * @param fileToDelete
     */
    public static void deleteFileIfExists(String fileToDelete) {

        try {
            File file = new File(fileToDelete);

            if (file.delete()) {
                log.info(file.getName() + " Was deleted!");
            } else {
                log.info("Delete Operation Failed. Check: " + file);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void uploadFileUsingAppleScript(String path) throws AWTException, IOException {

        Runtime runtime = Runtime.getRuntime();
        String appleScriptCommand = "tell app \"System Events\"\n" +

                "keystroke \"G\" using {command down, shift down}\n" +
                "delay 2\n" +
                "keystroke \"" + path + "\"\n" +
                "delay 1\n" +
                "keystroke return\n" +
                "delay 1\n" +
                "keystroke return\n" +
                "end tell";


        String[] arg = {"osascript", "-e", appleScriptCommand};
        Process process = runtime.exec(arg);

    }

    public static String getFilePathByOs(String filePathInProject) {
        String filePath = "";
        if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            filePath = Constants.CURRENT_USER_DIRECTORY + filePathInProject;
        } else if (System.getProperty("os.name").toUpperCase().contains("MAC")) {
            filePath = Constants.CURRENT_USER_DIRECTORY + filePathInProject;
        }
        return filePath;
    }
}