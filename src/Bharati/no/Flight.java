package Bharati.no;

public class Flight {

        private static int  count=0;
        private int flight_no=0;
        private int waiting_time=0;

    public void set_waiting_time(){
        this.waiting_time+=1;
    }
    public int get_waiting_time(){
        return this.waiting_time;
    }

    public int getFlight_no(){
        return flight_no;
    }

    Flight(){
        flight_no = count++;
    }





    public String getMessage(String s)
        {
            return "Flight "+flight_no+" ready for "+s;
        }

        @Override
        public String toString() {
            return "Flight{" + "Flight No = " + flight_no + '}';
        }
    }

