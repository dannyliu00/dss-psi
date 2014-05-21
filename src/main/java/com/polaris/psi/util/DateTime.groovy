package com.polaris.psi.util

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

import static java.util.Calendar.DATE
import static java.util.Calendar.HOUR_OF_DAY
import static java.util.Calendar.MILLISECOND
import static java.util.Calendar.MINUTE
import static java.util.Calendar.MONTH
import static java.util.Calendar.SECOND
import static java.util.Calendar.YEAR

final class DateTime implements Comparable<DateTime> {

    public static final String CANONICAL_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    public static final TimeZone UTC = TimeZone.getTimeZone('UTC')

    private static final Pattern DATE_TIME_PATTERN = Pattern.compile(/^(\d+)-?(\d{2})-?(\d{2})[-T ](\d{2}):?(\d{2}):?(\d{2})(?:\.?(\d{0,9}))?[ ]?(Z|[-+]\d{4})$/)
    private static final Pattern GMT_OFFSET_PATTERN = Pattern.compile(/^([-+])(\d{2})([0-5][0-9])$/)

    @SuppressWarnings("GrFinalVariableAccess") // Note: this inspection is often wrong about uninitialized final variables - especially when a constructor contains a validation in an if-block which throws IllegalArgumentException if something is wrong before initializing the field.
    final Long timeInMillis

    DateTime() {
        this(System.currentTimeMillis())
    }

    DateTime(final Long timeInMillis) {
        if (timeInMillis == null) {
            throw new IllegalArgumentException("invalid timeInMillis: [${timeInMillis}]")
        }

        this.timeInMillis = timeInMillis
    }

    DateTime(final TimeZone timeZone, final Integer year, final Integer month, final Integer day, final Integer hour, final Integer minute, final Integer second, Integer millisecond) {
        if (timeZone == null) {
            throw new IllegalArgumentException("invalid timeZone: [${timeZone}]")
        }

        if (year == null) {
            throw new IllegalArgumentException("invalid year: [${year}]")
        }

        if (month == null) {
            throw new IllegalArgumentException("invalid month: [${month}]")
        }

        if (day == null) {
            throw new IllegalArgumentException("invalid day: [${day}]")
        }

        if (hour == null) {
            throw new IllegalArgumentException("invalid hour: [${hour}]")
        }

        if (minute == null) {
            throw new IllegalArgumentException("invalid minute: [${minute}]")
        }

        if (second == null) {
            throw new IllegalArgumentException("invalid second: [${second}]")
        }

        if (millisecond == null) {
            throw new IllegalArgumentException("invalid millisecond: [${millisecond}]")
        }

        Calendar calendar = getNonLenientCalendarAtMidnightInTimeZone(timeZone)
        try {
            calendar.set(YEAR, year)
            calendar.set(MONTH, month - 1)
            calendar.set(DATE, day)
            calendar.set(HOUR_OF_DAY, hour)
            calendar.set(MINUTE, minute)
            calendar.set(SECOND, second)
            calendar.set(MILLISECOND, millisecond)
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("invalid date-time: %04d-%02d-%02d %02d:%02d:%02d.%03d, no such date", year, month, day, hour, minute, second, millisecond), e)
        }

        this.timeInMillis = calendar.timeInMillis
    }

    DateTime(final String dateTimeString) {
        this(parseTimeInMillis(dateTimeString))
    }

    @Override
    final boolean equals(Object o) {
        if (this.is(o)) {
            return true
        } else if (o == null) {
            return false
        } else if (this.class != o.class) {
            return false
        }

        DateTime other = (DateTime) o
        return (this.timeInMillis == other.timeInMillis)
    }

    @Override
    final int hashCode() {
        return this.timeInMillis.hashCode()
    }

    @Override
    final int compareTo(DateTime o) {
        if (o == null) {
            throw new NullPointerException('other date time was null')
        }
        return this.timeInMillis.compareTo(o.timeInMillis)
    }

    @Override
    final String toString() {
        return toString(CANONICAL_FORMAT_STRING, UTC)
    }

    /**
     * Formats this date time with the specified simple date format string, in the specified time zone.  This is done
     * by constructing a {@link java.text.SimpleDateFormat} object, setting its time zone to the specified time zone, creating a
     * {@link Date} object with this DateTime object's time in millis, then invoking the simple date format object's
     * {@link java.text.SimpleDateFormat#format(Date) format(Date)} method on the Date object.
     *
     * @param simpleDateFormatString the format string to use to format this date-time object
     * @param timeZone the time zone in which to format the date-time
     * @return the date-time formatted with the specified format string in the specified time zone
     * @throws IllegalArgumentException if either the format string or time zone is <code>null</code>
     */
    final String toString(final String simpleDateFormatString, final TimeZone timeZone) {
        if (simpleDateFormatString == null) {
            throw new IllegalArgumentException("invalid simpleDateFormatString: [${simpleDateFormatString}]")
        }

        if (timeZone == null) {
            throw new IllegalArgumentException("invalid timeZone: [${timeZone}]")
        }

        final SimpleDateFormat sdf = new SimpleDateFormat(simpleDateFormatString)
        sdf.timeZone = timeZone
        return sdf.format(new Date(this.timeInMillis))
    }

    private static GregorianCalendar getNonLenientCalendarAtMidnightInTimeZone(final TimeZone timeZone) {
        final Calendar calendar = new GregorianCalendar(timeZone)
        calendar.setLenient(false)
        calendar.set(HOUR_OF_DAY, 0)
        calendar.set(MINUTE, 0)
        calendar.set(SECOND, 0)
        calendar.set(MILLISECOND, 0)
        return calendar
    }

    private static Long parseTimeInMillis(final String dateTimeString) {
        if (dateTimeString == null) {
            throw new IllegalArgumentException("invalid dateTimeString: [${dateTimeString}]")
        }

        final Matcher m = DATE_TIME_PATTERN.matcher(dateTimeString)
        if (!m.matches()) {
            throw new IllegalArgumentException("invalid dateTimeString: [${dateTimeString}], try ISO 8601 format")
        }

        try {
            final Integer year = m.group(1) as Integer
            final Integer month = m.group(2) as Integer
            final Integer day = m.group(3) as Integer
            final Integer hour = m.group(4) as Integer
            final Integer minute = m.group(5) as Integer
            final Integer second = m.group(6) as Integer
            final Double subsecond = (m.groupCount() > 7 ? String.format('0.%s', m.group(7) ?: 0) : 0) as Double
            final Integer millisecond = Math.floor(1000.0d * subsecond) as Integer
            final String offsetString = (m.groupCount() > 7 ? m.group(8) : m.group(7))
            final Integer offsetInMinutes = parseOffsetInMinutes(offsetString)
            final Calendar calendar = getNonLenientCalendarAtMidnightInTimeZone(TimeZone.getTimeZone('UTC'))
            calendar.set(year, month - 1, day, hour, minute, second)
            calendar.set(MILLISECOND, millisecond)
            calendar.add(MINUTE, -offsetInMinutes)
            return calendar.timeInMillis
        } catch (Throwable t) {
            throw new IllegalArgumentException("invalid dateTimeString: [${dateTimeString}], no such date", t)
        }
    }

    private static Integer parseOffsetInMinutes(final String offsetString) {
        if ((offsetString == null) || 'Z'.equals(offsetString)) {
            return 0L
        }

        // We expect the form [-/+]HHMM for the offset.  We'll require the sign.
        final Matcher m = GMT_OFFSET_PATTERN.matcher(offsetString)
        if (!m.matches()) {
            throw new IllegalArgumentException("invalid offsetString: [${offsetString}], try ISO 8601 format")
        }

        final Integer sign = ('-'.equals(m.group(1)) ? -1 : 1)
        final Integer hours = m.group(2) as Integer
        final Integer minutes = m.group(3) as Integer
        return (sign * ((60 * hours) + minutes))
    }

}

