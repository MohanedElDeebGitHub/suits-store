public class ClassicSuit implements Suit{
    private String size;

    public ClassicSuit(String size) {
        this.size = size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public Suit clonce() {

        try{
            return (Suit) super.clone();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void serve() {
        System.out.println("classic Suit is Serving ");
    }
}
