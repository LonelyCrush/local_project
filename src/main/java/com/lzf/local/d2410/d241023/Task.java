package com.lzf.local.d2410.d241023;


public class Task {

  private void equationRoot(double a, double b, double c) {
    double delta = b * b - 4 * a * c;
    if (delta > 0) {
      double x1 = (-b + Math.sqrt(delta)) / (2 * a);
      double x2 = (-b - Math.sqrt(delta)) / (2 * a);
      System.out.println("x1 = " + x1 + ", x2 = " + x2);
    } else if (delta == 0) {
      double x = -b / (2 * a);
      System.out.println("x = " + x);
    } else {
      System.out.println("The equation has no real roots.");
    }
  }

  private void reverseInteger(int input) {
    int reversed = 0;
    while (input!= 0) {
      reversed = reversed * 10 + input % 10;
      input /= 10;
    }
    System.out.println("Reversed integer: " + reversed);
  }

  public static void main(String[] args) {
    Task task = new Task();
    task.equationRoot(1, -4, 3);
    task.reverseInteger(123);
  }
}
