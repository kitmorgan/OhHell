package kitmorgan;

public class RoundInfo {
    public int bid = 0;
    public int tricks = 0;

    public RoundInfo(){
    }
    public RoundInfo(int bid, int tricks){
        this.bid = bid;
        this.tricks = tricks;
    }
    public void setBidAndTrick(int bid, int tricks){
        this.bid = bid;
        this.tricks = tricks;
    }

    public void setBid(int bid){
        this.bid = bid;
    }

    public void setTricks(int tricks){
        this.tricks = tricks;
    }

    public int getBid() {
        return bid;
    }

    public int getTricks() {
        return tricks;
    }

    public void takeTrick(){
        this.tricks++;
    }

    public boolean madeBid(){
        return  (getBid() == getTricks());
    }
}
