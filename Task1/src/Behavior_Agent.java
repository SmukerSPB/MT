import jade.core.AID;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.swing.*;

public class Behavior_Agent extends Behaviour/*OneShotBehaviour */ {
    private AID bestSeller; // The agent who provides the best offer
    private MessageTemplate mt; // The template to receive replies
    private ListAgent infoAgent;

    myAgent AC;

    public Behavior_Agent(myAgent AC) {
        this.AC = AC;
        infoAgent = AC.infoAgent;
    }


    @Override
    public void action() {


        if(infoAgent.LeftValue == null && infoAgent.RightValue == null ) {
            System.out.println(AC.getLocalName() + " отправлю число: " + AC.Number + " агенту " + infoAgent.HeadAgent.Agent);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID(infoAgent.HeadAgent.Agent, AID.ISLOCALNAME));
            msg.setContent("" + AC.Number);
            AC.send(msg);
            AC.SendMessenger = true;
        }



    }


    @Override
    public boolean done() {
        return AC.SendMessenger;
    }
}