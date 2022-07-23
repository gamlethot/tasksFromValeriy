package Task3;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
class RunnerTest {

    List<UserOnline> userOnlineList;

    @BeforeEach
    void initUsersOnlineList(){
        //Max users online 4
        //Longest user online user №7, online for 9 months
        //Total users 10
        UserOnline userOnline1 = new UserOnline(
                LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1));
        UserOnline userOnline2 = new UserOnline(
                LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1));
        UserOnline userOnline3 = new UserOnline(
                LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1));
        UserOnline userOnline4 = new UserOnline(
                LocalDate.of(2020, 3, 1), LocalDate.of(2020, 4, 1));
        UserOnline userOnline5 = new UserOnline(
                LocalDate.of(2020, 3, 1), LocalDate.of(2020, 4, 1));
        UserOnline userOnline6 = new UserOnline(
                LocalDate.of(2020, 3, 1), LocalDate.of(2020, 4, 1));
        UserOnline userOnline7 = new UserOnline(
                LocalDate.of(2020, 3, 1), LocalDate.of(2020, 12, 1));
        UserOnline userOnline8 = new UserOnline(
                LocalDate.of(2021, 1, 1), LocalDate.of(2021, 6, 1));
        UserOnline userOnline9 = new UserOnline(
                LocalDate.of(2021, 1, 1), LocalDate.of(2021, 6, 1));
        UserOnline userOnline10 = new UserOnline(
                LocalDate.of(2021, 1, 1), LocalDate.of(2021, 6, 1));
        userOnlineList = List.of(userOnline1, userOnline2, userOnline3, userOnline4, userOnline5, userOnline6, userOnline7,
                                        userOnline8, userOnline9, userOnline10);
    }

    @Test
    void longestUserOnlineIsUserNum7() {
        UserOnline userOnline7 = Runner.longestUserOnline(userOnlineList);
        Assertions.assertNotNull(userOnline7);
        Assertions.assertSame(userOnline7, userOnlineList.get(6));
    }

    @Test
    void longestUserWasOnlineFor9monthsInDays() {
        long longestUserDaysOnline = ChronoUnit.DAYS.between(LocalDate.of(2020, 3, 1),
                LocalDate.of(2020, 12, 1));
        Assertions.assertEquals(longestUserDaysOnline,
                Runner.longestUserWasOnlineDays(Runner.longestUserOnline(userOnlineList)));
    }

    @Test
    void returnsSetOfQuantityUsersOnlineShouldReturn3and4() {
        Set<Integer> usersOnlineQuantity = Set.of(3, 4);
        Assertions.assertEquals(usersOnlineQuantity, Runner.usersOnlineQuantity(userOnlineList));
    }

    @Test
    void maxUsersOnlineQuantity() {
        Assertions.assertEquals(4, Runner.maxUsersOnlineQuantity(Runner.usersOnlineQuantity(userOnlineList)));
    }

    @Test
    void totalUsersEver() {
        Assertions.assertEquals(10, Runner.totalUsersEver(userOnlineList));
    }
}