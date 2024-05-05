
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        Пример данных для ввода

        Петрович Петр Сергеевич 10.10.2000 789787 m
        Петрович Алексей Иванович 05.01.1986 784841 m
        Ивановна Марина Федеровна 08.12.1992 765563 f
         */

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine(); // считывание данных
        String[] user = userInput.split(" "); // деление строки
        int resCheck = CheckInput(user); // проверка количества элементов в введенной строке
        switch (resCheck){ // бросаем исключение, если проверка на кол-во эл-нтов не прошла
            case -1: throw new RuntimeException("Введено меньше данных, чем необхдимо");
            case -2: throw new RuntimeException("Введено меньше данных, чем необхдимо");
        }

        CheckElement(user);
        SaveInput(user);



    }

    public static int CheckInput(String[] array){
        if(array.length < 6) {
            return -1;
        } if (array.length > 6){
            return -2;
        } else {
            return 0;
        }
    }

    public static void CheckElement(String[] array){

            // Проверка фамилии на содержание цифр
            if(array[0].matches(".*\\d.*")){ // если строка содержит цифру от 0 до 9, то true
                throw new RuntimeException("Фамилия введена неверно");
            }
            // Проверка имени на содержание цифр
            if(array[1].matches(".*\\d.*")) { // если строка содержит цифру от 0 до 9, то true
                throw new RuntimeException("Имя введено неверно");
            }
            // Проверка отчества на содержание цифр
            if(array[2].matches(".*\\d.*")){ // если строка содержит цифру от 0 до 9, то true
            throw new RuntimeException("Отчество введено неверно");
            }
            // Проверка введения даты в нужном формате и введения номера телефона
            try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy");
            Date date = (Date) simpleDateFormat.parse(array[3]);
            int phoneNumber = Integer.parseInt(array[4]);
            }catch (ParseException e){
                System.out.println("Неправильно введена дата рождения");
            } catch (NumberFormatException e){
                System.out.println("Неправильно введен номер");
            }
            // Проверка ввдения пола
            if(!array[5].equals("f") & !array[5].equals("m")){
                throw new RuntimeException("Пол введен неверно");
            }

    }

    public static void SaveInput(String[]array)  {

        try (FileWriter writerfile = new FileWriter(array[0] + ".txt", true)){
            for (String s : array) {
                writerfile.write("<" + s + ">");
            }
            writerfile.write("\r\n");
        }catch (IOException e){
            System.out.println("При записи данных произошла ошибка");
        }

    }
}