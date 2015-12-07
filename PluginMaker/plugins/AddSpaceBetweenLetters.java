package plugins;

public class AddSpaceBetweenLetters implements Plugin{

  public String transform(String s){
    String result = "";
    for(int i = 0 ; i < s.length(); i++){
      result += s.substring(i,i+1) + " ";
    }
    return result;
  }

  public String getLabel(){
    return "Add Space Between Letters";
  }

}
