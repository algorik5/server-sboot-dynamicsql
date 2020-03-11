package util;

import java.util.*;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.time.*;
import org.apache.commons.lang3.math.NumberUtils;

public class ConsoleCommand extends Thread
{
  public void run()
  {
    try
    {
      Scanner lineScanner = new Scanner(System.in);
      lineScanner.useDelimiter("\n");

      while(lineScanner.hasNext())
      {
        String command = lineScanner.next().trim();
        if(command.length() < 1)
        {
          System.out.println("");
          continue;
        }

        System.out.println("command # "+ command);

        if(command.equals("help")) printHelp();
        else
        {
          System.out.println("UNKNOWN COMMAND --------- command # "+ command);
        }
      }
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void waitYesNo()
  {
    Scanner lineScanner = new Scanner(System.in);
    lineScanner.useDelimiter("\n");

    System.out.println("@@@@@@@@@ Continue. (y/n) >>> ");

    String str = lineScanner.next().trim();
    if(str.equals("y") == false)
    {
      System.exit(0);
    }
  }
  public static boolean waitYesNoEx()
  {
    Scanner lineScanner = new Scanner(System.in);
    lineScanner.useDelimiter("\n");

    System.out.println("@@@@@@@@@ Continue. (y/n) >>> ");

    String str = lineScanner.next().trim();

    if(str.equals("y") == false) return false;

    return true;
  }

  public void printHelp()
  {

  }
}