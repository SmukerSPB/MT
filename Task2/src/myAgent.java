
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class myAgent extends Agent {
    double MyNumber;
    List<Integer> ArrConn;
    boolean Status = true;
    int Step = 0;

    protected void setup() {

        //= (Math.random() * 100);
        ArrConn = new ArrayList<Integer>();

        if(getLocalName().equals("AG[1]")) {
            MyNumber = 8;
            ArrConn.add(2);
            ArrConn.add(4);
            ArrConn.add(5);

        }
        if (getLocalName().equals("AG[2]")) {
            MyNumber = 22;
            ArrConn.add(1);
            ArrConn.add(3);

        }

        if (getLocalName().equals("AG[3]")) {
            MyNumber = 15;
            ArrConn.add(2);
            ArrConn.add(4);

        }
        if (getLocalName().equals("AG[4]")) {
            MyNumber = 5;
            ArrConn.add(1);
            ArrConn.add(3);
            ArrConn.add(5);

        }
        if (getLocalName().equals("AG[5]")) {
            MyNumber = 0;
            ArrConn.add(1);
            ArrConn.add(4);
        }
        addBehaviour(new Behavior_Agent(this));




    }
}

