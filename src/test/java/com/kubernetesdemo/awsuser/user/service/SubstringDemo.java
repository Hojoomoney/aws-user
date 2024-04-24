package com.kubernetesdemo.awsuser.user.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SubstringDemo {

    @Test
    void 문자열_분할() {
        String str = "a,b,c";
        // a,b,c,d,e,f
        StringBuilder sb = new StringBuilder(str);
        sb.append(",d,e,f");
        sb.toString().replace(",","");
        assertThat(sb.toString().length()).isEqualTo(3);
    }

    @Test
    void 주민번호로_성별_구분() {
        StringTokenizer st = getStringTokenizer();
        while (st.hasMoreTokens()){
            String ssn = st.nextToken();

            int year = Integer.parseInt(ssn.substring(0,2)); // 주민번호 앞 2자리
            char genderNum = ssn.charAt(7); // 주민번호의 성별번호 추출

            year = getYear(year,genderNum);
            year = getAge(ssn, year);

            String gender = getGender(genderNum);
            log.info("나이 : {} , 성별 : {}", year, gender);
        }
    }

    private static StringTokenizer getStringTokenizer() {
        String human1 = "970618-1";
        String human2 = "950101-2";
        String human3 = "020101-3";
        String human4 = "020101-4";
        String human5 = "730101-5";
        String human6 = "820101-6";
        String human7 = "120101-7";
        String human8 = "050101-8";

        String sb = human1 + "," +
                    human2 + "," +
                    human3 + "," +
                    human4 + "," +
                    human5 + "," +
                    human6 + "," +
                    human7 + "," +
                    human8;
        return new StringTokenizer(sb, ",");
    }

    static int getYear(int year, char genderNum){
        return LocalDate.now().getYear() - switch (genderNum) {
            case '1', '2', '5', '6' -> year + 1900;
            case '3', '4', '7', '8' -> year + 2000;
            default -> 0;
        };
    }
    private String getGender(char genderNum){
        return switch (genderNum){
            case '1', '3', '5', '7' -> "M";
            case '2', '4', '6', '8' -> "F";
            default -> "Error";
        };
    }
    private int getAge(String ssn, int age){
        int current = LocalDate.now().getMonthValue() * 100 + LocalDate.now().getDayOfMonth();
        int birthDay = Integer.parseInt(ssn.substring(2,4)) * 100 + Integer.parseInt(ssn.substring(4,6));

        return birthDay > current ? age-1 : age;
    }

    @Test
    public void getOldAge(){
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        String ssn = "970618-1";
        int birthYear = Integer.parseInt(ssn.substring(0,2));

        birthYear = switch (ssn.charAt(7)) {
            case '1', '2', '5', '6' -> birthYear + 1900;
            case '3', '4', '7', '8' -> birthYear + 2000;
            default -> birthYear + 1800;
        };

        int oldAge = year - birthYear;

        assertThat(oldAge).isEqualTo(27);

        int current = month * 100 + day;
        int birthDay = Integer.parseInt(ssn.substring(2,4)) * 100 + Integer.parseInt(ssn.substring(4,6));

        if(birthDay > current) oldAge--;
        assertThat(oldAge).isEqualTo(26);



    }

    @Test
    public void getAgeUsingLambda(){
        String ssn = "970618-1";
        int current = LocalDate.now().getMonthValue() * 100 + LocalDate.now().getDayOfMonth();
        int birthDay = Integer.parseInt(ssn.substring(2,4)) * 100 + Integer.parseInt(ssn.substring(4,6));
        int age = Stream.of(ssn)
                .map(i -> Integer.parseInt(i.substring(0,2)))
                .map(i -> switch (ssn.charAt(7)) {
                    case '1', '2', '5', '6' -> i + 1900;
                    case '3', '4', '7', '8' -> i + 2000;
                    default -> i + 1800;
                })
                .map(i -> LocalDate.now().getYear() - i)
                .map(i -> birthDay > current ? --i : i)
                .findAny().get();

        assertThat(age).isEqualTo(26);

    }
    @Test
    public void getAgeUsingLambda2(){
        String ssn = "970618-1";
        int year = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int age = Stream.of(ssn)
                .map(i -> Integer.parseInt(i.substring(0,2)))
                .map(i -> switch (ssn.charAt(7)) {
                    case '1', '2', '5', '6' -> i + 1900;
                    case '3', '4', '7', '8' -> i + 2000;
                    default -> i + 1800;
                })
                .map(i -> i * 10000)
                .map(i -> i + Integer.parseInt(ssn.substring(2,6)))
                .map(i -> (year - i)/10000)
                .findAny().get();

        assertThat(age).isEqualTo(26);

    }

}
