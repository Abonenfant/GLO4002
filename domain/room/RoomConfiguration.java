package ca.ulaval.glo4002.inn.domain.room;

import java.util.Objects;

public final class RoomConfiguration {
    private final Atmosphere atmosphere;
    private final Gravity gravity;
    private final Temperature temperature;

    public RoomConfiguration(Atmosphere atmosphere, Gravity gravity, Temperature temperature) {
        this.atmosphere = atmosphere;
        this.gravity = gravity;
        this.temperature = temperature;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Gravity getGravity() {
        return gravity;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoomConfiguration configuration)) {
            return false;
        }
        return atmosphere == configuration.atmosphere
                && gravity == configuration.gravity
                && temperature == configuration.temperature;
    }

    @Override
    public int hashCode() {
        return Objects.hash(atmosphere, gravity, temperature);
    }

    @Override
    public String toString() {
        return "RoomConfiguration[atmosphere=" + atmosphere
                + ", gravity=" + gravity + ", temperature=" + temperature + "]";
    }
}
