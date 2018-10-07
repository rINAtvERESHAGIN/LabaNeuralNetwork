package Laba1_0;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Laba1 {

// нормализовать данные тримя способами
    // 1) Линейно
    // Приведения зн-ч переменных к одинаковому масштабу измерений , диапазону нормализации [0;1] [-1;1]
    private double value;
    private double [] resultData;
    private double valueForParamWith_C;
    private double [] deNormalizeData;

    private double[] data;

    public static void main(String[] args) throws Exception {
        Laba1 xMin = new Laba1();
        Laba1 xMax = new Laba1();
        Laba1 xC = new Laba1();

        Laba1 initialData = new Laba1();
        //test
        Laba1 result = new Laba1();
        Laba1 resultDenormalize = new Laba1();
        double[] data = {70,88,70,70,55,62}; //вектор
        initialData.setData(data);
        boolean flag1 = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;

        int optionsForFindingXminAndmax = 0;
        String strToConverToNum = "";
        while (flag) {
            System.out.println("Выбрать метод нахождения x минимального и максимального");
            System.out.println("1: Метод нахождения значения мин и макс из текущего ветора");
            System.out.println("2: Метод введения теоритически возможных значений");
            System.out.println("3: Метод выборки средних размеров , расширения");
//            try {
                System.out.println("Введите значение:");
//                optionsForFindingXminAndmax = Integer.parseInt(reader.readLine());
//                System.out.println("Варинат выбран: " + optionsForFindingXminAndmax); // учесть колличество вариантов что бы число попадало в рамки : пример от 1 до 5 , variant было равно одному из этих чисел
////                flag = false;
//            } catch (Exception c) {
//                System.out.println("Введено неверно значения ");
//                optionsForFindingXminAndmax = 0;
//            }
          strToConverToNum = reader.readLine();
            if (controlOfInputData(strToConverToNum) && controlOfInputDataByRange(3 , 0 , strToConverToNum)){
                optionsForFindingXminAndmax = Integer.parseInt(strToConverToNum);
                System.out.println("Варинат выбран: " + optionsForFindingXminAndmax);
                Thread.sleep(1000);
            }else {
                System.out.println("Введено неверно значения ");
                optionsForFindingXminAndmax = 0;
            }
                switch (optionsForFindingXminAndmax) {
                    case 1:
                        result.methodOne(xMin, xMax, initialData.getData()); // вызов метода
                        flag = false;
                        break;
                    case 2:
                        result.methodTwo(xMin, xMax, 90.0, 30.0);
                        flag = false;
                        break;
                    case 3:
                        result.methodThree(xMin, xMax, initialData.getData());
                        flag = false;
                        break;
                }
        } // конец while
        System.out.println("Xmin = " + xMin.getValue() + '\n' + "Xmax = " + xMax.getValue() + '\n');
        // Нашли минимальные и максимальные значения из вектора

        boolean flagSecondPart = true;
        int varientsSwitchParametrs = 0;
        String testStrSecondStep = "";
        while (flagSecondPart){
            System.out.println("НАЙДЕМ НОРМАЛИЗОВАННЫЕ ЗНАЧЕНИЯ!");
            System.out.println("1: Линейная нормализация в пределах от 0 до 1");
            System.out.println("2: Линейная нормализация в пределах от -1 до 1");
            System.out.println("3: Нелинейная нормализация с использованием сигмойдной логистической функции в пределах от 0 до 1");
            System.out.println("4: Нелинейная нормализация с использованием сигмойдной логистической функции в пределах от -1 до 1");
            System.out.println("Введите цифру , для выбора метода: ");
            testStrSecondStep = reader.readLine();

            if (controlOfInputData(testStrSecondStep) && controlOfInputDataByRange(4, 0 ,testStrSecondStep)){
                varientsSwitchParametrs = Integer.parseInt(testStrSecondStep);
                System.out.println("Варинат выбран: " + varientsSwitchParametrs);
                xC.setValueForParamWith_C(methodToFindParamWith_C(xMin,xMax));
                Thread.sleep(1000);
            }
            else {
                System.out.println("Введено неверно значения ");
                varientsSwitchParametrs = 0;
            }
            String whatTheMethodYouChose = "";
            switch (varientsSwitchParametrs){
                case 1:
                    result.setResultData(linearNormalizationOfInputParametersFromZeroToOne(initialData.getData(),xMin,xMax));
                    whatTheMethodYouChose = "Линейная нормализация в пределах от 0 до 1";
                    printResult(result.getResultData() , whatTheMethodYouChose);
                    resultDenormalize.setDeNormalizeData(linearDenormalizationOfInputParametersFromZeroToOne(xMin,xMax,result));
                    whatTheMethodYouChose = "Линейная денормализация в пределах от 0 до 1";
                    printResult(resultDenormalize.getDeNormalizeData(),whatTheMethodYouChose);
                    break;
                case 2:
                    result.setResultData(linearNormalizationOfInputParametersFromMinusOneToOneUnit(initialData.getData(),xMin,xMax));
                    whatTheMethodYouChose = "Линейная нормализация в пределах от -1 до 1";
                    printResult(result.getResultData() , whatTheMethodYouChose);
                    resultDenormalize.setDeNormalizeData(linearDenormalizationOfInputParametersFromMinusOneToOne(xMin,xMax,result));
                    whatTheMethodYouChose = "Линейная денормализация в пределах от -1 до 1";
                    printResult(resultDenormalize.getDeNormalizeData(),whatTheMethodYouChose);
                    break;
                case 3:
                    result.setResultData(NonlinearNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromZeroToOneUnit(initialData.getData(),xMin,xMax,0.2,xC));
                    whatTheMethodYouChose = "Нелинейная нормализация с использованием сигмойдной логистической функции в пределах от 0 до 1";
                    printResult(result.getResultData() , whatTheMethodYouChose);
                    //
                    resultDenormalize.setDeNormalizeData(NonlinearDeNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromZeroToOneUnit(xMin,xMax,result,0.2,xC));
                    whatTheMethodYouChose = "Нелинейная денормализация с использованием сигмойдной логистической функции в пределах от 0 до 1";
                    printResult(resultDenormalize.getDeNormalizeData(),whatTheMethodYouChose);
                    break;
                case 4:
                    result.setResultData(NonlinearNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromMinusOneToOneUnit(initialData.getData(),xMin,xMax,0.2,xC));
                    whatTheMethodYouChose = "Нелинейная нормализация с использованием сигмойдной логистической функции в пределах от -1 до 1";
                    printResult(result.getResultData() , whatTheMethodYouChose);
                    //
                    resultDenormalize.setDeNormalizeData(NonlinearDeNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromMinusOneToOneUnit(xMin,xMax,result,0.2,xC));
                    whatTheMethodYouChose = "Нелинейная денормализация с использованием сигмойдной логистической функции в пределах от -1 до 1";
                    printResult(resultDenormalize.getDeNormalizeData(),whatTheMethodYouChose);
                    break;
            }

        }
//        result.setResultData(linearNormalizationOfInputParametersFromMinusOneToOneUnit(data,xMin,xMax));

        // Вывод на экран результато расчета нормализации
//        printResult(result.getResultData());
        //

    } // end main

    // Вывод на экран результато расчета нормализации
    public static void printResult(double[] arraysToPrint , String whatWeDo){
        System.out.println(whatWeDo);
        for (double value:arraysToPrint){
            System.out.println(value);
        }
    }
  // проверка значений удовлетворяющий выбору
  public static boolean controlOfInputData(String strToNum){
        boolean flag = true;
        try {
            int value = Integer.parseInt(strToNum);
            flag = true;
        }catch (Exception e){
            flag = false;
        }
        return flag;
  }
  public static boolean controlOfInputDataByRange(int rangeLeft , int rangeRigth , String strToNum){
        boolean flag = true;
        int values = 0;
        try{
            values = Integer.parseInt(strToNum);
        }catch (Exception e){
            flag = false;
        }
        if (values > rangeLeft || values <= rangeRigth ){
            flag = false;
        }
        else {
            flag = true;
        }
        return flag;
  }
  // денормализация .
                    // денормализация в пределах от [0:1]
    public static double[] linearDenormalizationOfInputParametersFromZeroToOne(Laba1 min , Laba1 max , Laba1 resultDataNormalize ){

        double [] resultDenomnalizeData  = new double[resultDataNormalize.getResultData().length];
        for (int i = 0; i < resultDataNormalize.getResultData().length ; i++) {
            resultDenomnalizeData[i] = min.getValue() + resultDataNormalize.getResultData()[i] * (max.getValue() - min.getValue());
        }
        return resultDenomnalizeData;
    }
                    // денормализация в пределах от [-1:1]
                    public static double[] linearDenormalizationOfInputParametersFromMinusOneToOne(Laba1 min , Laba1 max , Laba1 resultDataNormalize ){

                        double [] resultDenomnalizeData  = new double[resultDataNormalize.getResultData().length];
                        for (int i = 0; i < resultDataNormalize.getResultData().length ; i++) {
                            resultDenomnalizeData[i] = min.getValue() + (resultDataNormalize.getResultData()[i] + 1) * (max.getValue() - min.getValue()) / 2;
                        }
                        return resultDenomnalizeData;
                    }
                    // сигмойдная денормализация в пределах от [0:1]
                    public static double[] NonlinearDeNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromZeroToOneUnit(Laba1 min , Laba1 max , Laba1 resultDataNormalize, double alfa , Laba1 yC){
                        double [] resultDenomnalizeData  = new double[resultDataNormalize.getResultData().length];
                        for (int i = 0; i < resultDataNormalize.getResultData().length; i++) {
                            resultDenomnalizeData[i] = yC.getValueForParamWith_C() - (1/alfa) * Math.log((1/resultDataNormalize.getResultData()[i]) - 1 );
                        }
                        return resultDenomnalizeData;
                    }
                    // сигмойдная денормализация в пределах от [-1:1]
                    public static double[] NonlinearDeNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromMinusOneToOneUnit(Laba1 min , Laba1 max , Laba1 resultDataNormalize, double alfa , Laba1 yC){
                        double [] resultDenomnalizeData  = new double[resultDataNormalize.getResultData().length];
                        for (int i = 0; i < resultDataNormalize.getResultData().length; i++) {
                            resultDenomnalizeData[i] = yC.getValueForParamWith_C() - (1/alfa) * Math.log((1 - resultDataNormalize.getResultData()[i])/(1 + resultDataNormalize.getResultData()[i]));
                        }
                        return resultDenomnalizeData;
                    }
  //
  // нелинейная нормализация сигмойдной функцией в пределах от [-1:1]
  public static double[] NonlinearNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromMinusOneToOneUnit(double[] arraysOfValues, Laba1 xmin , Laba1 xmax , double alfa , Laba1 xC){
      double resultMas[] = new double[arraysOfValues.length];
      for (int i = 0; i < arraysOfValues.length; i++) {
          resultMas[i] = (Math.exp(alfa*(arraysOfValues[i] - xC.getValueForParamWith_C())) - 1)/(Math.exp(alfa*(arraysOfValues[i] - xC.getValueForParamWith_C())) + 1);
      }
        return resultMas;
  }
  // нелинейная нормализация сигмойдной функцией в пределах от [0:1]
  public static double[] NonlinearNormalizationOfTheSigmoidLogisticFunctionInTheRangeFromZeroToOneUnit(double[] arraysOfValues, Laba1 xmin , Laba1 xmax , double alfa , Laba1 xC){
      double resultMas[] = new double[arraysOfValues.length];
      for (int i = 0; i < arraysOfValues.length; i++) {
          resultMas[i] = 1/(Math.exp(-alfa * (arraysOfValues[i] - xC.getValueForParamWith_C())) + 1);
      }
      return resultMas;
  }
  // методо нахождения параметров с C
  public static double methodToFindParamWith_C(Laba1 min , Laba1 max){
        double CResult = 0.0;
        CResult = (min.getValue() + max.getValue()) / 2;
        return CResult;
  }
 // линейная нормализация в пределах [-1:1]
    public static double[] linearNormalizationOfInputParametersFromMinusOneToOneUnit(double[] arraysOfValues, Laba1 xmin , Laba1 xmax){
        double resultMas[] = new double[arraysOfValues.length];
        for (int i = 0; i < arraysOfValues.length ; i++) {
            resultMas[i] = 2 * ((arraysOfValues[i] - xmin.getValue())/(xmax.getValue() - xmin.getValue())) - 1;
        }
        return resultMas;
    }

 // линейная нормализация в пределах [0:1]
    public static double[] linearNormalizationOfInputParametersFromZeroToOne(double[] arraysOfValues, Laba1 xmin , Laba1 xmax){
        double resultMas[] = new double[arraysOfValues.length];
        for (int i = 0; i < arraysOfValues.length ; i++) {
            resultMas[i] = (arraysOfValues[i] - xmin.getValue() )/(xmax.getValue() - xmin.getValue());
        }
        return resultMas;
    }

    // (метод выборки средних размеров , расширение)
    public void methodThree(Laba1 xmin , Laba1 xmax, double[] arraysOfValue){
        xmin.setValue(methodMin(arraysOfValue));
        xmax.setValue(methodMax(arraysOfValue));
        double newXmin = xmin.getValue() - 0.1 * (xmax.getValue() - xmin.getValue());
        double newXmax = xmax.getValue() + 0.1 * (xmax.getValue() - xmin.getValue());
        xmax.setValue(newXmax);
        xmin.setValue(newXmin);
    }
    // (метод установки вручную данных из теоретичски возможных)
    public void methodTwo(Laba1 xmin , Laba1 xmax , double Xmax , double Xmin){
        xmin.setValue(Xmin);
        xmax.setValue(Xmax);
    }
    // (метод нахождения минимального и максимального значения из текущего ветора)
    public void methodOne(Laba1 xmin , Laba1 xmax , double[] massOfValues){
        xmax.setValue(methodMax(massOfValues));
        xmin.setValue(methodMin(massOfValues));
    }
    public double methodMin(double[] arrayOfValues ){
        double min = arrayOfValues[0];
        for (double num:arrayOfValues){
            if (min  > num)
                min = num;
        }
        return min;
    }
    public double methodMax(double[] arrayOfValues){
        double  max = arrayOfValues[0];
        for (double num:arrayOfValues){
            if (max < num)
                max = num;
        }
        return max;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public void setDeNormalizeData(double[] deNormalizeData) {
        this.deNormalizeData = deNormalizeData;
    }

    public double[] getDeNormalizeData() {
        return deNormalizeData;
    }

    public double getValueForParamWith_C() {
        return valueForParamWith_C;
    }

    public void setValueForParamWith_C(double valueForParamWith_C) {
        this.valueForParamWith_C = valueForParamWith_C;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double[] getResultData() {
        return resultData;
    }

    public void setResultData(double[] resultData) {
        this.resultData = resultData;
    }

    public Laba1() {
    }

    public static void defaultMethodWithParameters(){ // если хотим расчитать , значния для проверки работоспособности

    }
}
