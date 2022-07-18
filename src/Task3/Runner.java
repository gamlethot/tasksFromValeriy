package Task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class UserOnline {
        private LocalDate startSession;
        private LocalDate endSession;

    }

    public class Runner {

        //Найти дату когда был самый большой онлайн (вернуть какая дата, вернуть кол-во людей)
        //НЕОБЯЗАТЕЛЬНО (СЛОЖНО), если хотите можете попробовать найти самый большой по онлайну период времени
        public static void main(String[] args) {
            Random random = new Random();
            int i = random.nextInt(100, 500);
            List<UserOnline> usersOnline = Stream.generate(Runner::generateUser).limit(i).toList();
            //ТУТ ваш код (решение необязательно через стримы)

            Map<Integer, LocalDate> usersOnlineQuantityAndDate = usersOnlineQuantityAndDate(usersOnline);
            LocalDate maxUsersOnlineDate = maxUsersOnlineDate(usersOnlineQuantityAndDate);
            int maxUsersOnlineQuantity = maxUsersOnlineQuantity(usersOnlineQuantityAndDate);
            UserOnline longestUserOnline = longestUserOnline(usersOnline);
            long maxOnlineDuration = maxOnlineDuration(maxUsersOnlineDate, usersOnline);
            long longestUserWasOnlineDays = longestUserWasOnlineDays(longestUserOnline);

            consoleResultsOutput(usersOnline, maxUsersOnlineDate, maxUsersOnlineQuantity, longestUserOnline, maxOnlineDuration, longestUserWasOnlineDays);
        }

        private static void consoleResultsOutput(List<UserOnline> usersOnline, LocalDate maxUsersOnlineDate, int maxUsersOnlineQuantity, UserOnline longestUserOnline, long maxOnlineDuration, long longestUserWasOnlineDays) {
            System.out.println("ALL USERS: \n" + usersOnline);
            System.out.println("MAX users online: " + maxUsersOnlineQuantity +" users.");
            System.out.println("Date of MAX users online: " + maxUsersOnlineDate);
            System.out.println("Duration of MAX users online was: " + maxOnlineDuration+" day(s).");
            System.out.println("Longest ONLINE user - "+ longestUserOnline);
            System.out.println("He was online for - " + longestUserWasOnlineDays +" days.");
        }

        public static UserOnline longestUserOnline(List<UserOnline> usersOnline){
            return usersOnline.stream().min((o1, o2) -> (int) (ChronoUnit.DAYS.between(o2.getStartSession(), o2.getEndSession())
                    - ChronoUnit.DAYS.between(o1.getStartSession(), o1.getEndSession()))).orElse(null);
        }

        public static long longestUserWasOnlineDays(UserOnline userOnline){
            return ChronoUnit.DAYS.between(userOnline.getStartSession(), userOnline.getEndSession());
        }

        public static Map<Integer, LocalDate> usersOnlineQuantityAndDate(List<UserOnline> usersOnline) {
            Map<Integer, LocalDate> onlineUsersNow = new HashMap<>();
            for (int i = 0; i < usersOnline.size(); i++) {
                int countOnCurrentLoop = 1;
                LocalDate currentMaximumDate = null;
                LocalDate currentUserEndSession = usersOnline.get(i).getEndSession();
                for (int j = 0; j < usersOnline.size(); j++) {
                    if (i == j) {continue;}
                    if (usersOnline.get(j).getStartSession().isBefore(currentUserEndSession)){
                        countOnCurrentLoop++;
                        currentMaximumDate = usersOnline.get(j).getStartSession();
                    }
                }
                onlineUsersNow.put(countOnCurrentLoop, currentMaximumDate);
            }
            return onlineUsersNow;
        }

        public static int maxUsersOnlineQuantity (Map<Integer, LocalDate> onlineUsersNow) {
            return Objects.requireNonNull(onlineUsersNow.entrySet().stream().max((o1, o2) -> o1.getKey() - o2.getKey()).orElse(null)).getKey();
        }

        public static LocalDate maxUsersOnlineDate(Map<Integer, LocalDate> onlineUsersNow) {
            return Objects.requireNonNull(onlineUsersNow.entrySet().stream().max((o1, o2) -> o1.getKey() - o2.getKey()).orElse(null)).getValue();
        }

        public static long maxOnlineDuration(LocalDate maxUsersOnlineDate, List<UserOnline> usersOnline){
            LocalDate firstUserExitFromBiggestOnline = Objects.requireNonNull(usersOnline.stream()
                            .filter(userOnline -> userOnline.getEndSession().isAfter(maxUsersOnlineDate))
                            .min((o1, o2) -> (int) (ChronoUnit.DAYS.between(maxUsersOnlineDate, o1.getEndSession()) -
                                    ChronoUnit.DAYS.between(maxUsersOnlineDate, o2.getEndSession())))
                            .orElse(null))
                            .getEndSession();
            return ChronoUnit.DAYS.between(maxUsersOnlineDate, firstUserExitFromBiggestOnline);
        }

        public static UserOnline generateUser(){
            Random random = new Random();

            int randomYearStart = random.nextInt(2020,2022);
            int randomMonthStart = random.nextInt(1,12);
            int randomDayStart = random.nextInt(1, Month.of(randomMonthStart).maxLength());
            int randomYearEnd = random.nextInt(2020,2022);
            int randomMonthEnd = random.nextInt(1,12);
            int randomDayEnd = random.nextInt(1, Month.of(randomMonthEnd).maxLength());

            LocalDate startUserOnline = LocalDate.of(randomYearStart, randomMonthStart, randomDayStart);
            LocalDate endUserOnline = LocalDate.of(randomYearEnd, randomMonthEnd, randomDayEnd);

            if(startUserOnline.isAfter(endUserOnline)) {
                LocalDate temp = startUserOnline;
                startUserOnline = endUserOnline;
                endUserOnline = temp;
            }

            return new UserOnline(startUserOnline, endUserOnline);
        }
    }


