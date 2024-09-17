package main.java.org.solvd.structure.enums;

public enum CarModel {
    KIA_RIO(EngineType.PETROL, 5.3, "LitresPer100km", 3),
    BMW_M2(EngineType.PETROL,9.7, "LitresPer100km", 3),
    TESLA_MODEL_S(EngineType.ELECTRIC, 18.9, "kWhPer100km", 3),
    TOYOTA_COROLLA(EngineType.DIESEL, 5, "LitresPer100km", 3),
    VOLVO_EM90(EngineType.ELECTRIC, 14.9, "kWhPer100km", 5);
    private final EngineType engineType;
    private final double fuelConsumption;
    private final String fuelConsumptionUnits;
    private final int seatsForPassengers;
    CarModel(EngineType engineType, double fuelConsumption, String fuelConsumptionUnits, int seatsForPassengers){
        this.engineType = engineType;
        this.fuelConsumption = fuelConsumption;
        this.fuelConsumptionUnits = fuelConsumptionUnits;
        this.seatsForPassengers = seatsForPassengers;
    }

    public enum EngineType{
        PETROL(30),
        DIESEL(35),
        HYBRID(40),
        ELECTRIC(85),
        GAS(50);
        private final int fuelEfficiency;
        EngineType(int fuelEfficiency){
            this.fuelEfficiency = fuelEfficiency;
        }
        public int getFuelEfficiency(){
            return fuelEfficiency;
        }
    }
    public double getFuelConsumption(){
        return fuelConsumption;
    }
    public int getSeatsForPassengers(){
        return seatsForPassengers;
    }
    public String getFuelConsumptionUnits(){
        return fuelConsumptionUnits;
    }

    public EngineType getEngineType() {
        return engineType;
    }
}
