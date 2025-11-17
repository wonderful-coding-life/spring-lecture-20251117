public class SpringCafe {
    public static void main(String[] args) {
        Barista barista = new Barista();
        DripCoffeeMachine dripCoffeeMachine = new DripCoffeeMachine();
        barista.setCoffeeMachine(dripCoffeeMachine);
        barista.makeCoffee();
    }
}
