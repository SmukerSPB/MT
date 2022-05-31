package Forms;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManegmentMenu extends JFrame {
    private JPanel panel1;
    private JButton start;
    private JButton паузаButton;
    private JButton стопButton;
    private JProgressBar progressBar1;
    private JTabbedPane InformationPanel;
    private JPanel Shipment;
    private JPanel Acceptance;
    public JTable tableOrdersA;
    public JTable tableOrdersS;
    private JPanel DiagramPanel;
    private JPanel SettingConn;
    private JButton ShippingOrdersS;
    private JButton ShippingOrdersA;
    private JLabel ServerSQL;
    private JLabel BaseSQL;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ConnectionBase;
    private JButton CheckConnectionBase;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JPanel DiagramPanelO;
    private JPanel DiagramPanelA;


    public ManegmentMenu() {
        setContentPane(panel1);
        setTitle("Системное меню управления");
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        tableOrdersS.setFillsViewportHeight(true);
        tableOrdersA.setFillsViewportHeight(true);
        tableOrdersA.validate();
        tableOrdersS.validate();



        createTable();
        tableOrdersS.getRowCount();

      //  System.out.println(tableOrdersA.getModel().getValueAt(getModel().getValueAt(1,1),1));




        CheckConnectionBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MetodJohnson Result = new  MetodJohnson(tableOrdersA.getModel());

                final GanttChart1 demo = new GanttChart1("Диаграмма Гантта - классический метод Джонсана",tableOrdersS.getModel(),Result.p);
                ChartPanel chartPanel = new ChartPanel(demo.chart);
                chartPanel.setDomainZoomable(true);

                ChartPanel chartPanel2 = new ChartPanel(demo.chart2);
                chartPanel.setDomainZoomable(true);

               /* demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);*/

                DiagramPanelA.setLayout(new BorderLayout());
                DiagramPanelA.add(chartPanel, BorderLayout.CENTER);
                DiagramPanelA.setVisible(true);
                DiagramPanelA.validate();

                DiagramPanelO.setLayout(new BorderLayout());
                DiagramPanelO.add(chartPanel2, BorderLayout.CENTER);
                DiagramPanelO.setVisible(true);
                DiagramPanelO.validate();


            }
        });


    }


    private void createTable(){

        Object[][] data = {

                {"1", "a", 5, 3, 2, 1, 12, 8},
                {"2", "b", 2, 3, 3, 4, 11, 13},
                {"3", "c", 0, 6, 1, 3, 13, 11},
                {"4", "d", 1, 1, 3, 2,  6, 9},
                {"5", "e", 3, 2, 4, 3, 11, 13}
        };


        String[] Col = {"N", "Наименование заказа", "Время выполнения на 1 этапе (Ai)", "Время выполнения на 2 этапе (Bi)", "Время выполнения на 3 этапе (Ci)", "Время выполнения на 4 этапе (Di)","Первый промежуточный этап","Второй промежуточный результат"};

        DefaultTableModel tableModel = new DefaultTableModel(data,Col);
        tableOrdersS.setModel(tableModel);
        tableOrdersS.getTableHeader().setResizingAllowed(false);

         ;


        tableOrdersA.setModel(new DefaultTableModel(
                data,
                Col));


    }


    public static void main(String[] args) {
        ManegmentMenu myFrame = new ManegmentMenu();
    }

}

