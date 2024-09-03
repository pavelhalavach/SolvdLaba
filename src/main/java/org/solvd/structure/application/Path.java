package org.solvd.structure.application;

import java.util.Objects;

public class  Path {
    private float startPoint;
    private float endPoint;
    private float distanceInKm;

    public Path(float startPoint, float endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        setDistance();
    }

    @Override
    public String toString() {
        return "{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", distanceInKm=" + distanceInKm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Float.compare(startPoint, path.startPoint) == 0 && Float.compare(endPoint, path.endPoint) == 0 && Float.compare(distanceInKm, path.distanceInKm) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, distanceInKm);
    }

    public float getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(float startPoint) {
        this.startPoint = startPoint;
    }

    public float getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(float endPoint) {
        this.endPoint = endPoint;
    }

    public float getDistance() {
        return distanceInKm;
    }

    public void setDistance() {
        this.distanceInKm = endPoint-startPoint;
    }
}
