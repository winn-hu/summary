package concurrency.threadPattern.singleThreadExcution.flight;

public class FlightSecurity {
    private int count = 0;
    private String boardingpass = "null";
    private String idCard = "null";

    public synchronized void pass(String boardingpass, String idCard){
        this.boardingpass = boardingpass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingpass.charAt(0) != idCard.charAt(0)){
            throw new RuntimeException("=========Exception========="+toString());
        }
    }

    @Override
    public String toString() {
        return String.format("The %d passager, boardingPass[%s],idCard[%s].", count, boardingpass, idCard);
    }
}
