package plugins;

public class Reverse implements Plugin{

  public String transform(String s){
    String result = "";
    for(int i = s.length() ; i > 0; i--){
      result += s.substring(i-1,i);
    }
    return result;
  }

  public String getLabel(){
    return "Reverse text";
  }

}
