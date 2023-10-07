package com.authorname;
import com.authorname.model.Instrument;

import junit.framework.TestCase;

public class QuickTestJdbcTest extends TestCase() {

   private QuickTestJdbc quicktestjdbc;

   private List<Instrument> instruments;

   protected void setUp() throws Exception {
      super.setUp();
      quicktestjdbc = new QuickTestJdbc();
   }

   protected void tearDown() throws Exception {
      super.tearDown();
      quicktestjdbc = null;
   }

   public void testread_all_instrument() throws Exception {
      //instruments = quicktestjdbc.read_all_instrument();
      Instrument instrfirst = new Intrument(2,"trumpet",2,"middle row");
      String instrname = "trumpet";

      assertEquals(instrname,instrfirst.getName());
   }
}
