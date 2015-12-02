public class Alternate implements Plugin{

  public String transform(String s){
    String result = "";
    for(int i = 0 ; i < s.length(); i++){
      String temp = "";
      temp = s.substring(i,i+1);
      if((i % 2) == 0){
        temp = temp.toUpperCase();
      }
      else {
        temp = temp.toLowerCase();
      }
      result += temp;
    }
    return result;
  }

  public String getLabel(){
    return "Alternate upper an lower case";
  }

}
