package org.jboss.arquillian.performance.cdi;

public class HardWorker
{
   public double workingHard()
   { 
      try
      {
         Thread.sleep(10);
      }
      catch (InterruptedException ie)
      {
         ie.printStackTrace();
      }
      return 21;
   }
}
