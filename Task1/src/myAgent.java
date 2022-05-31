
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import javax.swing.*;


public class myAgent extends Agent {

    float Number;
    int size;
    float args;
    float Summ = 0,Variant,CountAgent;
    ListAgent infoAgent;
    boolean SendMessenger = false;
    int countActive = 0;
    protected void setup() {
        Object[] args = getArguments();
        infoAgent = (ListAgent) args[0];
        Number = infoAgent.NumberOfAgenta;
        size = Integer.parseInt((String)  args[1]);
        System.out.println(getLocalName() + " -  загодал " + Number);


        addBehaviour(new Behavior_Agent(this));

        addBehaviour(new Behaviour() {
            @Override
            public void action() {

                ACLMessage msg = receive();

                 if( msg != null && getLocalName().equals("AG[0]") && SendMessenger == false)
                 {
                        if(infoAgent.RightValue != null && infoAgent.LeftValue!=null) {
                            if (Summ == 0) {

                                Summ = Number + Float.parseFloat(msg.getContent());
                                System.out.println(getLocalName() + " Принял новое значение, обновил статус. Моя сумма: " + Summ);
                            } else {
                                Summ = Summ + Float.parseFloat(msg.getContent());
                                System.out.println("ИТОГОВАЯ СУММА АГЕНТОВ " + Summ);
                                if (size != 0) {
                                    System.out.println("СРЕДНЕЕ АРИФМИТИЧЕСКОЕ ЧИСЛО АГЕНТОВ РАВНО:  " + (float) (Summ / size));
                                }
                                SendMessenger = true;
                            }

                        }else
                        {
                            Summ = Summ + Float.parseFloat(msg.getContent())+Number;
                            System.out.println("ИТОГОВАЯ СУММА АГЕНТОВ РАВНА:" + Summ);
                            if (size != 0) {
                                System.out.println("СРЕДНЕЕ АРИФМИТИЧЕСКОЕ ЧИСЛО АГЕНТОВ РАВНО: " + (float) (Summ / size));
                            }
                            SendMessenger = true;
                        }



                }else if ( msg != null && ! getLocalName().equals("AG[0]") &&  SendMessenger == false && (infoAgent.RightValue == null || infoAgent.LeftValue==null))
                {
                    if(Summ == 0)
                    {
                        Summ  = Number+Float.parseFloat(msg.getContent());
                    }
                    System.out.println(getLocalName() + " отправлю число: " + Summ  + " агенту " + infoAgent.HeadAgent.Agent );

                    ACLMessage msgout = new ACLMessage(ACLMessage.INFORM);
                    msgout.addReceiver(new AID(infoAgent.HeadAgent.Agent, AID.ISLOCALNAME));
                    msgout.setContent("" + Summ);

                    send(msgout);
                    SendMessenger = true;
                }else if ( msg != null && ! getLocalName().equals("AG[0]") &&  SendMessenger == false && infoAgent.RightValue !=null && infoAgent.RightValue !=null)
                {

                    countActive +=1;
                    float backSumm = Number;
                    if(Summ == 0)
                    {
                        Summ  = Number+Float.parseFloat(msg.getContent());
                    }
                    else {
                        System.out.print(getLocalName() + " Моя предыдущая сумма до обновления : " + Summ);
                        Summ = Summ + Float.parseFloat(msg.getContent());
                        System.out.print(" - Мое новое значение: " + Summ + "\n");


                    }


                    if(countActive ==2) {

                        System.out.println(getLocalName() + " отправлю число: " + Summ  + " агенту " + infoAgent.HeadAgent.Agent + ". Моя сумма: "+Summ);
                        ACLMessage msgout = new ACLMessage(ACLMessage.INFORM);
                        msgout.addReceiver(new AID(infoAgent.HeadAgent.Agent, AID.ISLOCALNAME));
                        msgout.setContent("" + Summ);
                        send(msgout);
                        SendMessenger = true;
                    }
                }
            }

            @Override
            public boolean done() {
                return SendMessenger;
            }


        });



    }


}

