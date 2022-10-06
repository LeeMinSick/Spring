package hello.core.singleton;

public class StatelessService {

    public int order(String name, int price) { //파라미터
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
