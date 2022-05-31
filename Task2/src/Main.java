import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.util.*;


public class Main {


    public static void main(String[] args) throws ControllerException {
        //  Runtime rt = Runtime.instance();

        int Number_of_Agent = 0;

        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.GUI, "true");
        ContainerController cc = rt.createMainContainer(p);

        int Variant =0;
        int MenuSystem = 0;
        AgentController ac;


        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
        System.out.println("--------------- Функциональное меню  ------------------");
        System.out.println("* 1. Задайте количество Агентов в системе             *");
        System.out.println("* 2. Запустить генерацию чисел для Агентов            *");
        System.out.println("* 3. Задать структуру общения между клиентами         *");
        System.out.println("* 5. Вывести результат общения между объектами        *");
        System.out.println("* 4. Выход                                            *");
        System.out.println("-------------------------------------------------------");
        System.out.println("Для старта выберете пункт 1: ");

        do {

            Scanner ScannerMenu = new Scanner(System.in);
            MenuSystem = ScannerMenu.nextInt();

            switch (MenuSystem) {
                case 1:
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    System.out.println("Привет, пожалуйста, задай количество Агентов в системе: ");
                    Scanner inNumberAgent = new Scanner(System.in);
                    Number_of_Agent = inNumberAgent.nextInt();
                case 2:
                    boolean EndCreateConnection = false;

                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();

                    for(int i =0; i<Number_of_Agent;i++) {

                        try {
                            ac = cc.createNewAgent("AG[" + (i + 1) + "]", "myAgent", null);
                            ac.start();
                        } catch (StaleProxyException e) {
                            e.printStackTrace();
                        };
                    }


                    break;

                case 3:
                    break;
                case 4:
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    break;

                default:
                    System.out.print("\u001b[H\u001b[2J");
                    System.out.flush();
                    System.out.println("Не корректный ввод");
                    break;
            }
        } while (MenuSystem != 4);

        System.exit(0);
    }

}