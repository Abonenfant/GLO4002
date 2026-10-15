package ca.ulaval.glo4002.inn.domain.stay;

public enum StayPackage {
    ROYAL(300),
    COMFORT(150),
    MODEST(60);

    private final Money nightlyRate;

    StayPackage(int nightlyRateInPieces) {
        this.nightlyRate = Money.ofPieces(nightlyRateInPieces);
    }

    public Money nightlyRate() {
        return nightlyRate;
    }
}
