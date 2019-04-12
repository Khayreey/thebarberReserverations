package khayreey.khayreey.thebarber;

public class bookList
{
    String name,shaveHair,shaveBear,hairPigment,faceMusk,time,date,price;

    public bookList()
    {
    }

    public bookList(String name, String shaveHair, String shaveBear, String hairPigment, String faceMusk, String time, String date, String price) {
        this.name = name;
        this.shaveHair = shaveHair;
        this.shaveBear = shaveBear;
        this.hairPigment = hairPigment;
        this.faceMusk = faceMusk;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShaveHair() {
        return shaveHair;
    }

    public void setShaveHair(String shaveHair) {
        this.shaveHair = shaveHair;
    }

    public String getShaveBear() {
        return shaveBear;
    }

    public void setShaveBear(String shaveBear) {
        this.shaveBear = shaveBear;
    }

    public String getHairPigment() {
        return hairPigment;
    }

    public void setHairPigment(String hairPigment) {
        this.hairPigment = hairPigment;
    }

    public String getFaceMusk() {
        return faceMusk;
    }

    public void setFaceMusk(String faceMusk) {
        this.faceMusk = faceMusk;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
