package concurrency.threadPattern.singleThreadExcution.flight;

public class Passager extends Thread{
    private FlightSecurity flightSecurity;
    private String boardingPass;
    private String idCard;

    public Passager(FlightSecurity flightSecurity, String boardingPass, String idCard) {
        this.flightSecurity = flightSecurity;
        this.idCard = idCard;
        this.boardingPass = boardingPass;
    }

    @Override
    public void run() {
        while (true){
            flightSecurity.pass(boardingPass,idCard);
        }
    }

    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passager(flightSecurity,"A123456","AS123456").start();
        new Passager(flightSecurity,"B123456","BS123456").start();
        new Passager(flightSecurity,"C123456","CS123456").start();
    }
}
