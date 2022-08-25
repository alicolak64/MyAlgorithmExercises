import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ali Çolak
 * 25.01.2021
 */

public class TelevisionSimulation {

    static Scanner scanner = new Scanner(System.in);
    static Television tv;
    public static void main(String[] args) {

        showMenu();
        boolean exit = false;

        while (!exit){
            System.out.println("Enter : (Press 8 to see the menu)");
            int choice = scanner.nextInt();
            switch (choice){
                case 1->
                        setUpTv();
                case 2->
                        openTv();
                case 3->
                        upVolume();
                case 4->
                        downVolume();
                case 5->
                        changeChannel();
                case 6->
                        getChannelInformation();
                case 7->
                        closeTv();
                case 8->
                        showMenu();
                case 0-> {
                    System.out.println("Exiting the system");
                    exit = true;
                }
                default -> System.out.println("Enter a value between 0 and 8");
            }
        }

    }

    public static void getChannelInformation() {

        if ( tv != null ){
            tv.getChannelInformation();
        } else
            System.out.println("First set up the TV and create the channels (You can do it by pressing the 1 button in the menu.");

    }

    public static void changeChannel() {

        if ( tv != null ){
            System.out.println("Which channel do you want to go to?");
            tv.changeChannel(scanner.nextInt());
        } else
            System.out.println("First set up the TV and create the channels (You can do it by pressing the 1 button in the menu.");

    }

    public static void downVolume() {

        if ( tv != null )
            tv.downVolume();
        else
            System.out.println("First set up the TV and create the channels (You can do it by pressing the 1 button in the menu.");
    }

    public static void upVolume() {

        if ( tv != null )
            tv.upVolume();
        else
            System.out.println("First set up the TV and create the channels (You can do it by pressing the 1 button in the menu.");

    }

    public static void closeTv() {

        if (tv != null)
            tv.closeTv();
        else
            System.out.println("First set up the TV and create the channels (You can do it by pressing the 1 button in the menu.");

    }

    public static void openTv() {

        if (tv != null)
            tv.openTv();
        else
            System.out.println("First set up the TV and create the channels (You can do it by pressing the 1 button in the menu.");

    }

    private static void setUpTv() {

        if ( tv == null ){
            scanner.nextLine();
            System.out.println("Enter the brand of the TV ");
            String brand = scanner.nextLine();
            System.out.println("Enter the dimension of the TV");
            int dimension = scanner.nextInt();
            tv = new Television(brand,dimension);
            System.out.println(tv);
        } else
            System.out.println(tv);
    }

    private static void showMenu(){
        System.out.println("*********MENU**********");
        System.out.println(
                "1-Set Up\n"+
                "2-Open Tv \n"+
                "3-Up Volume\n"+
                "4-Down Volume \n"+
                "5-Change Channel \n"+
                "6-Information Channel \n"+
                "7-Close Tv \n"+
                "8-Show Menu \n"+
                "0-Exit");
    }
}
class Television{

    private String brand;
    private int dimension;
    private ArrayList<Channel> channels;
    private boolean isOpen;
    private int volume;
    private int currentChannel;
    private int numberOfChannels;

    public Television(String brand, int dimension) {
        this.brand = brand;
        this.dimension = dimension;
        channels = new ArrayList<>();
        createChannels();
        this.isOpen = false;
        currentChannel = 1;
        numberOfChannels = channels.size();
        volume = 10;
    }

    private void createChannels() {

        NewsChannel cnn = new NewsChannel("CNN",1,"General Haber");
        NewsChannel trtSport = new NewsChannel("TRT Sport",2,"Sport Haber");
        MusicChannel powerTv = new MusicChannel("POWER TV",3,"Foreign Pop");
        MusicChannel powerTurkTv = new MusicChannel("POWER Turk TV",4,"Turkish Pop");
        channels.add(cnn);
        channels.add(trtSport);
        channels.add(powerTv);
        channels.add(powerTurkTv);
    }

    public void openTv(){

        if (!isOpen){
            isOpen = true;
            System.out.println("TV turned on");
        } else
            System.out.println("TV is already on");

    }

    public void closeTv(){

        if (isOpen){
            isOpen = false;
            System.out.println("TV turned off");
        } else
            System.out.println("TV turned off");

    }

    public void upVolume(){

        if ( isOpen ){
            if ( volume < 20 ){
                volume++;
                System.out.println("Volume : " + volume);
            } else
                System.out.println("Volume at maximum ");
        } else
            System.out.println("The TV is off. You can first turn on the TV with the 2 button and then increase the volume.");

    }

    public void downVolume(){

        if ( isOpen ){
            if ( volume > 0 ){
                volume--;
                System.out.println("Volume : " + volume);
            } else
                System.out.println("Volume at minimum ");
        } else
            System.out.println("The TV is off. You can first turn on the TV with the 2 button and then decrease the volume..");

    }

    public void changeChannel(int newChannel){

        if ( isOpen ){
            if ( newChannel != currentChannel ){
                if ( newChannel <= numberOfChannels && newChannel >= 1 ){
                    currentChannel = newChannel;
                    System.out.println( channels.get( newChannel - 1 ).getName() + " channel opened") ;
                } else
                    System.out.println( "The channel you entered could not be found." );
            } else
                System.out.println("You are already on channel " + currentChannel + " Please enter another channel no");
        } else
            System.out.println("The TV is off. You can first turn on the TV with the 2 button and then change the channel.");

    }

    public void getChannelInformation(){

        if (isOpen)
            System.out.println( " Informations : Channel Name : " + channels.get( currentChannel - 1 ).getName() + " Channel No : " + channels.get( currentChannel - 1 ).getChannelNo() );
        else
            System.out.println("To see the channel information, you must turn on the TV with the 2 button.");

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "Brand : "+brand+ " Dimension : "+dimension +" TV was created.";
    }

}
class Channel {
    private String name;
    private int channelNo;

    public Channel(String name, int channelNo) {
        this.name = name;
        this.channelNo = channelNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }


}
class NewsChannel extends Channel{

    private String newsType;


    public NewsChannel (String name, int channelNo,String type) {
        super(name, channelNo);
        this.newsType=type;
    }
}

class MusicChannel extends Channel{

    private String musicType;

    public MusicChannel(String name, int channelNo,String type) {
        super(name, channelNo);
        this.musicType=type;
    }
}