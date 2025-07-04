
public class InputController{


public static boolean requestingUp(){
if(GameLib.iskeyPressed(GameLib.KEY_UP))return true;
    return false;
          }
public static boolean requestingDown(){
if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) return true;
 return false;         }
public static boolean requestingLeft(){
if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) return true;
return false;
          }
public static boolean requestingRight(){
if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) return true;
 return false;         }
public static boolean requestingCtrl(){
if(GameLib.iskeyPressed(GameLib.KEY_CONTROL))return true;
return false;
          }
public static boolean requestingEsc(){
if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) return true;
return false;
          }






}
