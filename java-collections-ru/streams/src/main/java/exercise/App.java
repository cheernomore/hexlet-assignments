package exercise;

import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails ) {
        if (emails.size() == 0) {
            return 0;
        }

        return emails.stream()
                .filter(email -> isEmailFree(email))    //  App::isEmailFree
                .count();
    }

    public static boolean isEmailFree(String email) {
        return email.endsWith("gmail.com") || email.endsWith("hotmail.com") || email.endsWith("yandex.ru");
    }
}
// END
