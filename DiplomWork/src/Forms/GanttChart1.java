package Forms;

// import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.table.TableModel;

/**
 * Демонстрационный пример создания диаграммы Гантта с использованием JFreeChart
 */


public class GanttChart1 extends ApplicationFrame
{
    private static final long serialVersionUID = 1L;
    public  ChartPanel chartPanel;
    public  ChartPanel chartPanel2;

    public  JFreeChart chart2;
    public  JFreeChart chart;

    /**
     * Конструктор примера
     * @param title заголовок примера
     */
    public GanttChart1(final String title, TableModel a, int   p[])
    {
        super(title);
        DatasetGantt ds = new DatasetGantt(a,p);
        DatasetGantt2 ds2 = new DatasetGantt2(a,p);

        chart = createChart(ds.createDataset1(), "График выполнения методом Джонсона");
        chart2 = createChart(ds2.createDataset2(), "График выполнения");
        // Формат представления даты
        DateAxis axis = (DateAxis) chart.getCategoryPlot().getRangeAxis();
        DateAxis axis2 = (DateAxis) chart2.getCategoryPlot().getRangeAxis();
        DateFormat sdf = new SimpleDateFormat("mm");
        axis.setDateFormatOverride(sdf);
        axis2.setDateFormatOverride(sdf);
        // Локализация меток
        Locale locale = new Locale("ru");
        axis.setLocale(locale);
        axis2.setLocale(locale);
        // Размещение оси абсцисс внизу
        chart.getCategoryPlot().setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        chart2.getCategoryPlot().setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        // Размещение диаграммы на панели
        chartPanel = new ChartPanel(chart);
        chartPanel2 = new ChartPanel(chart2);
       // chartPanel.setPreferredSize(new java.awt.Dimension(600, 450));

        setContentPane(chartPanel);
    }
    /**
     * Метод создания диаграммы
   */
    private JFreeChart createChart(final IntervalCategoryDataset dataset, final String title) {
        final JFreeChart chart = ChartFactory.createGanttChart(
                title,   // chart title
//          "Task",  // domain axis label
                null,    // domain axis label
//          "Date",  // range axis label
                null,    // range axis label
                dataset, // data
                true,    // include legend
                true,    // tooltips
                false    // urls
        );
//      chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;
    }

}
