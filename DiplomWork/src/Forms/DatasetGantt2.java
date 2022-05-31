package Forms;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import javax.swing.table.TableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatasetGantt2
{
	private  TableModel table;
	private int arr;
	private  SimpleDateFormat   sdf;
	private  String[]  tasks = {"Этап №1",
			"Этап №2",
			"Этап №3",
			"Этап №4"};


	private  String[][] withOutAlgoritm  = {
			{"00", "05","05","08","08","10","10","11"},
			{"05", "07","08","11","11","14","14","18"},
			{"00", "00","12","17","17","18","18","21"},
			{"07", "08","17","18","18","21","21","23"},
			{"08", "11","18","20","21","23","23","25"},
	};



	public DatasetGantt2(TableModel a, int   p[]){}

	/**
	 * Метод конвертации текстовой даты в объект Date
	 *
	 * @param sdate текстовая дата
	 * @return Date объект даты
	 */
	private Date date(final String sdate)
	{
		if (sdf == null)
			sdf = new SimpleDateFormat("mm");

		Date date = null;
		try {
			date = sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * Создание Dataset плановых и актуальных задач для построения диаграммы Гантта
	 * @return Dataset 42531
	 */
	public IntervalCategoryDataset createDataset2()
	{
		final TaskSeries s1= new TaskSeries("Деталь 1");
		final TaskSeries s2= new TaskSeries("Деталь 2");
		final TaskSeries s3= new TaskSeries("Деталь 3");
		final TaskSeries s4= new TaskSeries("Деталь 4");
		final TaskSeries s5= new TaskSeries("Деталь 5");
	//	final TaskSeries s6= new TaskSeries("Деталь 6");

for(int i =0;i<4;i++) {
	s1.add(new Task(tasks[i], new SimpleTimePeriod(date(withOutAlgoritm[0][i * 2]), date(withOutAlgoritm[0][i * 2 + 1]))));
	s2.add(new Task(tasks[i], new SimpleTimePeriod(date(withOutAlgoritm[1][i * 2]), date(withOutAlgoritm[1][i * 2 + 1]))));
	s3.add(new Task(tasks[i], new SimpleTimePeriod(date(withOutAlgoritm[2][i * 2]), date(withOutAlgoritm[2][i * 2 + 1]))));
	s4.add(new Task(tasks[i], new SimpleTimePeriod(date(withOutAlgoritm[3][i * 2]), date(withOutAlgoritm[3][i * 2 + 1]))));
	s5.add(new Task(tasks[i], new SimpleTimePeriod(date(withOutAlgoritm[4][i * 2]), date(withOutAlgoritm[4][i * 2 + 1]))));
}







	//	s6.add(new Task(tasks[0], new SimpleTimePeriod(date(dates_schedule[5][0]),date(dates_schedule[5][1]))));


		final TaskSeriesCollection collection = new TaskSeriesCollection();
		collection.add(s1);
		collection.add(s2);
		collection.add(s3);
		collection.add(s4);
		collection.add(s5);
	//	collection.add(s6);
		return collection;
	}
}
