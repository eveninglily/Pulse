import play.*;
import play.mvc.*;
import models.*;

import static play.mvc.Results.*;

public class Global extends GlobalSettings {

  @Override
  public void onStart(Application app) {
  }  
  
  @Override
  public void onStop(Application app) {
    Logger.info("Application shutdown...");
  }  
    
}   