import java.util.List;
import java.util.Stack;


public class ListAgent {

    ListAgent LeftValue;
    ListAgent RightValue;
    String Agent;
    String ID;

    float NumberOfAgenta, size;


    ListAgent HeadAgent;
    public ListAgent(){}


    public ListAgent addAgent(String AC, String ID, int Num) {
        if (HeadAgent == null) {
            ListAgent newNode = new ListAgent(AC, ID, Num);
            // System.out.println(AC + " загадал число: " + Num);
            HeadAgent = newNode;
            HeadAgent.HeadAgent = newNode;
            size++;
            return HeadAgent;
        } else { // корневой узел занят
            ListAgent currentNode = HeadAgent;
            ListAgent parentNode;
            while (true) {
                parentNode = currentNode;

                if (Num == currentNode.NumberOfAgenta) {
                    parentNode.LeftValue = new ListAgent(AC, ID, Num);
                    parentNode.LeftValue.HeadAgent = parentNode;
                    size++;
                    return  parentNode.LeftValue ;
                } else if (Num < currentNode.NumberOfAgenta) {
                    currentNode = currentNode.LeftValue;
                    if (currentNode == null) {
                        //   System.out.println(AC + " загадал число: " + Num);
                        parentNode.LeftValue = new ListAgent(AC, ID, Num);
                        parentNode.LeftValue.HeadAgent = parentNode;
                        size++;
                        return  parentNode.LeftValue ;
                    }
                } else { // Или направо?
                    currentNode = currentNode.RightValue;
                    if (currentNode == null) {
                        //System.out.println(AC + " загадал число: " + Num);
                        parentNode.RightValue = new ListAgent(AC, ID, Num);
                        parentNode.RightValue.HeadAgent = parentNode;
                        size++;
                        return  parentNode.RightValue ; // и выйти
                    }
                }
            }
        }
        //return null;
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(HeadAgent);
        int gaps = 42; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack localStack = new Stack(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                ListAgent temp = (ListAgent) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.NumberOfAgenta+" "+temp.ID); // выводим его значение в консоли
                    localStack.push(temp.LeftValue); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.RightValue);
                    if (temp.RightValue != null ||
                            temp.LeftValue != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }

    public ListAgent(String Agent, String ID, int Number) {
        this.Agent = Agent;
        this.ID = ID;
        this.NumberOfAgenta = Number;
    }


}

