package net.lelyak.config;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@UtilityClass
public class ClockHolder {
    private final AtomicReference<Clock> CLOCK_REFERENCE = new AtomicReference<>(Clock.systemDefaultZone());

    public Clock getClock() {
        return CLOCK_REFERENCE.get();
    }

    public Clock setClock(Clock clock) {
        Objects.requireNonNull(clock, "clock cannot be null");
        Clock oldClock = CLOCK_REFERENCE.getAndSet(clock);
        log.info("Set new clock {}. Old clock is {}", clock, oldClock);
        return oldClock;
    }
}
