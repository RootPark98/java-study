package lang.datetime;

import java.time.*;
import java.time.temporal.*;
import java.time.format.*;

public class DateTimeStudy{
	public static void main(String[] args){
		System.out.println("1. Library overview");
		libraryOverview();

		System.out.println("\n2. LocalDateTime");
		localDateTimeExample();

		System.out.println("\n3. ZonedDateTime");
		zonedDateTimeExample();

		System.out.println("\n4. Instant");
		instantExample();

		System.out.println("\n5. Duration and Period");
		durationAndPeriodExample();

		System.out.println("\n6. Core interfaces");
		coreInterfaceExample();

		System.out.println("\n7. Query and manipulation");
		queryAndManipulationExample();

		System.out.println("\n8. Parsing and formatting");
		parsingAndFormattingExample();
	}

	static void libraryOverview(){
		LocalDate date = LocalDate.of(2026, 8, 6);
		LocalTime time = LocalTime.of(19, 30, 15);
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		System.out.println("LocalDate = " + date);
		System.out.println("LocalTime = " + time);
		System.out.println("LocalDateTime = " + dateTime);
	}

	static void localDateTimeExample(){
		LocalDateTime original = LocalDateTime.of(2026, 8, 6, 19, 30);

		LocalDateTime changed = original
			.plusDays(3)
			.minusHours(2)
			.withMinute(0);

		System.out.println("Original = " + original);
		System.out.println("Changed = " + changed);
		System.out.println("Same object = " + (original == changed));
	}

	static void zonedDateTimeExample(){
		LocalDateTime localDateTime = LocalDateTime.of(2026, 8, 6, 19, 30);

		ZoneId seoulZone = ZoneId.of("Asia/Seoul");
		ZoneId newYorkZone = ZoneId.of("America/New_York");

		ZonedDateTime seoulTime = ZonedDateTime.of(localDateTime, seoulZone);
		ZonedDateTime newYorkTime = seoulTime.withZoneSameInstant(newYorkZone);

		System.out.println("Seoul = " + seoulTime);
		System.out.println("New York = " + newYorkTime);
		System.out.println("Seoul offset = " + seoulTime.getOffset());
	}

	static void instantExample(){
		ZonedDateTime seoulTime = ZonedDateTime.of(
			2026,
			8,
			6,
			19,
			30,
			0,
			0,
			ZoneId.of("Asia/Seoul")
		);

		Instant instant = seoulTime.toInstant();
		Instant oneHourLater = instant.plusSeconds(3600);

		System.out.println("Instant = " + instant);
		System.out.println("Onew hour later = " + oneHourLater);
		System.out.println("Epoch second = " + instant.getEpochSecond());
	}

	static void durationAndPeriodExample(){
		LocalTime startTime = LocalTime.of(9, 10);
		LocalTime endTime = LocalTime.of(11, 45);

		Duration duration = Duration.between(startTime, endTime);

		LocalDate startDate = LocalDate.of(2026, 8, 6);
		LocalDate endDate = LocalDate.of(2027, 10, 20);

		Period period = Period.between(startDate, endDate);

		System.out.println("Duration minutes = " + duration.toMinutes());
		System.out.println("Period years = " + period.getYears());
		System.out.println("Period months = " + period.getMonths());
		System.out.println("Period days = " + period.getDays());
	}

	static void coreInterfaceExample(){
		LocalDate date = LocalDate.of(2026, 8, 6);

		LocalDateTime dateTime = LocalDateTime.of(2026, 8, 6, 19, 30);

		TemporalAccessor accessor = date;

		Temporal temporal = dateTime;

		TemporalAmount amount = Period.ofDays(3);

		TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);

		System.out.println("Year through TemporalAccessor = " + accessor.get(ChronoField.YEAR));
		System.out.println("Temporal plus two days = " + temporal.plus(2, ChronoUnit.DAYS));
		System.out.println("Temporal plus amount = " + temporal.plus(amount));
		System.out.println("Next Friday = " + date.with(nextFriday));
	}

	static void queryAndManipulationExample(){
		LocalDateTime dateTime = LocalDateTime.of(2026, 8, 6, 19, 30, 15);

		System.out.println("Year = " + dateTime.getYear());
		System.out.println("Month = " + dateTime.getMonth());
		System.out.println("Day of week = " + dateTime.getDayOfWeek());
		System.out.println("Hour = " + dateTime.getHour());

		System.out.println("Month through ChronoField = " + dateTime.get(ChronoField.MONTH_OF_YEAR));

		LocalDateTime changed = dateTime
			.withYear(2030)
			.plusMonths(2)
			.minusDays(5);

		System.out.println("Changed = " + changed);

		LocalDate date = LocalDate.of(2026, 8, 6);

		LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

		LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

		long daysBetween = ChronoUnit.DAYS.between(date, nextMonday);

		System.out.println("Last day of month = " + lastDayOfMonth);
		System.out.println("Next Monday = " + nextMonday);
		System.out.println("Days until next Monday = " + daysBetween);
	}

	static void parsingAndFormattingExample(){
		DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime dateTime = LocalDateTime.of(2026, 8, 6, 19, 30, 15);

		String formatted = dateTime.format(formatter);

		LocalDateTime parsed = LocalDateTime.parse(
			"2027-01-20 08:45:30", formatter);

		System.out.println("Formatted = " + formatted);
		System.out.println("Parsed = " + parsed);

		ZonedDateTime zonedDateTime = ZonedDateTime.parse(
			"2026-08-06T19:30:00+09:00[Asiz/Seoul]");

		System.out.println("Parsed zoned date time = " + zonedDateTime);
	}
}
