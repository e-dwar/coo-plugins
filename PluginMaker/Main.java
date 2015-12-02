public class Main{
    public static void main (String[] arg){

      String mot = "abcdef ghijkl";
      Plugin pluginSpace = new AddSpaceBetweenLetters();
      Plugin pluginAlternate = new Alternate();
      Plugin pluginReverse = new Reverse();

      System.out.println("DEBUG SPACE = " + pluginSpace.transform(mot));
      System.out.println("DEBUG ALTERNATE = " + pluginAlternate.transform(mot));
      System.out.println("DEBUG REVERSE = " + pluginReverse.transform(mot));
  }
}
