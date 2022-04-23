package com.example.logyourlakes;

public class Lake {

    private String name;
    private String municipality;
    private String deepestDepth;
    private String averageDepth;
    private String shorelineLength;
    private String waterSystem;
    private String centerCoordinates;
    private String deepestCoordinates;

    public Lake(String name, String municipality, String deepestDepth, String averageDepth,
                String shorelineLength, String waterSystem, String centerCoordinates,
                String deepestCoordinates) {
        this.name = name;
        this.municipality = municipality;
        this.deepestDepth = deepestDepth;
        this.averageDepth = averageDepth;
        this.shorelineLength = shorelineLength;
        this.waterSystem = waterSystem;
        this.centerCoordinates = centerCoordinates;
        this.deepestCoordinates = deepestCoordinates;
    }

    public String getName() {
        return name;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getDeepestDepth() {
        return deepestDepth;
    }

    public String getAverageDepth() { return averageDepth; }

    public String getShorelineLength() {
        return shorelineLength;
    }

    public String getWaterSystem() { return waterSystem; }

    public String getCenterCoordinates() {return centerCoordinates; }

    public String getDeepestCoordinates() { return deepestCoordinates; }

}
