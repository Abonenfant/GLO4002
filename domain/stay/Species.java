package ca.ulaval.glo4002.inn.domain.stay;

import ca.ulaval.glo4002.inn.domain.room.Atmosphere;
import ca.ulaval.glo4002.inn.domain.room.Gravity;
import ca.ulaval.glo4002.inn.domain.room.RoomConfiguration;
import ca.ulaval.glo4002.inn.domain.room.Temperature;

public enum Species {
    QUIDAM(Atmosphere.STANDARD, Gravity.NORMAL, Temperature.TEMPERATE),
    PYROPHORE(Atmosphere.STANDARD, Gravity.NORMAL, Temperature.HOT),
    PLANTAGENET(Atmosphere.HUMID, Gravity.NORMAL, Temperature.HOT),
    MERFOLK(Atmosphere.HUMID, Gravity.LIGHT, Temperature.COLD),
    MENHIR(Atmosphere.STANDARD, Gravity.HEAVY, Temperature.COLD);

    private final Atmosphere atmosphereRequirement;
    private final Gravity gravityRequirement;
    private final Temperature temperatureRequirement;

    Species(Atmosphere atmosphereRequirement, Gravity gravityRequirement, Temperature temperatureRequirement) {
        this.atmosphereRequirement = atmosphereRequirement;
        this.gravityRequirement = gravityRequirement;
        this.temperatureRequirement = temperatureRequirement;
    }

    public Atmosphere atmosphereRequirement() {
        return atmosphereRequirement;
    }

    public Atmosphere requiredAtmosphere() {
        return atmosphereRequirement;
    }

    public Gravity gravityRequirement() {
        return gravityRequirement;
    }

    public Gravity requiredGravity() {
        return gravityRequirement;
    }

    public Temperature temperatureRequirement() {
        return temperatureRequirement;
    }

    public Temperature requiredTemperature() {
        return temperatureRequirement;
    }

    public RoomConfiguration requiredConfiguration() {
        return new RoomConfiguration(atmosphereRequirement, gravityRequirement, temperatureRequirement);
    }

    public boolean matches(RoomConfiguration configuration) {
        return requiredConfiguration().equals(configuration);
    }
}
