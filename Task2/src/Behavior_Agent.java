import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentManager;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class Behavior_Agent extends SimpleBehaviour {

    private boolean finished = false;
    public String DestinationPoint;

    double alpha;
    myAgent Agent;
    int step = 0;
    boolean Print = false;
    double Summ = 0;
    double x0;

    public Behavior_Agent(myAgent AC) {

        Agent = AC;
        System.out.println(AC.getLocalName() + " - Мои друзья " + AC.ArrConn);
        x0 = Agent.MyNumber;
    }


    @Override
    public void action() {


        if (Print == false) {
            for (int i = 0; i < Agent.ArrConn.size(); i++) {

                //  System.out.println("Шаг " + step + " - " + Agent.getLocalName() + " - отправил число - " + Agent.MyNumber + " соседy: " + "AG[" + Agent.ArrConn.get(i) + "]");
                //  System.out.println("Шаг " + step + " - " + Agent.getLocalName() + " - отправил число - " + Agent.MyNumber + "Мои соседи: " + Agent.ArrConn);
                ACLMessage NewMess = new ACLMessage(ACLMessage.INFORM);
                NewMess.setOntology("Step - " + step);
                NewMess.addReceiver(new AID("AG[" + Agent.ArrConn.get(i) + "]", AID.ISLOCALNAME));
                NewMess.setContent("" + (Agent.MyNumber));
                Agent.send(NewMess);
                // checkNumber();
            }

            Print = true;

        }

    }


    @Override
    public boolean done() {
        for (int i = 0; i < Agent.ArrConn.size(); i++) {
            MessageTemplate m1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            MessageTemplate m2 = MessageTemplate.MatchOntology("Step - " + step);
            MessageTemplate m3 = MessageTemplate.and(m1, m2);

            ACLMessage msg = Agent.blockingReceive(m3, 180000);
            if (msg != null) {
                Summ += Double.parseDouble(msg.getContent());
            }

        }

        double Number = 0;
        if (Summ != 0) {
            {
                Number = (Agent.MyNumber + Summ);


                if (Agent.getLocalName().equals("AG[1]") || Agent.getLocalName().equals("AG[4]")) {
                    alpha = 4;
                } else {
                    alpha = 3;
                }

                Agent.MyNumber = Number / alpha;
                //  System.out.println(step + "--" + Agent.getLocalName() + " - Поменял число на - " + Number);
                // System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println(step + "--" + Agent.getLocalName() + " - Поменял число на - " + Agent.MyNumber + " - Сумма:" + Number);
                Summ = 0;
            }
        }
        step++;
        Print = false;

        if (step == 120) {
            if (Agent.getLocalName().equals("AG[1]"))
                System.out.println("Answer: "+ Agent.MyNumber+" (+/-)E = " + Math.abs(10 - Number/alpha));
            return true;
        }

        return false;

    }
}