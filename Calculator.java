import java.util.Scanner;

public class Calculator {

    Scanner scanner = new Scanner(System.in);
    NumberProcess process = new NumberProcess();
    BasicAction action = new BasicAction();

    public void start(){
        startCalc();

         while(true){

            System.out.println("Input: ");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                exitCalc();
                break;
            }

            try {
                String[] symbols = line.split(" ");
                if (symbols.length != 3) throw new Exception("Что-то пошло не так, попробуйте еще раз");

                Number firstNumber = process.createNumber(symbols[0]);
                Number secondNumber = process.createNumber(symbols[2], firstNumber.getType());
                String result = action.calculate(firstNumber, secondNumber, symbols[1]);
                System.out.println("Output: \n" + result);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                exitCalc();
                break;
            }
        }

            scanner.close();
    }

    private void startCalc() {
        System.out.println("Добро пожаловать в Калькулятор, он работает только с арабскими и римскими цифрами от 1 до 10\n" +
                        "Обладает довольно скудным фукционалом и может предложить только следующие операции:\n" +
                        "Сложение(+), Вычитание(-), Умножение(*), Деление(/)\n" +
                        "Если Вы хотите покинуть программу, введите 'exit'");
    }

    private void exitCalc() {

        System.out.print("До скорых встреч!");


    }
}