package Task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
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

            List<Integer> usersOnlineQuantity = usersOnlineQuantityAndDate(usersOnline);
            int totalUsersEver = totalUsersEver(usersOnline);
            int maxUsersOnlineQuantity = maxUsersOnlineQuantity(usersOnlineQuantity);
            UserOnline longestUserOnline = longestUserOnline(usersOnline);
            long longestUserWasOnlineDays = longestUserWasOnlineDays(longestUserOnline);

            consoleResultsOtput(usersOnline, totalUsersEver, maxUsersOnlineQuantity, longestUserOnline, longestUserWasOnlineDays);
        }

        private static void consoleResultsOtput(List<UserOnline> usersOnline, int totalUsersEver, int maxUsersOnlineQuantity, UserOnline longestUserOnline, long longestUserWasOnlineDays) {
            System.out.println("ALL USERS: \n" + usersOnline);
            System.out.println("Total users ever online: " + totalUsersEver);
            System.out.println("MAX users online: " + maxUsersOnlineQuantity +" users.");
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

        public static List<Integer> usersOnlineQuantityAndDate(List<UserOnline> usersOnline){
            List<Integer> resultOnlineList = new ArrayList<>();
            List<List<LocalDate>> collect = usersOnline.stream()
                    .map(userOnline -> userOnline.getStartSession().datesUntil(userOnline.getEndSession()).collect(Collectors.toList()))
                    .toList();
            for (int i = 0; i < collect.size(); i++) {
                List<LocalDate> baseList = collect.get(i);
                int count = 0;
                for (int j = 0; j < collect.size(); j++) {
                    if (i==j){continue;}
                    List<LocalDate> comparingList = collect.get(j);
                    if (comparingList.stream().anyMatch(baseList::contains)){
                        count++;
                    }
                }
                resultOnlineList.add(count);
            }
        return resultOnlineList;
        }

        public static int maxUsersOnlineQuantity (List<Integer> usersOnlineQuantity) {
            return Collections.max(usersOnlineQuantity);
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

        public static int totalUsersEver(List<UserOnline> usersOnline) {
            return usersOnline.size();
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


