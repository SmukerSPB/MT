package Forms;

import org.jfree.data.xy.Vector;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.List;
import java.util.*;
public class MetodJohnson
{


    private int n = 5;
    private int[] a = new int[n];
    private int[] b = new int[n];
    private int[] c = new int[n];
    private int[] w = new int[n];
    public int[] p = new int[n];

    private int min(int a,int b)
    {
        if(a<=b)
        {
            return a;
        }
        return b;
    }

    private int sing(int a)
    {
        if (a>0)
        {
            return 1;
        }
        else if(a == 0)
        {
            return 0;
        }
        else {
            return -1;
        }
    }

    void swap(int a[], int i, int j)
    {

        int t = a[i];

        a[i] = a[j];

        a[j] = t;

    }

    public MetodJohnson(TableModel A) {


        int i, j, m = 1;

        Scanner in1 = new Scanner(System.in);

        for (i = 0; i < n; ++i) {
            p[i] = i + 1;
            a[i] = Integer.parseInt(A.getValueAt(i, 6).toString());;
            b[i] = Integer.parseInt(A.getValueAt(i, 7).toString());

            System.out.println("a["+(i+1)+"] b["+(i+1)+"] : "+a[i] +" "+b[i]);
            c[i] = min(a[i], b[i]);

            m = m + c[i];

        }

        for (i = 0; i < n; ++i) {
            w[i] = sing((a[i] - b[i]) * (m - c[i]));
        }



        for (i = 0; i < n; ++i) {
            for (j = 0; j < n - i - 1; ++j) {
                if (w[j] > w[j + 1]) {

                    swap(a, j, j + 1);

                    swap(b, j, j + 1);

                    swap(w, j, j + 1);

                    swap(p, j, j + 1);

                }
            }
        }

        for (i = 0; i < n; ++i) {
            System.out.print(p[i] + " ");
        }

    }
}
