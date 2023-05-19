package com.example;
import java.util.*;
import com.fazecast.jSerialComm.SerialPort;
import java.util.Arrays;


public class timeController {
    public double[] CongestionFactor;// array to define traffic data for each signal
    public int greenTime;
    String RD;
    double Currentarr[];
    double Freearr[];
    static int GreenSignalTime[] = new int[4];

    public timeController(){
        CongestionFactor = new double[4]; //since we are having 4 signals
        greenTime = 60; // assuming that general green signal time is 60secs.
       
    }

    public void updateData() throws Exception{
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of roads :");//Taking input for number of roads
        int n = sc.nextInt();
        double[] CurrentSpeedArray = new double[n];
        double[] FreeFlowSpeedArray = new double[n];
        

        for(int i=0;i<n;i++){
           System.out.print("Enter road name :");//Taking input for name of the road
           String RD = sc.next();
           System.out.print("Enter the co-ordinates :");//Taking input for co-ordinates of the code i.e Latitude and Longitude
           String coordinates = sc.next();
           data m = new data(coordinates,RD); 
           CurrentSpeedArray[i] = m.getCurrentSpeed(); //Array
           FreeFlowSpeedArray[i] = m.getFreeFlowSpeed();
           CongestionFactor[i]= CurrentSpeedArray[i]/FreeFlowSpeedArray[i];
        }
        sc.close();

        System.out.print("Array for Current Speeds is: "); 
        System.out.println(Arrays.toString(CurrentSpeedArray));
        System.out.print("Array for FreeFlow Speeds is: ");
        System.out.println(Arrays.toString(FreeFlowSpeedArray));
        System.out.print("Array for Traffic Data is : ");
        System.out.println(Arrays.toString(CongestionFactor));  
        System.out.println();
    }

    public int[] greenSigTime(){
        
        double totaltraffic = 0;
        for(int i=0;i<4;i++){
            totaltraffic = totaltraffic + CongestionFactor[i];
        }
        double[] percentage = new double[4];
        for(int i=0;i<4;i++){
            percentage[i] = (double) CongestionFactor[i] / totaltraffic;
        }
        for(int i =0;i<4; i++){
            int signalGreenTime = (int) Math.round(greenTime*percentage[i]);
            
            GreenSignalTime[i]= signalGreenTime; 
            System.out.println("Total traffic around the signal is :"+ totaltraffic);
            System.out.println("Green Signal time for Road "+ (i+1) +" is "+ signalGreenTime +" seconds.");
            
            

        }
        System.out.println("Array for Signal Time is :");
            System.out.println(Arrays.toString(GreenSignalTime));

            return GreenSignalTime;
         


    }

    public static void main(String[] args) throws Exception {
        
        timeController controller = new timeController();
        controller.updateData();
        controller.greenSigTime();
       
        //Serial Communication Starts here
        SerialPort serialPort = SerialPort.getCommPort("COM3");
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        serialPort.setComPortParameters(9600, 8, 1, 0);
        serialPort.openPort();
        serialPort.setBaudRate(9600);

        //Time Delay for the communication to be established
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if(serialPort.openPort()){
            System.out.println("Serial Port is open for Connection"); //Checking For whether the port is open or not.
        }
        else{
            System.out.println("Serial Port is closed");
        }
        
        
        int r1 = GreenSignalTime[0];
        int r2 = GreenSignalTime[1];
        int r3 = GreenSignalTime[2];
        int r4 = GreenSignalTime[3];

        String time1 = Integer.toString(r1); 
        serialPort.writeBytes(time1.getBytes(), time1.getBytes().length);

        Thread.sleep(1100);
        System.out.println("Data for Road 1 sent !");

        String time2 = Integer.toString(r2);
        serialPort.writeBytes(time2.getBytes(), time2.getBytes().length);
        Thread.sleep(1100);
        System.out.println("Data for Road 2 sent !!");

        String time3 = Integer.toString(r3); 
        serialPort.writeBytes(time3.getBytes(), time3.getBytes().length);
        Thread.sleep(1100);
        System.out.println("Data for Road 3 sent !!!");

        String time4 = Integer.toString(r4);
        serialPort.writeBytes(time4.getBytes(), time4.getBytes().length);
        Thread.sleep(1100);
        System.out.println("Data for Road 4 sent !!!!");

        //OutputStream outputStream = serialPort.getOutputStream();
        //int r1 = GreenSignalTime[0];
        // Send the array
        /*int[] array1 = {10,5};
        try {
            for (int i = 0; i < array1.length; i++) {
                outputStream.write(array1[i]);
            }
            outputStream.flush();
            System.out.println("Array sent: " + Arrays.toString(array1));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Thread.sleep(r1*1000+3000);
       
        /*int[] array2 = {11, r2};
        try {
            for (int i = 0; i < array2.length; i++) {
                outputStream.write(array2[i]);
            }
            outputStream.flush();
            System.out.println("Array sent: " + Arrays.toString(array2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(r2*1000+3000);

        int[] array3 = {12, r3};
        try {
            for (int i = 0; i < array3.length; i++) {
                outputStream.write(array3[i]);
            }
            outputStream.flush();
            System.out.println("Array sent: " + Arrays.toString(array3));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(r3*1000+3000);

        int[] array4 = {13, r4};
        try {
            for (int i = 0; i < array4.length; i++) {
                outputStream.write(array4[i]);
            }
            outputStream.flush();
            System.out.println("Array sent: " + Arrays.toString(array4));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(r4*1000+3000);
       
        

        Thread.sleep(5000);*/



        

        //int r1 = GreenSignalTime[0];
        /*int r2 = GreenSignalTime[1];
        int r3 = GreenSignalTime[2];
        int r4 = GreenSignalTime[3];

       
        String time1 = String.valueOf(r1); 
        serialPort.writeBytes(time1.getBytes(), time1.getBytes().length);
        

        String time2 = String.valueOf(r2); 
        serialPort.writeBytes(time2.getBytes(), time2.getBytes().length);
        

        String time3 = String.valueOf(r3); 
        serialPort.writeBytes(time3.getBytes(), time3.getBytes().length);
        

        String time4 = String.valueOf(r4); 
        serialPort.writeBytes(time4.getBytes(), time4.getBytes().length);*/
        


        
        
        

        
        

        
        
        
        
    }
    
}
