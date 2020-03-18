public class Hospital implements Comparable<Hospital> {
    Integer capacity;
    String name;
    private Integer currentNo=0;


    public int compareTo(Hospital hospital){

        return capacity-hospital.getCapacity();

    }

    public Hospital(Integer capacity, String name) {
        this.capacity = capacity;
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "capacity=" + capacity +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(Integer currentNo) {
        this.currentNo = currentNo;
    }
}
