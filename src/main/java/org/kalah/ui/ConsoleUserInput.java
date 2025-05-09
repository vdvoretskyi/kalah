package org.kalah.ui;

import java.util.Scanner;
import org.kalah.rules.UserInput;

public class ConsoleUserInput implements UserInput {

  @Override
  public int getHouseNumber() {
    System.out.println("Enter the index of the house to sow: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  @Override
  public void printError(final String errorMessage) {
    System.out.println(errorMessage);
  }
}
