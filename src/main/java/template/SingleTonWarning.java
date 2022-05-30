package template;

public class SingleTonWarning extends Thread {

    private int seq;
    private int time;
    private String name;

    public SingleTonWarning(){}
    public SingleTonWarning(int seq, int time, String name){
        this.seq = seq;
        this.time = time;
        this.name = name;
    }

    SomethingDTO dto = SomethingDTO.getInstance();

    public void setDto(int id, String name) {
        dto.setId(id);
        dto.setName(name);
    }

    public void printDTO(){
        System.out.println("dto.id = " + dto.getId());
        System.out.println("dto.name = " + dto.getName());
    }

    public void run(){
        System.out.println(seq + " thread start.");
        try {
            setDto(seq, name);
            Thread.sleep(time);
            printDTO();
        } catch (Exception e) {
        }
        System.out.println(seq + " thread end.");
    }

    public static void main(String[] args) {

        Thread t1 = new SingleTonWarning(1, 1000, "첫번째");
        Thread t2 = new SingleTonWarning(2, 500, "두번째");

        t1.start();
        t2.start();
    }
}
