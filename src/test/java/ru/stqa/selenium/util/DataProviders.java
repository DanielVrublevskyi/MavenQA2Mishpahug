package ru.stqa.selenium.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> singleFilterByHoliday() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/DSingleFilterByHoliday.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> singleFilterByHolidayWeb() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/DSingleFilterHolidayWeb.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginNegative() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/DLoginNegative.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }


    @DataProvider
    public static Iterator<Object[]> anotherNegativeLogin() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"marinaLongLongLong@gmail.com", "marinaLongLongLong"});
        data.add(new Object[]{"MaRina@123", "MaRina"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> randomUsers() {
        List<Object[]> data = new ArrayList();

        for(int i = 0; i < 5; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }

        return data.iterator();
    }

    private Object generateRandomPassword() {
        return "pass" + (new Random()).nextInt();
    }

    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt()+"@gmail.com";
    }

    @DataProvider
    public Iterator<Object[]> randomStringUsers() {
        List<Object[]> data = new ArrayList();

        for(int i = 0; i < 2; ++i) {
            data.add(new Object[]{this.generateRandomString(5), this.generateRandomString(10)});
        }

        return data.iterator();
    }

    private String generateRandomString(int smb) {
        String str = "";
        char ch;
        int number;
        Random gen = new Random();
        for(int i=0; i < smb; i++){
            number = ' ' + gen.nextInt('}' - ' ' +1);
            str = str + (char)number;
        }

        return str;
    }
}

